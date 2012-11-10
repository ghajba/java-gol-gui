package hu.hajba.gol;

import gol.board.Board;
import gol.rule.CellState;
import hu.hajba.gol.gui.GameBoard;
import hu.hajba.gol.gui.GoLFrame;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

/**
 * This is the main class, it starts the application.
 *
 * User: Hajbus
 * Date: 2012.11.09.
 * Time: 21:02
 */
public class Main
{
    /**
     * The main method.
     *
     * @param args -- java standard command line arguments
     */
    public static void main(String... args)
    {
        new GoLFrame().setVisible(true);
    }
}
