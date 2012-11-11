package hu.hajba.gol.gui;

import gol.board.Board;
import gol.board.Cell;
import gol.rule.CellState;
import hu.hajba.gol.Point;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * User: Hajbus
 * Date: 2012.11.09.
 * Time: 22:23
 */
public class GameBoard extends JPanel implements Runnable
{
    private static final int SQUARE_SIZE = 19;
    public static final int PADDING = 5;
//    private static final Board board = new Board(10,10);

    private final int sizeX;
    private final int sizeY;
    private final Board board;

    private boolean running = false;


    public GameBoard(int sizeX, int sizeY)
    {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.board = new Board(this.sizeX,this.sizeY);
        this.addMouseListener(new MouseClickListener());

    }
    public void paintComponent(Graphics g)
    {
        g.clearRect((int)getParent().getBounds().getX(),(int)getParent().getBounds().getY(),(int)getParent().getBounds().getWidth(),(int)getParent().getBounds().getHeight());
//        g.drawRect((int)getParent().getBounds().getX(),(int)getParent().getBounds().getY(),(int)getParent().getBounds().getWidth(),(int)getParent().getBounds().getHeight());
        for(int i = 0; i <= sizeX; i++)
        {
            g.drawLine(i*SQUARE_SIZE+PADDING,PADDING,i*SQUARE_SIZE+PADDING,sizeY*SQUARE_SIZE+PADDING);
        }
        for(int j = 0; j <= sizeY; j++)
        {
            g.drawLine(PADDING, j*SQUARE_SIZE+PADDING,sizeX*SQUARE_SIZE + PADDING,j*SQUARE_SIZE+PADDING);
        }

        for(int x = 0; x < sizeX; x++)
        {
            for(int y = 0; y < sizeY; y++)
            {
                if(CellState.ALIVE.equals(board.getCellAt(x,y).getCurrentState()))
                {
                    g.fillRect(x*SQUARE_SIZE+PADDING+3,y*SQUARE_SIZE+PADDING+3,SQUARE_SIZE-5,SQUARE_SIZE-5);
                }
            }
        }
    }

    public void initBoard(java.util.List<Point> startingPoints)
    {
        for(Point point : startingPoints)
        {
            addCell(point.getX(),point.getY());
        }
    }

    public void setRunning(boolean running)
    {
        this.running = running;
    }

    public void clearBoard()
    {
        for(int i = 0; i < sizeX; i++)
        {
            for(int j = 0; j < sizeY; j++)
            {
                board.getCellAt(i,j).setCurrentState(CellState.DEAD);
            }
        }
        updateBoard();
    }

    public void updateBoard()
    {
        this.revalidate();
        this.repaint();
    }

    @Override
    public void run()
    {
        while(true)
        {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            if(running && containsLiveCell(board))
            {

                board.nextRound();
                updateBoard();
            }
        }
    }

    private void addCell(int x, int y)
    {
        board.getCellAt(x,y).setCurrentState(CellState.ALIVE);
    }

    private static boolean containsLiveCell(Board board)
    {
        for(int i = 0; i<10; i++)
            for (int j = 0; j < 10; j++)
                if(CellState.ALIVE.equals(board.getCellAt(i, j).getCurrentState()))
                    return true;
        return false;
    }

    public class MouseClickListener extends MouseAdapter
    {
        public void mouseClicked(MouseEvent me)
        {
            if(running)
            {
                return;
            }
            Cell cellAt = board.getCellAt(((me.getX() - PADDING) / SQUARE_SIZE), ((me.getY() - PADDING) / SQUARE_SIZE));
            if(CellState.ALIVE == cellAt.getCurrentState())
            {
                cellAt.setCurrentState(CellState.DEAD);
            }
            else
            {
                cellAt.setCurrentState(CellState.ALIVE);
            }
            me.getComponent().revalidate();
            me.getComponent().repaint();
        }
    }
}
