package com.cda25.springboot.square_games.game.controller;

import com.cda25.springboot.square_games.game.controller.DTO.*;
import com.cda25.springboot.square_games.game.services.GameService;
import com.cda25.springboot.square_games.game.services.GameServiceImpl;
import fr.le_campus_numerique.square_games.engine.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class GameController {

    @Autowired
    private GameService gameService = new GameServiceImpl();

    @PostMapping("/game")
    public GameCreatedDTO createGame(@RequestBody GameParams gameCreationParams) {
        Game game = gameService.createGame(gameCreationParams);
        return new GameCreatedDTO(game.getId().toString(), new GameParams(game.getFactoryId(), game.getPlayerIds().size(), game.getBoardSize()));
    }

    @GetMapping("/games")
    public CatalogGamesDTO getGameCatalog() {
        return new CatalogGamesDTO(gameService.getGamesIdentifiers());
    }

    @GetMapping("/games/ongoing")
    public OngoingGamesDTO getGamesOngoing() {
        Map<String, Game> games = gameService.getGamesOngoing();
        return OngoingGamesDTO.createGamesOngoingGames(games);
    }

    @GetMapping("/games/{game_id}")
    public GameDTO getGame(@PathVariable (value = "game_id") String game_id) {
        return GameDTO.createGameDTO(gameService.getGame(game_id));
    }

    @PutMapping("/games/{game_id}")
    public GameDTO makeMove(@PathVariable String game_id, @RequestBody TokenPosMove posMoves) {
        Game game = gameService.getGame(game_id);
        try {
            if(posMoves.initPos() == null && !game.getRemainingTokens().isEmpty()) {
                game.getRemainingTokens().iterator().next().moveTo(posMoves.finalPos());
            } else if (posMoves.initPos() != null && !game.getBoard().isEmpty()){
                game.getBoard().get(posMoves.initPos()).moveTo(posMoves.finalPos());
            }
        } catch (InvalidPositionException e) {
            throw new RuntimeException(e);
        }
        return GameDTO.createGameDTO(game);
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
        Collection<TokenDTO> tokens = new ArrayList<>();
        Game game = gameService.getGame(game_id);
        if (game != null){
            tokens = GameDTO.createGameDTO(game).availableTokens();
        }
        return tokens;
    }

}
