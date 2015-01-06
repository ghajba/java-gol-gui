package hu.japy.gol.board;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class GameOfLifeBoardTest {

    @Test
    public void testEmptyBoard() {
        assertFalse(new GameOfLifeBoard(1, 1).getCellAt(0, 0));
    }

    @Test
    public void testPointOutsideBoard() {
        assertFalse(new GameOfLifeBoard(1, 1).getCellAt(1, 0));
        assertFalse(new GameOfLifeBoard(1, 1).getCellAt(0, 1));
        assertFalse(new GameOfLifeBoard(1, 1).getCellAt(-1, 0));
        assertFalse(new GameOfLifeBoard(1, 1).getCellAt(0, -1));
    }

    @Test
    public void testChangesOutsideTheBoard() {
        final GameOfLifeBoard board = new GameOfLifeBoard(1, 1);
        board.changeCellAt(1, 0);
        board.changeCellAt(0, 1);
        board.changeCellAt(0, -1);
        board.changeCellAt(-1, 0);
        assertFalse(board.getCellAt(0, 0));
    }

    @Test
    public void testOneCellBoard() {
        final GameOfLifeBoard board = new GameOfLifeBoard(1, 1);
        board.changeCellAt(0, 0);
        assertTrue(board.getCellAt(0, 0));
        board.changeCellAt(0, 0);
        assertFalse(board.getCellAt(0, 0));
    }

    @Test
    public void testResetBoard() {
        final GameOfLifeBoard board = new GameOfLifeBoard(1, 1);
        board.changeCellAt(0, 0);
        assertTrue(board.containsLiveCell());
        board.resetBoard();
        assertFalse(board.containsLiveCell());
    }

    @Test
    public void testStaticCube() {

        final GameOfLifeBoard board = new GameOfLifeBoard(2, 2);
        board.changeCellAt(0, 0);
        board.changeCellAt(0, 1);
        board.changeCellAt(1, 0);
        board.changeCellAt(1, 1);
        board.nextRound();
        assertTrue(board.getCellAt(0, 0));
        assertTrue(board.getCellAt(0, 1));
        assertTrue(board.getCellAt(1, 0));
        assertTrue(board.getCellAt(1, 1));
    }

    @Test
    public void testPopulationGrowth() {
        final GameOfLifeBoard board = new GameOfLifeBoard(2, 2);
        board.changeCellAt(0, 0);
        board.changeCellAt(0, 1);
        board.changeCellAt(1, 0);
        board.nextRound();
        assertTrue(board.getCellAt(0, 0));
        assertTrue(board.getCellAt(0, 1));
        assertTrue(board.getCellAt(1, 0));
        assertTrue(board.getCellAt(1, 1));
    }

    @Test
    public void testDynamicLine() {
        final GameOfLifeBoard board = new GameOfLifeBoard(3, 3);
        board.changeCellAt(1, 0);
        board.changeCellAt(1, 1);
        board.changeCellAt(1, 2);
        board.nextRound();
        assertTrue(board.getCellAt(0, 1));
        assertTrue(board.getCellAt(1, 1));
        assertTrue(board.getCellAt(2, 1));
        board.nextRound();
        assertTrue(board.getCellAt(1, 0));
        assertTrue(board.getCellAt(1, 1));
        assertTrue(board.getCellAt(1, 2));
    }
}
