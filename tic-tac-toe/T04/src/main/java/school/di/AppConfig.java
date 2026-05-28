package school.di;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import school.datasource.mapper.GameMapper;
import school.datasource.mapper.GameMapperImpl;
import school.datasource.repository.GameRepository;
import school.datasource.repository.GameRepositoryImpl;
import school.datasource.repository.GameStorage;
import school.domain.service.GameService;
import school.domain.service.GameServiceImpl;
import school.web.mapper.MapperWeb;

@Configuration
public class AppConfig {
    @Bean
    public GameStorage gameStorage(){
        return new GameStorage();
    }
    @Bean
    public GameMapper gameMapper(){
        return new GameMapperImpl();
    }
    @Bean
    public GameRepository gameRepository(GameStorage gameStorage,GameMapper gameMapper){
        return new GameRepositoryImpl(gameStorage,gameMapper);
    }
    @Bean
    public GameService gameService(GameRepository gameRepository){
        return new GameServiceImpl(gameRepository);
    }
    @Bean
    public MapperWeb mapperWeb(){
        return new MapperWeb();
    }
}
