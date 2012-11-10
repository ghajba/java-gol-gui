package gol.rule;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

/**
 * @author Klaus Bayrhammer
 */
public class PopulationTest
{
    @Test
    public void createUnlivableLowPopulation()
    {
        Population pop = Population.forNumberOfNeighbors(1);
        assertEquals(Population.UNLIVABLE, pop);
    }

    @Test
    public void createSufficientPopulation()
    {
        Population pop = Population.forNumberOfNeighbors(2);
        assertEquals(Population.SUFFICIENT, pop);
    }

    @Test
    public void createOptimalPopulation()
    {
        Population pop = Population.forNumberOfNeighbors(3);
        assertEquals(Population.OPTIMAL, pop);
    }
}
