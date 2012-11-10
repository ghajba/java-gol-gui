package gol.board;

public class Point
{
    final int x;
    final int y;

    public Point(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getDistanceFrom(Point p1)
    {
        int xDistance = x - p1.x;
        int yDistance = y - p1.y;

        return Math.max(Math.abs(xDistance), Math.abs(yDistance));
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }

        Point point = (Point) o;

        return x == point.x && y == point.y;

    }

    @Override
    public int hashCode()
    {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}