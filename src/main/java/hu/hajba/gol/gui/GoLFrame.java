package hu.hajba.gol.gui;

import java.awt.Dimension;

import javax.swing.JFrame;

/**
 * @author GHajba
 */
public class GoLFrame extends JFrame {
    private static final long serialVersionUID = 498792054583252572L;
    private static final int WIDTH = 400;
    private static final int HEIGHT = 240;

    public GoLFrame() {
        this.setTitle("Game of Life");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(WIDTH, HEIGHT);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        this.getContentPane().add(new GoLPanel(this));

        this.setResizable(false);
        this.pack();
        this.setVisible(true);
    }
}
