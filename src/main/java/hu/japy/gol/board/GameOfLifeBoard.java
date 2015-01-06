package hu.japy.gol.board;

/**
 * Representing a GameOfLife board
 *
 * @author GHajba
 *
 */
public class GameOfLifeBoard {

    private final int columns;
    private final int rows;

    private int[][] board;

    /**
     * @param width
     *            the cell amount of one line in the boards
     * @param height
     *            the cell amount of one column in the board
     */
    public GameOfLifeBoard(int width, int height) {
        this.columns = width;
        this.rows = height;
        this.board = createEmptyBoard();
    }

    public void resetBoard() {
        this.board = createEmptyBoard();
    }

    private int[][] createEmptyBoard() {
        return new int[this.rows][this.columns];
    }

    public boolean getCellAt(int row, int column) {
        if (row < 0 || row >= this.rows || column < 0 || column >= this.columns) {
            return false;
        }
        return this.board[row][column] == 1;
    }

    public void changeCellAt(int row, int column) {
        if (row >= 0 && row < this.rows && column >= 0 && column < this.columns) {
            this.board[row][column] = (this.board[row][column] + 1) % 2;
        }
    }

    public boolean containsLiveCell() {
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                if (this.board[i][j] == 1) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Calculates the population for the next round.
     */
    public void nextRound() {

        final int[][] nextBoard = createEmptyBoard();
        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < this.columns; j++) {
                int neighbours = 0;
                if (i == 0) {
                    neighbours += getBottom(i, j);
                    if (j != 0) {
                        neighbours += getLeft(i, j) + getBottomLeft(i, j);
                    }
                    if (j != this.columns - 1) {
                        neighbours += getRight(i, j) + getBottomRight(i, j);
                    }
                } else if (j == 0) {
                    if (i != this.rows - 1) {
                        neighbours += getBottom(i, j) + getBottomRight(i, j);
                    }
                    neighbours += getTop(i, j) + getTopRight(i, j) + getRight(i, j);

                } else if (j == this.columns - 1) {
                    if (i != this.rows - 1) {
                        neighbours += getBottom(i, j) + getBottomLeft(i, j);
                    }
                    neighbours += getTop(i, j) + getTopLeft(i, j) + getLeft(i, j);

                } else {
                    if (i != this.rows - 1) {
                        neighbours += getBottom(i, j) + getBottomRight(i, j) + getBottomLeft(i, j);
                    }
                    neighbours += getTop(i, j) + getTopRight(i, j) + getTopLeft(i, j) + getLeft(i, j) + getRight(i, j);
                }
                if ((getCellAt(i, j) && (neighbours == 2 || neighbours == 3)) || (!getCellAt(i, j) && neighbours == 3)) {
                    nextBoard[i][j] = 1;
                } else {
                    nextBoard[i][j] = 0;
                }
            }
        }

        this.board = nextBoard;
    }

    private int getLeft(int i, int j) {
        return this.board[i][j - 1];
    }

    private int getRight(int i, int j) {
        return this.board[i][j + 1];
    }

    private int getTop(int i, int j) {
        return this.board[i - 1][j];
    }

    private int getBottom(int i, int j) {
        return this.board[i + 1][j];
    }

    private int getTopLeft(int i, int j) {
        return this.board[i - 1][j - 1];
    }

    private int getTopRight(int i, int j) {
        return this.board[i - 1][j + 1];
    }

    private int getBottomLeft(int i, int j) {
        return this.board[i + 1][j - 1];
    }

    private int getBottomRight(int i, int j) {
        return this.board[i + 1][j + 1];
    }
}
