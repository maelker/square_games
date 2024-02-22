package com.cda25.springboot.square_games.application.controller;

import com.cda25.springboot.square_games.application.controller.DTO.*;
import com.cda25.springboot.square_games.application.controller.parameters.GameParams;
import com.cda25.springboot.square_games.application.controller.parameters.TokenPosMove;
import com.cda25.springboot.square_games.application.services.GameService;
import com.cda25.springboot.square_games.application.services.GameServiceImpl;
import fr.le_campus_numerique.square_games.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@CrossOrigin
@RestController
public class GameController {

    @Autowired
    private GameService gameService = new GameServiceImpl();

    @Autowired
    private MessageSource msg;

    @PostMapping("/game")
    public GameCreatedDTO createGame(@RequestBody GameParams gameCreationParams) {
        Game game = gameService.createGame(gameCreationParams);
        return game == null ? null : new GameCreatedDTO(game.getId().toString(), new GameParams(game.getFactoryId(), game.getPlayerIds().size(), game.getBoardSize()));
    }

    @GetMapping("/games")
    public CatalogGamesDTO getGameCatalog() {
        return new CatalogGamesDTO(gameService.getGamesIdentifiers());
    }

    @GetMapping("/games/ongoing")
    public OngoingOrFinishedGamesDTO getGamesOngoing() {
        return OngoingOrFinishedGamesDTO.createGamesOngoingOrFinishedGames(gameService.getGamesOngoing());
    }

    @GetMapping("/games/finished")
    public OngoingOrFinishedGamesDTO getGamesFinished() {
        return OngoingOrFinishedGamesDTO.createGamesOngoingOrFinishedGames(gameService.getGamesFinished());
    }

    @GetMapping("/games/{game_id}")
    public GameDTO getGame(@PathVariable (value = "game_id") String game_id) {
        return GameDTO.createGameDTO(gameService.getGame(game_id));
    }

    @PutMapping("/games/{game_id}")
    public GameDTO makeMove(@PathVariable String game_id,
                            @RequestBody TokenPosMove tokenPosMove) {
        return GameDTO.createGameDTO(gameService.makeMove(game_id, tokenPosMove));
    }

    @DeleteMapping("/games/{game_id}")
    public boolean deleteGame(@PathVariable String game_id) {
        return gameService.deleteGame(game_id);
    }

    @GetMapping("/games/{game_id}/board")
    public BoardDTO getGameBoard(@PathVariable (value = "game_id") String game_id) {
        BoardDTO result = null;
        Game game = gameService.getGame(game_id);
        if (game != null){
            result = BoardDTO.createBoardDTO(game.getBoard());
        }
        return result;
    }

    @GetMapping("/games/{game_id}/playable_tokens")
    public Collection<TokenDTO> getGameTokens(@PathVariable (value = "game_id") String game_id) {
        Collection<TokenDTO> tokenDTOs = new ArrayList<>();
        Game game = gameService.getGame(game_id);
        if (game != null){
            tokenDTOs = Objects.requireNonNull(GameDTO.createGameDTO(game)).availableTokens();
        }
        return tokenDTOs;
    }

    @GetMapping("/test")
    public String getTest(@RequestHeader(value = "Accept-Language", required = false) Locale locale) {
        return gameService.getInterName(locale);
    }

    @GetMapping("/{game_id}")
    public String getDefault(@RequestHeader(value = "Accept-Language", required = false) Locale locale,
                             @PathVariable (value = "game_id") String game_id) {
        return gameService.getDefaultValues(game_id, locale);
    }

}
