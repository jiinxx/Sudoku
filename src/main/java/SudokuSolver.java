import se.rubble.SudokuMatrix;

public class SudokuSolver {

    private final SudokuMatrix matrix;



    public SudokuSolver() {
        Integer[][] integers = {
                {null, 6, 9, null, null, 3, null, 1, null},
                {null, null, 2, 6, 5, 4, null, null, null},
                {null, null, 3, null, null, null, 6, 2, 8},
                {1, null, null, 2, null, null, 5, 9, null},
                {null, 7, 5, null, 3, 9, null, 8, null},
                {null, 2, null, null, null, 5, null, null, 4},
                {null, null, 6, null, null, null, null, null, 1},
                {null, 4, null, 5, 6, null, 7, null, 9},
                {null, 9, 7, 1, 4, null, null, null, null},
        };

        this.matrix = new SudokuMatrix(integers);
    }

    public static void main(String[] args) {
        new SudokuSolver().solve();
    }

    private void solve() {

        System.out.println(matrix);
       matrix.solve();

        //System.out.println(matrix.row(0).contains(5));

        System.out.println(matrix.asIntMatrix());
    }
}
