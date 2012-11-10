package hu.hajba.gol.gui;

import javax.swing.JButton;
import javax.swing.JPanel;

import gol.board.Board;
import hu.hajba.gol.Point;


import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

/**
 * User: Hajbus
 * Date: 2012.11.10.
 * Time: 11:28
 */
public class GoLPanel extends JPanel
{
    private static final int WIDTH = 350;
    private static final int HEIGHT = 300;
    public static boolean RUNNING = false;

    public GoLPanel()
    {
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        createAndShowGUI();
    }

    private void createAndShowGUI()
    {
        final GameBoard gameBoard = new GameBoard(10, 10, new Board(10,10));
        gameBoard.initBoard(Arrays.asList(new Point(1, 3), new Point(2, 3), new Point(3, 3)));
        gameBoard.setBounds(105,10,220,250);
        this.add(gameBoard);
        new Thread(gameBoard).start();

        final JButton startButton = new JButton("Start");
        startButton.setSize(50,25);
        startButton.setBounds(10,10,75,25);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                RUNNING = !RUNNING;
                gameBoard.setRunning(RUNNING);
                startButton.setText(RUNNING ? "Stop" : "Start");
            }
        });

        this.add(startButton);
    }
}
