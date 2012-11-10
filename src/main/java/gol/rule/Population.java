package gol.rule;
/**
 * @author Klaus Bayrhammer
 */
public enum Population
{
    SUFFICIENT, OPTIMAL, UNLIVABLE;

    public static Population forNumberOfNeighbors(int numberOfNeighbors)
    {
        if (numberOfNeighbors == 2)
        {
            return SUFFICIENT;
        } else if (numberOfNeighbors == 3)
        {
            return OPTIMAL;
        }
        return UNLIVABLE;
    }
}
