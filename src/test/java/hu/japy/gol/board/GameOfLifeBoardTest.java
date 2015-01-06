package hu.japy.gol.board;

import static org.testng.Assert.assertFalse;
import static org.testng.Assert.assertTrue;

import org.testng.annotations.Test;

public class GameOfLifeBoardTest {

    @Test
    public void checkEmptyBoard() {
        assertFalse(new GameOfLifeBoard(1, 1).getCellAt(0, 0));
    }

    @Test
    public void checkPointOutsideBoard() {
        assertFalse(new GameOfLifeBoard(1, 1).getCellAt(1, 0));
    }

    @Test
    public void checkOneCellBoard() {
        final GameOfLifeBoard board = new GameOfLifeBoard(1, 1);
        board.changeCellAt(0, 0);
        assertTrue(board.getCellAt(0, 0));
        board.changeCellAt(0, 0);
        assertFalse(board.getCellAt(0, 0));
    }
}
