package com.cda25.springboot.square_games.controllers;

import com.cda25.springboot.square_games.dto.games.*;
import com.cda25.springboot.square_games.entities_do.GameParams;
import com.cda25.springboot.square_games.entities_do.TokenPosMove;
import com.cda25.springboot.square_games.services.GameService;
import fr.le_campus_numerique.square_games.engine.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;
import java.util.Objects;

@CrossOrigin
@RestController @RequestMapping("games")
public class GameController {

    @Autowired
    private GameService gameService;

    @GetMapping("")
    public CatalogGamesDTO getGameCatalog() {
        return new CatalogGamesDTO(
                gameService.getCatalog().stream()
                        .map(gameParamsWithRange -> new GameParamsWithRangeDTO(gameParamsWithRange.game(), gameParamsWithRange.playerCount(), gameParamsWithRange.boardSize())
                        )
                        .toList()
        );
    }


    @PostMapping("")
    public GameCreatedDTO createGame(@RequestBody GameParams gameCreationParams) {
        Game game = gameService.createGame(gameCreationParams);
        return game == null ? null : new GameCreatedDTO(game.getId().toString(), new GameParams(game.getFactoryId(), game.getPlayerIds().size(), game.getBoardSize()));
    }

    @GetMapping("ongoing")
    public OngoingOrFinishedGamesDTO getGamesOngoing() {
        return OngoingOrFinishedGamesDTO.createGamesOngoingOrFinishedGames(gameService.getGamesOngoing());
    }

    @GetMapping("finished")
    public OngoingOrFinishedGamesDTO getGamesFinished() {
        return OngoingOrFinishedGamesDTO.createGamesOngoingOrFinishedGames(gameService.getGamesFinished());
    }

    @GetMapping("{gameId}")
    public GameDTO getGame(@PathVariable(value = "gameId") String gameId) {
        return GameDTO.createGameDTO(gameService.getGameWithGameId(gameId));
    }

    @Deprecated
    @GetMapping("{gameId}/default")
    public GameParamsDTO getDefaultValues(@RequestHeader(value = "Accept-Language", required = false) Locale locale,
                                          @PathVariable(value = "gameId") String gameId) {
        GameParams gameParams = gameService.getDefaultValues(gameId, locale);
        return new GameParamsDTO(gameParams.game(), gameParams.playerCount(), gameParams.boardSize());
    }

    @GetMapping("{gameId}/board")
    public BoardDTO getGameBoard(@PathVariable(value = "gameId") String gameId) {
        BoardDTO result = null;
        Game game = gameService.getGameWithGameId(gameId);
        if (game != null) {
            result = BoardDTO.createBoardDTO(game.getBoard());
        }
        return result;
    }

    @GetMapping("{gameId}/playable_tokens")
    public Collection<TokenDTO> getGameTokens(@PathVariable(value = "gameId") String gameId) {
        Collection<TokenDTO> tokenDTOs = new ArrayList<>();
        Game game = gameService.getGameWithGameId(gameId);
        if (game != null) {
            tokenDTOs = Objects.requireNonNull(GameDTO.createGameDTO(game)).availableTokens();
        }
        return tokenDTOs;
    }

    @PutMapping("{gameId}/update")
    public GameDTO makeMove(@PathVariable String gameId,
                            @RequestBody TokenPosMove tokenPosMove) {
        return GameDTO.createGameDTO(gameService.makeMove(gameId, tokenPosMove));
    }

    @DeleteMapping("{gameId}/delete")
    public GameDTO deleteGame(@PathVariable String gameId) {
        return GameDTO.createGameDTO(gameService.deleteGame(gameId));
    }

}
