package school.domain.service;

import school.domain.model.Game;
import school.domain.model.GameStatus;
import school.domain.model.Move;

import java.util.UUID;

public interface GameService {

    Move findBestMove(Game game);
    boolean validateGame(Game pastGame,Game currentGame);
    GameStatus getGameStatus(Game game);
    Game processGame(UUID id, Game currentGame);
    Game createGame();
}
