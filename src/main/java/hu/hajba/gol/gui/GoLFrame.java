package hu.hajba.gol.gui;

import gol.board.Board;
import hu.hajba.gol.*;

import javax.swing.*;
import java.awt.*;
import java.awt.Point;
import java.util.Arrays;

/**
 * User: Hajbus
 * Date: 2012.11.10.
 * Time: 11:28
 */
public class GoLFrame extends JFrame
{
    private static final int WIDTH = 350;
    private static final int HEIGHT = 300;
    public GoLFrame()
    {
        this.setTitle("Game of Life");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setPreferredSize(new Dimension(WIDTH,HEIGHT));

        this.getContentPane().add(new GoLPanel());

        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }
}
