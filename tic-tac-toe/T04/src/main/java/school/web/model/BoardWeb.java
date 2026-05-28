package school.web.model;

public class BoardWeb {
    private static  int row = 3;
    private static  int col = 3;
    private  int[][] matrix;

    public BoardWeb() {
    }

    public BoardWeb(int[][] matrix) {
        if (checkMatrix(matrix)) {
            this.matrix = copyMatrix(matrix);
        } else {
            throw new IllegalArgumentException("Incorrect matrix");
        }
    }

    public int[][] getMatrix() {
        int[][] tempMatrix = new int[row][col];
        tempMatrix = copyMatrix(matrix);
        return tempMatrix;
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
                if (matrix[i][j] != 0 && matrix[i][j] != 1 && matrix[i][j] != 2) {
                    return false;
                }
            }
        }
        return true;
    }

    private int[][] copyMatrix(int[][] matrix) {
        int[][] tempMatrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                tempMatrix[i][j] = matrix[i][j];
            }
        }
        return tempMatrix;
    }

    public void setMatrix(int[][] matrix){
        if (checkMatrix(matrix)){
            int [][] tempMatrix = copyMatrix(matrix);
            this.matrix=tempMatrix;
        }
        else {
            throw new IllegalArgumentException("Incorrect matrix");
        }
    }
}
