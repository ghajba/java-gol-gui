package gol.rule;

/**
 * @author Klaus Bayrhammer
 */
public class RuleEngine
{
    public CellState nextState(CellState alive, int numberOfNeigbors)
    {
        Population population = Population.forNumberOfNeighbors(numberOfNeigbors);
        return alive.nextState(population);
    }
}
