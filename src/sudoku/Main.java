package sudoku;

public class Main {
    public static void main(String[] args) {

        final int[][] sudoku = {
                {8,6,0,0,2,0,0,0,0},
                {0,0,0,7,0,0,0,5,9},
                {0,0,0,0,0,0,0,0,0},
                {0,0,0,0,6,0,8,0,0},
                {0,4,0,0,0,0,0,0,0},
                {0,0,5,3,0,0,0,0,7},
                {0,0,0,0,0,0,0,0,0},
                {0,2,0,0,0,0,6,0,0},
                {0,0,7,5,0,9,0,0,0}
        };

        System.out.println(Sudoku.asString(sudoku));

        if (Sudoku.solve(sudoku)) {

            System.out.println(Sudoku.asString(sudoku));
        }

    }
}
