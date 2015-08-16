package se.rubble;


import java.util.ArrayList;
import java.util.Arrays;

public class SudokuMatrix {
    private MatrixCell[][] cells = new MatrixCell[9][9];

    public SudokuMatrix(Integer[][] integers) {
        for(int row = 0; row < integers.length; row++){
            for (int col = 0; col < integers[row].length; col++){
                ArrayList<Integer> possibleValues = new ArrayList<>();
                if(integers[row][col] != null){
                    possibleValues.add(integers[row][col]);
                }else {
                    for (int val = 1; val < 10; val++) {
                        if (!new MatrixRow(integers[row]).contains(val)
                                && !new MatrixColumn(integers,col).contains(val)
                                && !new MatrixBox(integers,row / 3, col / 3).contains(val)) {
                            possibleValues.add(val);
                        }
                    }
                }
                cells[row][col] = new MatrixCell(possibleValues);
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 9; i++){
            sb.append(Arrays.toString(cells[i]));
            sb.append("\n");
        }
        return sb.toString();
    }

    public int[][] asIntMatrix() {
        int[][] intMatrix = new int[9][9];

        for(int row = 0; row < 9; row++){
            for(int col = 0; col < 9; col++){
                intMatrix[row][col] = cells[row][col].activeValue;
            }
        }
        return intMatrix;
    }

    public void solve() {

    }

    public class MatrixCell {

        Integer activeValue;
        ArrayList<Integer> possibleValues = new ArrayList<>();

        public MatrixCell(ArrayList<Integer> possibleValues) {
            this.activeValue = possibleValues.get(0);
            possibleValues.remove(0);
            this.possibleValues = possibleValues;
        }

        @Override
        public String toString() {
            return "" + activeValue + possibleValues;
        }

        public void addPossible(int val) {
            possibleValues.add(val);
        }

        public void setActiveValue(Integer integer) {
            if(possibleValues.contains(integer)) {
                possibleValues.remove(integer);
            }
            activeValue = integer;
        }
    }

    private class MatrixRow{
        private final Integer[] integers;

        public MatrixRow(Integer[] integer) {
            this.integers = integer;
        }

        public boolean contains(int val) {
            return Arrays.asList(integers).contains(val);
        }
    }

    private class MatrixColumn{
        private final Integer[] colValues = new Integer[9];

        public MatrixColumn(Integer[][] integers, int col) {
            for(int row = 0; row < 9; row++){
                this.colValues[row] = integers[row][col];
            }
        }

        public boolean contains(int val) {
            return Arrays.asList(colValues).contains(val);
        }
    }
    private class MatrixBox{
        private final Integer[][] matrix = new Integer[3][3];

        public MatrixBox(Integer[][] integers, int x, int y) {
            int i = 0;
            for (int row = x*3; row < x*3 + 3; row++){
                int j = 0;
                for (int col = y*3; col < y*3+3; col++){
                    matrix[i][j++] = integers[row][col];
                }
                i++;
            }
        }

        public boolean contains(int val) {
            boolean result = false;
            for(int row = 0; row <3; row ++){
                result = result || Arrays.asList(matrix[row]).contains(val);
            }
            return result;
        }
    }
}
