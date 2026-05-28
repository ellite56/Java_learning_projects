package school.datasource.repository;

import school.datasource.model.Game;

import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class GameStorage {
    private final ConcurrentHashMap<UUID, Game> games=new ConcurrentHashMap<>();

    public Game saveGame(Game game){
        if (game!=null){
            games.put(game.getId(),game);
        }
        else {
            throw new IllegalArgumentException("Incorrect. Game is null");
        }
        return game;
    }

    public Game getGame(UUID id){
        if (id!=null){
            return games.get(id);
        }
        else {
            throw new IllegalArgumentException("Incorrect. Id is null");

        }
    }
}
