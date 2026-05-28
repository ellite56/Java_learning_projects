package school.domain.model;

public class Move {
    /**
     Данный класс Описывает один конкретный
     ход используется как раз-таки для возврата лучшего хода
     */
    private final int row;
    private final int col;

    private static boolean checkValidations(int row,int col){
        if ((row<0 || row>2) || (col<0 || col>2)){
            return false;
        }
        return true;
    }

    public Move(int row,int col){
        if (checkValidations(row,col)){
            this.row=row;
            this.col=col;
        }
        else {
            throw new IllegalArgumentException("Incorrect position");
        }
    }

    public int getCol() {
        return col;
    }

    public int getRow() {
        return row;
    }
}
