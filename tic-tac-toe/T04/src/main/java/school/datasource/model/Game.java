package school.datasource.model;

import java.util.UUID;

public class Game {
    private final UUID id;
    private final Board board;

    private boolean checkNull(UUID id,Board board){
        if (id==null || board==null){
            return false;
        }
        return true;
    }

    public Game(UUID id,Board board){
        if(checkNull(id,board)){
            this.id=id;
            this.board=board;
        }
        else {
            throw new IllegalArgumentException("Incorrect. Id or board equal null");
        }
    }

    public UUID getId() {
        return id;
    }

    public Board getBoard() {
        return board;
    }
}
