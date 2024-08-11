package sudoku;

import static sudoku.Board.EMPTY_CELL;
import static sudoku.Board.GRID_SIZE;


class Solver {

    private final Board board;


    Solver(int[][] input) {
        this.board = new Board(input);
    }

    boolean solve() {
        return solve(0,0);
    }


    Board getBoard() {
        return board;
    }


    private boolean solve(int row, int col) {
        if (row == GRID_SIZE) {
            row = 0;
            if (++col == GRID_SIZE) {
                return true;
            }
        }
        if (board.getCell(row, col) != EMPTY_CELL) {
            return solve(row+1,col);
        }
        // For all possible values
        for(int val = 1; val <= GRID_SIZE; val++) {
            if (isMoveOK(row, col, val)) {
                board.setCell(row, col, val);
                if (solve(row+1, col)) {
                    return true;
                }
            }
        }
        // Reset the cell to EMPTY to do recursive backtrack and try again
        board.setCell(row, col, EMPTY_CELL);
        return false;
    }


    private boolean isMoveOK(int row, int col, int val) {
        return ! (  arrayContains(board.getRow(row), val)
                || arrayContains(board.getColumn(col), val)
                || arrayContains(board.getRegion(row, col), val));
    }

    private boolean arrayContains(int[] array, int val) {
        for (int arrayVal : array) {
            if (arrayVal == val) {
                // return true and stop the iteration
                return true;
            }
        }
        return false;
    }
}
