package school.domain.model;

public class Board {
    /**
     * 0- в матрице- пустая клетка
     * 1- ход человека
     * 2- ход компьютера
     **/
    private int[][] matrix;
    public static final int empty = 0;
    public static final int player = 1;
    public static final int computer = 2;
    public static final int row = 3;
    public static final int col = 3;

    public Board() {
        matrix = new int[row][col];
    }

    public Board(int[][] matrix) {
        if (checkMatrix(matrix)) {
            int[][] tmp_matrix = copyMatrix(matrix);
            this.matrix = tmp_matrix;
        } else {
            throw new IllegalArgumentException("Invalid board matrix");
        }
    }

    private boolean checkMatrix(int[][] matrix) {
        if (matrix == null) {
            return false;
        }
        if (matrix.length != row) {
            return false;
        }
        for (int i = 0; i < matrix.length; i++) {
            if (matrix[i] != null) {
                if (col != matrix[i].length) {
                    return false;
                }
            } else {
                return false;
            }
        }
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] != empty && matrix[i][j] != player && matrix[i][j] != computer) {
                    return false;
                }
            }
        }

        return true;
    }

    private static int[][] copyMatrix(int[][] matrix) {
        int[][] tmp_matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                tmp_matrix[i][j] = matrix[i][j];
            }
        }
        return tmp_matrix;
    }


    public int[][] getMatrix() {
        int[][] tmp_matrix = copyMatrix(matrix);
        return tmp_matrix;
    }

    public void checkPosition(int row, int col) {
        if ((row < 0 || row > 2) || (col < 0 || col > 2)) {
            throw new IllegalArgumentException(("Invalid position"));
        }
    }

    public int getCellState(int row, int col) {
        int state;
        checkPosition(row, col);
        state = matrix[row][col];
        return state;
    }

    public boolean isEmptyCell(int row, int col) {
        int state = getCellState(row, col);
        if (state == empty) {
            return true;
        } else {
            return false;
        }
    }

    public void setCellState(int row,int col,int value){
        checkPosition(row,col);
        if (value!=empty && value!=player && value!=computer){
            throw new IllegalArgumentException("Incorrect state");
        }
        matrix[row][col]=value;
    }
}
