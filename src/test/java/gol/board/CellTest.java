package gol.board;

import static org.junit.Assert.assertEquals;

import java.util.Collections;

import org.junit.Test;

import gol.rule.CellState;

/**
 * @author Klaus Bayrhammer
 */
public class CellTest
{
    @Test
    public void isRelevantNeighbor()
    {
        Cell cell = new Cell(1, 1);

        Cell unrelevantNeighbor = new Cell(1, 3);
        unrelevantNeighbor.setCurrentState(CellState.ALIVE);

        cell.addRelevantNeighbors(Collections.singleton(unrelevantNeighbor));

        assertEquals(0, cell.getNumberOfAliveNeighbors());
    }

    @Test
    public void prepareIteration()
    {
        Cell cell = new Cell(1, 1);
        cell.setCurrentState(CellState.ALIVE);
        cell.prepareIterate();
        cell.iterate();
        assertEquals(CellState.DEAD, cell.getCurrentState());
    }
}
