package hu.japy.gol.gui;

import gol.board.Board;
import gol.board.Cell;
import gol.rule.CellState;
import hu.japy.gol.Point;

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
    private final Board board;

    private boolean running = false;

    public GameBoard(int sizeX, int sizeY) {
        this.setBackground(Color.WHITE);
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.board = new Board(this.sizeX, this.sizeY);
        this.addMouseListener(new MouseClickListener());

    }

    @Override
    public void paintComponent(Graphics g) {
        g.clearRect((int) getParent().getBounds().getX(), (int) getParent().getBounds().getY(), (int) getParent().getBounds().getWidth(),
                (int) getParent().getBounds().getHeight());
        g.setColor(Color.BLACK);
        for (int x = 0; x < this.sizeX; x++) {
            for (int y = 0; y < this.sizeY; y++) {
                if (CellState.ALIVE.equals(this.board.getCellAt(x, y).getCurrentState())) {
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

    public void initBoard(java.util.List<Point> startingPoints) {
        for (final Point point : startingPoints) {
            addCell(point.getX(), point.getY());
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void clearBoard() {
        for (int i = 0; i < this.sizeX; i++) {
            for (int j = 0; j < this.sizeY; j++) {
                this.board.getCellAt(i, j).setCurrentState(CellState.DEAD);
            }
        }
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
                Thread.sleep(500);
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
            if (this.running && containsLiveCell(this.board)) {

                this.board.nextRound();
                updateBoard();
            }
        }
    }

    private void addCell(int x, int y) {
        this.board.getCellAt(x, y).setCurrentState(CellState.ALIVE);
    }

    private static boolean containsLiveCell(Board board) {
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (CellState.ALIVE.equals(board.getCellAt(i, j).getCurrentState())) {
                    return true;
                }
            }
        }
        return false;
    }

    public class MouseClickListener extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent me) {
            if (GameBoard.this.running) {
                return;
            }
            final Cell cellAt = GameBoard.this.board
                    .getCellAt(((me.getX() - PADDING) / SQUARE_SIZE), ((me.getY() - PADDING) / SQUARE_SIZE));
            if (CellState.ALIVE == cellAt.getCurrentState()) {
                cellAt.setCurrentState(CellState.DEAD);
            } else {
                cellAt.setCurrentState(CellState.ALIVE);
            }
            me.getComponent().revalidate();
            me.getComponent().repaint();
        }
    }

    /**
     * @return
     */
    public static int getSquareDisplaySize() {
        return SQUARE_SIZE + PADDING;
    }
}
