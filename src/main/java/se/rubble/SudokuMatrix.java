package se.rubble;

import java.util.Arrays;

public class SudokuMatrix {

    private Integer[][] matrix = {
            {null,   6,   9,null,null,   3,null,   1,null},
            {null,null,   2,   6,   5,   4,null,null,null},
            {null,null,   3,null,null,null,   6,   2,   8},
            {   1,null,null,   2,null,null,   5,   9,null},
            {null,   7,   5,null,   3,   9,null,   8,null},
            {null,   2,null,null,null,   5,null,null,   4},
            {null,null,   6,null,null,null,null,null,   1},
            {null,   4,null,   5,   6,null,   7,null,   9},
            {null,   9,   7,   1,   4,null,null,null,null},
    };

    @Override
    public String toString() {
        StringBuffer stringBuffer = new StringBuffer();
        for (int i=0;i<9;i++){
            stringBuffer.append(Arrays.toString(matrix[i]));
            stringBuffer.append("\n");
        }
        return stringBuffer.toString();
    }

    public void set(int posx, int posy, int val) {
        matrix[posx][posy] = val;
    }

//    public boolean rowContains(int row, Integer val){
//        return Arrays.asList(matrix[row]).contains(val);
//    }

    public SudokuMatrixRow row(int i) {
        inclusiveBetween(0,9,i,String.format("Column value need to be between 0 and 9: %d",i));
        return new SudokuMatrixRow(matrix[i]);
    }

    public SubMatrix subMatrix(int x, int y) {
        inclusiveBetween(0,3,x,String.format("Splice value need to be between 0 and 3: %d",x));
        inclusiveBetween(0,3,y,String.format("Splice value need to be between 0 and 3: %d",y));

        Integer[][] subMatrix = new Integer[3][3];

        int i = 0;
        for (int row = x*3; row < x*3 + 3; row++){
            int j = 0;
            for (int col = x*3; col < x*3+3; col++){
                subMatrix[i][j++] = matrix[row][col];
            }
            i++;
        }
        return new SubMatrix(subMatrix);
    }

    private void inclusiveBetween(int i, int i1, int x, String s) {
        if(x<i || x >i1) throw new IllegalArgumentException(s);
    }

    public SudokuMatrixColumn col(int i) {
        Integer[] col = new Integer[9];
        for(int row = 0; row < 9; row ++){
            col[row] = matrix[row][i];
        }
        return new SudokuMatrixColumn(col);
    }

    public Integer get(int row, int col) {
        return matrix[row][col];
    }

    public class SudokuMatrixRow {
        private final Integer[] row;

        public SudokuMatrixRow(Integer[] row) {
            this.row = row;
        }

        public boolean contains(int i) {
            return Arrays.asList(row).contains(i);
        }
    }

    public class SubMatrix {
        private final Integer[][] matrix;

        public SubMatrix(Integer[][] subMatrix) {
            this.matrix = subMatrix;
        }

        @Override
        public String toString() {
            StringBuffer stringBuffer = new StringBuffer();
            for (int i = 0; i <3; i++){
                stringBuffer.append(Arrays.toString(matrix[i])).append("\n");
            }
            return stringBuffer.toString();
        }

        public boolean contains(Object o) {
            boolean result = false;
            for(int row = 0; row <3; row ++){
                result = result || Arrays.asList(matrix[row]).contains(o);
            }
            return result;
        }
    }

    public class SudokuMatrixColumn {
        private final Integer[] column;

        public SudokuMatrixColumn(Integer[] col) {
            this.column = col;
        }

        public boolean contains(int i) {
            return Arrays.asList(column).contains(i);
        }
    }
}
