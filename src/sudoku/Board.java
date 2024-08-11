package sudoku;

import java.util.Arrays;


final class Board {

    static final int EMPTY_CELL = 0;
    static final int GRID_SIZE = 9;
    static final int REGION_SIZE = 3;
    static final String ERROR_MSG_SIZE = "Please provide a "+ GRID_SIZE +"x" + GRID_SIZE + " array for Input";

    private final int[][] board;
    private char[] line;


    Board(int[][] init) {

        board = init;
    }

    int[] getRow(int row) {
        return board[row];
    }


    int[] getColumn(int col) {
        final int[] columnView = new int[GRID_SIZE];
        for(int a = 0; a < GRID_SIZE; a++) {
            columnView[a] = board[a][col];
        }
        return columnView;
    }


    int[] getRegion(int row, int col) {
        final int[] regionView = new int[GRID_SIZE];
        final int rowBase = row - (row % REGION_SIZE);
        final int colBase = col - (col % REGION_SIZE);
        int counter = 0;
        // REGION_SIZE (3) Rows/Columns from rowBase/ColumnBase
        for (int r = rowBase; r < REGION_SIZE + rowBase; r++) {
            for (int c = colBase ; c < REGION_SIZE + colBase; c++) {
                regionView[counter++] = board[r][c];
            }
        }
        return regionView;
    }



    int getCell(int row, int col) {
        return board[row][col];
    }


    void setCell(int row, int col, int val) {
        board[row][col] = val;
    }


    @Override
    public String toString() {
        // Exact size of the generated string for the buffer (values + spacers)
        final int size = (GRID_SIZE*2+1+((REGION_SIZE+1)*2))*(GRID_SIZE+REGION_SIZE+1);
        final String verticalSpace = " |";
        // A StringBuilder is absolutely needed here
        // use of String concatenation (+) would have really bad performance
        final StringBuilder buffer = new StringBuilder(size);
        // Row/Column traversal
        for (int a=0; a < GRID_SIZE; a++) {
            int[] row = board[a];
            if (a % REGION_SIZE == 0) {
                appendLine(buffer);
            }
            for (int b=0; b < GRID_SIZE; b++) {
                int value = row[b];
                if (b % REGION_SIZE == 0) {
                    buffer.append(verticalSpace);
                }
                appendValue(buffer, value);
            }
            buffer.append(verticalSpace);
            buffer.append('\n');
        }
        appendLine(buffer);
        return buffer.toString();
    }


    private void appendValue(StringBuilder buffer, int value) {
        buffer.append(' ');
        if (value != EMPTY_CELL) {
            buffer.append(value);
        } else {
            buffer.append('_');
        }
    }

    private void appendLine(StringBuilder buffer) {
        // Only create the line once
        // Thread safe because the Sudoku class create one new Board for every toString() method call
        if (line == null) {
            line = new char[GRID_SIZE*2+((REGION_SIZE+1)*2)];
            Arrays.fill(line, '-');
            //first char as space
            line[0] = ' ';
        }
        buffer.append(line);
        buffer.append('\n');
    }
}
