package hu.hajba.gol.gui;

import javax.swing.JButton;
import javax.swing.JComboBox;
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
    private GameBoard gameBoard;

    public GoLPanel()
    {
        this.setLayout(null);
        this.setBackground(Color.WHITE);
        createAndShowGUI();
    }

    private void createAndShowGUI()
    {
        createGameBoard(10,10);

        final JButton clearButton = new JButton("Clear");
        clearButton.setSize(50,25);
        clearButton.setBounds(10,45,100,25);
        clearButton.setEnabled(!RUNNING);
        clearButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                gameBoard.clearBoard();
            }
        });

        final JComboBox<Integer> sizeXBox = new JComboBox<Integer>(createSequentialArray(1,25));
        sizeXBox.setBounds(10,80,40,25);

        final JComboBox<Integer> sizeYBox = new JComboBox<Integer>(createSequentialArray(1,25));
        sizeYBox.setBounds(60,80,40,25);

        final JButton setSizeButton = new JButton("Set size");
        setSizeButton.setBounds(10,115,100,25);
        setSizeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                createGameBoard(sizeXBox.getItemAt(sizeXBox.getSelectedIndex()),sizeYBox.getItemAt(sizeYBox.getSelectedIndex()));
            }
        });

        final JButton startButton = new JButton("Start");
        startButton.setSize(50, 25);
        startButton.setBounds(10, 10, 100, 25);
        startButton.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                RUNNING = !RUNNING;
                gameBoard.setRunning(RUNNING);
                startButton.setText(RUNNING ? "Stop" : "Start");
                clearButton.setEnabled(!RUNNING);
                sizeXBox.setEnabled(!RUNNING);
                sizeYBox.setEnabled(!RUNNING);
                setSizeButton.setEnabled(!RUNNING);
            }
        });

//        this.add(gameBoard);
        this.add(clearButton);
        this.add(startButton);
        this.add(sizeXBox);
        this.add(sizeYBox);
        this.add(setSizeButton);

    }

    private void createGameBoard(int sizeX, int sizeY)
    {
        if(gameBoard != null)
            this.remove(gameBoard);
        this.gameBoard = new GameBoard(sizeX, sizeY);
//        gameBoard.initBoard(Arrays.asList(new Point(1, 3), new Point(2, 3), new Point(3, 3)));
        gameBoard.setBounds(110,10,220,250);
        this.add(gameBoard);
        this.revalidate();
        this.repaint();
//        gameBoard.updateBoard();
        new Thread(gameBoard).start();
    }

    private Integer[] createSequentialArray(int from, int to)
    {
        if(to < from) return new Integer[0];
        int size = to - (from - 1);
        Integer[] rc = new Integer[size];
        for(int i = from, j = 0; i <= to; i++, j++)
        {
            rc[j] = i;
        }
        return rc;
    }
}
