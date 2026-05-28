package school.datasource.repository;

import school.domain.model.Game;

import java.util.UUID;

public interface GameRepository {
    public Game save(Game game);
    public Game findById(UUID id);
}
