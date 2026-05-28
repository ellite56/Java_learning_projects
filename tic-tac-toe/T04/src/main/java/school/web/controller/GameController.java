package school.web.controller;

import school.domain.model.Game;
import school.domain.service.GameService;
import school.web.mapper.MapperWeb;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import school.web.model.GameWeb;

import java.util.UUID;

@RestController
@RequestMapping("/game")

public class GameController {
    /**
     Контроллер спринг проиводится обработка http запросов и возвращаемый обхект автоматически переводится в json
     */
    private final GameService gameService;
    private final MapperWeb mapperWeb;

    public GameController(GameService gameService,MapperWeb mapperWeb){
        if (gameService !=null && mapperWeb!=null){
            this.gameService=gameService;
            this.mapperWeb=mapperWeb;
        }
        else {
            throw new IllegalArgumentException("Incorrect.mapperWeb or gameService is null");
        }

    }
    @PostMapping("/{id}")
    public GameWeb processGame(@PathVariable UUID id,@RequestBody GameWeb gameWeb){
        Game doamingame=mapperWeb.WebToDomain(gameWeb);
        school.domain.model.Game processedGame =gameService.processGame(id,doamingame);
        GameWeb response=mapperWeb.DomainToWeb(processedGame);
        response.setStatus(gameService.getGameStatus(processedGame));
        return response;
    }

    @PostMapping
    public GameWeb createWebGame(){
        Game game=gameService.createGame();
        return mapperWeb.DomainToWeb(game);
    }
}
