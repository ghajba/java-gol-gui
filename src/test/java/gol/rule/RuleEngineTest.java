package gol.rule;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 * @author Klaus Bayrhammer
 */
public class RuleEngineTest
{

    private RuleEngine ruleEngine;

    @Before
    public void setUp()
    {
        ruleEngine = new RuleEngine();
    }

    @Test
    public void aliveCellWithOneNeighbor()
    {
        CellState nextState = ruleEngine.nextState(CellState.ALIVE, 1);
        assertEquals(CellState.DEAD, nextState);
    }

    @Test
    public void aliveCellWithTwoNeighbors()
    {
        CellState nextState = ruleEngine.nextState(CellState.ALIVE, 2);
        assertEquals(CellState.ALIVE, nextState);
    }

    @Test
    public void deadCellWithTwoNeighbors()
    {
        CellState nextState = ruleEngine.nextState(CellState.DEAD, 2);
        assertEquals(CellState.DEAD, nextState);
    }

    @Test
    public void aliveCellWithThreeNeighbors()
    {
        CellState nextState = ruleEngine.nextState(CellState.ALIVE, 3);
        assertEquals(CellState.ALIVE, nextState);
    }

    @Test
    public void deadCellWithThreeNeighbors()
    {
        CellState nextState = ruleEngine.nextState(CellState.DEAD, 3);
        assertEquals(CellState.ALIVE, nextState);
    }
}
