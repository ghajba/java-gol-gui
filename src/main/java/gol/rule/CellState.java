package gol.rule;

/**
 * @author Klaus Bayrhammer
 */
public enum CellState
{
    DEAD {
        @Override
        public CellState nextState(Population population)
        {
            return population == Population.OPTIMAL ? CellState.ALIVE : CellState.DEAD;
        }
    }, ALIVE
    {
        @Override
        CellState nextState(Population population)
        {
            return population == Population.UNLIVABLE ? CellState.DEAD : CellState.ALIVE;
        }
    };

    abstract CellState nextState(Population numberOfNeighbors);
}
