package com.cda25.springboot.square_games.application.controller;

import com.cda25.springboot.square_games.application.controller.DTO.*;
import com.cda25.springboot.square_games.application.controller.parameters.GameParams;
import com.cda25.springboot.square_games.application.controller.parameters.TokenPosMove;
import com.cda25.springboot.square_games.application.services.GameService;
import com.cda25.springboot.square_games.application.services.GameServiceImpl;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Objects;

@CrossOrigin
@RestController
public class GameController {

    @Autowired
    private GameService gameService = new GameServiceImpl();

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

    @GetMapping("/games/{gameId}")
    public GameDTO getGame(@PathVariable(value = "gameId") String gameId) {
        return GameDTO.createGameDTO(gameService.getGame(gameId));
    }

    @PutMapping("/games/{gameId}")
    public GameDTO makeMove(@PathVariable String gameId,
                            @RequestBody TokenPosMove tokenPosMove) {
        return GameDTO.createGameDTO(gameService.makeMove(gameId, tokenPosMove));
    }

    @DeleteMapping("/games/{gameId}")
    public boolean deleteGame(@PathVariable String gameId) {
        return gameService.deleteGame(gameId);
    }

    @GetMapping("/games/{gameId}/board")
    public BoardDTO getGameBoard(@PathVariable(value = "gameId") String gameId) {
        BoardDTO result = null;
        Game game = gameService.getGame(gameId);
        if (game != null) {
            result = BoardDTO.createBoardDTO(game.getBoard());
        }
        return result;
    }

    @GetMapping("/games/{gameId}/playable_tokens")
    public Collection<TokenDTO> getGameTokens(@PathVariable(value = "gameId") String gameId) {
        Collection<TokenDTO> tokenDTOs = new ArrayList<>();
        Game game = gameService.getGame(gameId);
        if (game != null) {
            tokenDTOs = Objects.requireNonNull(GameDTO.createGameDTO(game)).availableTokens();
        }
        return tokenDTOs;
    }

    @GetMapping("/{gameId}")
    public String getDefaultValues(@RequestHeader(value = "Accept-Language", required = false) Locale locale,
                                   @PathVariable(value = "gameId") String gameId) {
        return gameService.getDefaultValues(gameId, locale);
    }

}
