package school.datasource.repository;

import school.datasource.mapper.GameMapper;
import school.domain.model.Game;

import java.util.UUID;

public class GameRepositoryImpl implements GameRepository {
    private final GameStorage gameStorage;
    private final GameMapper  gameMapper;

    public GameRepositoryImpl(GameStorage gameStorage, GameMapper gameMapper){
        if (gameStorage !=null && gameMapper !=null){
            this.gameStorage=gameStorage;
            this.gameMapper=gameMapper;
        }
        else {
            throw new IllegalArgumentException("Incorrect. gameStorage or gameMapper is null");
        }
    }

    @Override
    public Game save(Game game) {
        school.datasource.model.Game tempGame=gameMapper.toDataSource(game);
        school.datasource.model.Game savedGame=gameStorage.saveGame(tempGame);
        return gameMapper.toDomain(savedGame);
    }

    @Override
    public Game findById(UUID id) {
        if (id!=null){
            school.datasource.model.Game newGame=gameStorage.getGame(id);
            if (newGame==null){
                return null;
            }
            Game domaingame=gameMapper.toDomain(newGame);
            return domaingame;
        }
        else {
            throw new IllegalArgumentException("Incorrect. Id is null");
        }
    }
}
