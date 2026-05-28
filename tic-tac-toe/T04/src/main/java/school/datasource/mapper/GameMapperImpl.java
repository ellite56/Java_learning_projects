package school.datasource.mapper;

import school.datasource.model.Board;
import school.datasource.model.Game;

public class GameMapperImpl implements GameMapper {
    @Override
    public Game toDataSource(school.domain.model.Game game) {
        if (game != null) {
            int[][] matrix = game.getBoard().getMatrix();
            school.datasource.model.Board newBoard = new Board(matrix);
            school.datasource.model.Game newGame = new Game(game.getId(), newBoard);
            return newGame;
        } else {
            throw new IllegalArgumentException("Incorrect.game is null");
        }

    }

    @Override
    public school.domain.model.Game toDomain(Game game) {
        if (game != null) {
            int[][] matrix = game.getBoard().getMatrix();
            school.domain.model.Board newBoard =new school.domain.model.Board(matrix);
            school.domain.model.Game newGame = new school.domain.model.Game(game.getId(),newBoard);
            return newGame;
        } else {
            throw new IllegalArgumentException("Incorrect.Gaame is null");

        }
    }
}
