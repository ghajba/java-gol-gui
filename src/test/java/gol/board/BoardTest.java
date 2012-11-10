package gol.board;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import gol.rule.CellState;

/**
 * @author Klaus Bayrhammer
 */
public class BoardTest
{
    @Test
    public void checkPointOnEmptyBoard()
    {
        Board board = new Board(1, 1);

        Cell cell = board.getCellAt(0, 0);

        assertEquals(CellState.DEAD, cell.getCurrentState());
    }

    @Test
    public void checkPointOutsideBoard()
    {
        Board board = new Board(1, 1);

        Cell cell = board.getCellAt(1, 0);

        assertEquals(CellState.DEAD, cell.getCurrentState());
    }

    @Test
    public void setCellStateAlive()
    {
        Board board = new Board(1, 1);
        board.getCellAt(0, 0).setCurrentState(CellState.ALIVE);

        Cell cell = board.getCellAt(0, 0);

        assertEquals(CellState.ALIVE, cell.getCurrentState());
    }

    @Test
    public void setTwoCellStates()
    {
        Board board = new Board(1, 2);
        board.getCellAt(0, 0).setCurrentState(CellState.ALIVE);

        Cell cell = board.getCellAt(0, 1);

        assertEquals(CellState.DEAD, cell.getCurrentState());
    }

    @Test
    public void getZeroAliveNeighborsOnEmptyBoard()
    {
        Board board = new Board(1, 1);
        assertEquals(0, board.getCellAt(0, 0).getNumberOfAliveNeighbors());
    }

    @Test
    public void getOneAliveNeighborsOnBoard()
    {
        Board board = new Board(3, 3);
        board.getCellAt(0, 0).setCurrentState(CellState.ALIVE);
        assertEquals(1, board.getCellAt(1, 1).getNumberOfAliveNeighbors());
    }

    @Test
    public void iterateBoard()
    {
        Board board = new Board(2, 2);
        board.getCellAt(0, 0).setCurrentState(CellState.ALIVE);
        board.getCellAt(1, 0).setCurrentState(CellState.ALIVE);
        board.getCellAt(0, 1).setCurrentState(CellState.ALIVE);

        board.nextRound();

        assertEquals(CellState.ALIVE, board.getCellAt(1, 1).getCurrentState());
    }
}
