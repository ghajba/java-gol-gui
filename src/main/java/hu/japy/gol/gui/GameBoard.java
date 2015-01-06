package hu.japy.gol.gui;

import hu.japy.gol.board.GameOfLifeBoard;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

/**
 * @author GHajba
 */
public class GameBoard extends JPanel implements Runnable {
    private static final long serialVersionUID = -979139922610179824L;
    private static final int SQUARE_SIZE = 19;
    public static final int PADDING = 5;

    private final int sizeX;
    private final int sizeY;
    // private final Board board;
    private final GameOfLifeBoard board;

    private boolean running = false;

    public GameBoard(int sizeX, int sizeY) {
        this.setBackground(Color.WHITE);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        // this.board = new Board(this.sizeX, this.sizeY);
        this.board = new GameOfLifeBoard(this.sizeX, this.sizeY);
        new GameOfLifeBoard(this.sizeX, this.sizeY);
        this.addMouseListener(new MouseClickListener());

    }

    @Override
    public void paintComponent(Graphics g) {
        g.clearRect((int) getParent().getBounds().getX(), (int) getParent().getBounds().getY(), (int) getParent().getBounds().getWidth(),
                (int) getParent().getBounds().getHeight());
        g.setColor(Color.BLACK);
        for (int x = 0; x < this.sizeX; x++) {
            for (int y = 0; y < this.sizeY; y++) {
                if (this.board.getCellAt(x, y)) {
                    g.fillRect(x * SQUARE_SIZE + PADDING, y * SQUARE_SIZE + PADDING, SQUARE_SIZE, SQUARE_SIZE);
                }
            }
        }

        g.setColor(Color.DARK_GRAY);
        for (int i = 0; i <= this.sizeX; i++) {
            g.drawLine(i * SQUARE_SIZE + PADDING, PADDING, i * SQUARE_SIZE + PADDING, this.sizeY * SQUARE_SIZE + PADDING);
        }
        for (int j = 0; j <= this.sizeY; j++) {
            g.drawLine(PADDING, j * SQUARE_SIZE + PADDING, this.sizeX * SQUARE_SIZE + PADDING, j * SQUARE_SIZE + PADDING);
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void clearBoard() {
        this.board.resetBoard();
        updateBoard();
    }

    public void updateBoard() {
        this.revalidate();
        this.repaint();
    }

    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(300);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
            if (this.running && this.board.containsLiveCell()) {

                this.board.nextRound();
                updateBoard();
            }
        }
    }

    public class MouseClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent me) {
            if (GameBoard.this.running) {
                return;
            }
            GameBoard.this.board.changeCellAt(((me.getX() - PADDING) / SQUARE_SIZE), ((me.getY() - PADDING) / SQUARE_SIZE));
            me.getComponent().revalidate();
            me.getComponent().repaint();
        }
    }

    public static int getSquareDisplaySize() {
        return SQUARE_SIZE + PADDING;
    }
}
