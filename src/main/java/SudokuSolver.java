import se.rubble.SudokuMatrix;

public class SudokuSolver {
    public static void main(String[] args) {
        new SudokuSolver().solve();
    }

    private void solve() {
        SudokuMatrix matrix = new SudokuMatrix();

        System.out.println(matrix);

        System.out.println(matrix.row(0).contains(5));
        System.out.println(matrix.col(1).contains(1));
        System.out.println(matrix.subMatrix(0, 0));
        System.out.println(matrix.subMatrix(0, 0).contains(9));

//        for(int val = 1; val < 10; val++){
//            for(int row = 0; row < 9; row++){
//                for(int col = 0; col < 9; col++){
//                    if(!matrix.row(row).contains(val)
//                            && !matrix.col(col).contains(val)
//                            && !matrix.subMatrix(row%3,col%3).contains(val)
//                            && matrix.get(row,col) == null){
//                        matrix.set(row,col,val);
//                    }
//                }
//            }
//        }

        for(int row = 0; row <9; row++){
            for(int col = 0; col < 9; col++){
                if(matrix.get(row, col) == null){
                    for(int val = 1; val < 10; val++){
                        if(!matrix.row(row).contains(val)
                            && !matrix.col(col).contains(val)
                            && !matrix.subMatrix(row%3,col%3).contains(val)){
                            matrix.set(row,col,val);
                            break;
                        }else {
                            if (val == 9) System.out.println(String.format("No valid options for(row,col): %d,%d",row,col));
                        }
                    }
                }
            }
        }

        System.out.println(matrix);
    }
}
