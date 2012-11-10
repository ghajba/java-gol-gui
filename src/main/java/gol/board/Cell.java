package gol.board;

import java.util.HashSet;
import java.util.Set;

import gol.rule.CellState;
import gol.rule.RuleEngine;

/**
 * @author Klaus Bayrhammer
 */
public class Cell
{
    private CellState currentState = CellState.DEAD;
    private CellState nextState;
    private final Point coordinates;

    private Set<Cell> neighbors = new HashSet<Cell>();
    private RuleEngine ruleEngine = new RuleEngine();

    public Cell(int x, int y)
    {
        coordinates = new Point(x, y);
    }

    public CellState getCurrentState()
    {
        return currentState;
    }

    public void setCurrentState(CellState currentState)
    {
        this.currentState = currentState;
    }

    public boolean isAt(int x, int y)
    {
        return coordinates.equals(new Point(x, y));
    }

    public void addRelevantNeighbors(Set<Cell> possibleNeighbors)
    {
        for (Cell possibleNeighbor : possibleNeighbors)
        {
            if (possibleNeighbor.isRelevantNeighbor(coordinates))
            {
                neighbors.add(possibleNeighbor);
            }
        }
    }

    private boolean isRelevantNeighbor(Point p)
    {
        int distance = p.getDistanceFrom(coordinates);
        return distance == 1;
    }

    public int getNumberOfAliveNeighbors()
    {
        int aliveNeighbors = 0;
        for (Cell neighbor : neighbors)
        {
            if (neighbor.getCurrentState() == CellState.ALIVE)
            {
                aliveNeighbors++;
            }
        }
        return aliveNeighbors;
    }

    public void prepareIterate()
    {
        nextState = ruleEngine.nextState(currentState, getNumberOfAliveNeighbors());
    }

    public void iterate()
    {
        currentState = nextState;
    }
}
