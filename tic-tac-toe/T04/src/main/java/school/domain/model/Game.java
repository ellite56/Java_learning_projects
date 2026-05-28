package school.domain.model;

import java.util.UUID;

public class Game {
    private final UUID id;
    private Board board;

    public Game(){
        this.id=UUID.randomUUID();
        this.board= new Board();
    }

    public Game(UUID id,Board board){
        if(id !=null && board != null){
            this.id=id;
            this.board=board;
        }
        else {
            throw new IllegalArgumentException("A game cannot be created with a null id or a null board.");
        }
    }

    public Board getBoard() {
        return board;
    }

    public UUID getId() {
        return id;
    }

}
