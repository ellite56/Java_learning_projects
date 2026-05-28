package school.datasource.mapper;

import school.domain.model.Game;

public interface GameMapper {
    school.datasource.model.Game toDataSource(Game game);
    Game toDomain(school.datasource.model.Game game);
}
