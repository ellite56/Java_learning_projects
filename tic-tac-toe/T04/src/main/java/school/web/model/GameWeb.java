package school.web.model;

import school.domain.model.GameStatus;

import java.util.UUID;

public class GameWeb {
    private UUID id;
    private BoardWeb board;
    private GameStatus status;

    public GameWeb() {
    }

    private boolean checkNull(UUID id, BoardWeb board) {
        if (id == null || board == null) {
            return false;
        }
        return true;
    }

    public GameWeb(UUID id, BoardWeb board, GameStatus status) {
        if (checkNull(id, board)) {
            this.id = id;
            this.board = board;
            this.status = status;
        } else {
            throw new IllegalArgumentException("Incorrect. Id or board equal null");
        }
    }

    public UUID getId() {
        return id;
    }

    public BoardWeb getBoard() {
        return board;
    }

    public GameStatus getStatus() {
        return status;
    }

    public void setId(UUID id) {
        if (id != null) {
            this.id = id;
        } else {
            throw new IllegalArgumentException("Incorrect Id");
        }
    }

    public void setBoard(BoardWeb boardWeb) {
        if (boardWeb != null) {
            this.board = boardWeb;
        } else {
            throw new IllegalArgumentException("Incorrect. BoardWeb is null");
        }
    }

    public void setStatus(GameStatus status){
        this.status=status;
    }
}
