package gol.board;

import java.util.HashSet;
import java.util.Set;

/**
 * @author Klaus Bayrhammer
 */
public class Board
{
    private static final Cell CELL_OUTSIDE_BOARD = new Cell(0, 0);

    private Set<Cell> board = new HashSet<Cell>();

    public Board(int sizeX, int sizeY)
    {
        createEmptyBoard(sizeX, sizeY);
        determineNeighbors();
    }

    private void createEmptyBoard(int sizeX, int sizeY)
    {
        for (int x = 0; x < sizeX; x++)
        {
            for (int y = 0; y < sizeY; y++)
            {
                board.add(new Cell(x, y));
            }
        }
    }

    private void determineNeighbors()
    {
        for (Cell cell : board)
        {
            cell.addRelevantNeighbors(board);
        }
    }

    public Cell getCellAt(int x, int y)
    {
        for (Cell cell : board)
        {
            if (cell.isAt(x, y))
            {
                return cell;
            }
        }
        return CELL_OUTSIDE_BOARD;
    }

    public void nextRound()
    {
        prepareCellsForNextRound();
        iterateCellsToNextRound();
    }

    private void iterateCellsToNextRound()
    {
        for (Cell cell : board)
        {
            cell.iterate();
        }
    }

    private void prepareCellsForNextRound()
    {
        for (Cell cell : board)
        {
            cell.prepareIterate();
        }
    }
}