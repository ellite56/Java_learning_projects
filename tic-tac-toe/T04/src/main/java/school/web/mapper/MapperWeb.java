package school.web.mapper;

import school.domain.model.Board;
import school.domain.model.Game;
import school.web.model.BoardWeb;
import school.web.model.GameWeb;

import java.util.UUID;

public class MapperWeb {


    public Game WebToDomain(GameWeb game) {
        if (game == null) {
            throw new IllegalArgumentException("Game is null");
        }
        int[][] matrix = game.getBoard().getMatrix();
        UUID id = game.getId();
        Board termBoard = new Board(matrix);
        Game game1 = new Game(id, termBoard);
        return game1;
    }

    public GameWeb DomainToWeb(Game game) {
        if (game == null) {
            throw new IllegalArgumentException("Game is null");
        }
        int[][] matrix = game.getBoard().getMatrix();
        UUID id = game.getId();
        BoardWeb boardWeb = new BoardWeb(matrix);
        GameWeb gameWeb = new GameWeb();
        gameWeb.setId(id);
        gameWeb.setBoard(boardWeb);
        return gameWeb;
    }
}
