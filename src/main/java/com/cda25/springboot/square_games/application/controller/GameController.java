package com.cda25.springboot.square_games.application.controller;

import com.cda25.springboot.square_games.application.controller.DTO.*;
import com.cda25.springboot.square_games.application.controller.parameters.GameParams;
import com.cda25.springboot.square_games.application.controller.parameters.TokenPosMove;
import com.cda25.springboot.square_games.application.persistance.DAOService;
import com.cda25.springboot.square_games.application.persistance.user.UserR;
import com.cda25.springboot.square_games.application.persistance.user.dto.UserDTO;
import com.cda25.springboot.square_games.application.persistance.user.dto.UsersDTO;
import com.cda25.springboot.square_games.application.services.GameService;
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
    private GameService gameService;

    @Autowired
    private DAOService daoService;

    @GetMapping("/games")
    public CatalogGamesDTO getGameCatalog() {
        return new CatalogGamesDTO(
                gameService.getCatalog().stream()
                        .map(gameParamsWithRange -> new GameParamsWithRangeDTO(gameParamsWithRange.game(), gameParamsWithRange.playerCount(), gameParamsWithRange.boardSize())
                                )
                        .toList()
        );
    }


    @PostMapping("/games/create")
    public GameCreatedDTO createGame(@RequestBody GameParams gameCreationParams) {
        Game game = gameService.createGame(gameCreationParams);
        return game == null ? null : new GameCreatedDTO(game.getId().toString(), new GameParams(game.getFactoryId(), game.getPlayerIds().size(), game.getBoardSize()));
    }

    @GetMapping("/games/{gameId}/full")
    public GameDTO getGame(@PathVariable(value = "gameId") String gameId) {
        return GameDTO.createGameDTO(gameService.getGameWithGameId(gameId));
    }

    @GetMapping("/games/ongoing")
    public OngoingOrFinishedGamesDTO getGamesOngoing() {
        return OngoingOrFinishedGamesDTO.createGamesOngoingOrFinishedGames(gameService.getGamesOngoing());
    }

    @GetMapping("/games/finished")
    public OngoingOrFinishedGamesDTO getGamesFinished() {
        return OngoingOrFinishedGamesDTO.createGamesOngoingOrFinishedGames(gameService.getGamesFinished());
    }

    @GetMapping("/games/{gameId}/board")
    public BoardDTO getGameBoard(@PathVariable(value = "gameId") String gameId) {
        BoardDTO result = null;
        Game game = gameService.getGameWithGameId(gameId);
        if (game != null) {
            result = BoardDTO.createBoardDTO(game.getBoard());
        }
        return result;
    }

    @GetMapping("/games/{gameId}/playable_tokens")
    public Collection<TokenDTO> getGameTokens(@PathVariable(value = "gameId") String gameId) {
        Collection<TokenDTO> tokenDTOs = new ArrayList<>();
        Game game = gameService.getGameWithGameId(gameId);
        if (game != null) {
            tokenDTOs = Objects.requireNonNull(GameDTO.createGameDTO(game)).availableTokens();
        }
        return tokenDTOs;
    }

    @PutMapping("/games/{gameId}/update")
    public GameDTO makeMove(@PathVariable String gameId,
                            @RequestBody TokenPosMove tokenPosMove) {
        return GameDTO.createGameDTO(gameService.makeMove(gameId, tokenPosMove));
    }

    @DeleteMapping("/games/{gameId}/delete")
    public GameDTO deleteGame(@PathVariable String gameId) {
        return GameDTO.createGameDTO(gameService.deleteGame(gameId));
    }

    @Deprecated
    @GetMapping("/{gameId}")
    public GameParamsDTO getDefaultValues(@RequestHeader(value = "Accept-Language", required = false) Locale locale,
                                   @PathVariable(value = "gameId") String gameId) {
        GameParams gameParams = gameService.getDefaultValues(gameId, locale);
        return new GameParamsDTO(gameParams.game(), gameParams.playerCount(), gameParams.boardSize());
    }

    @GetMapping("/users")
    public Collection<UserDTO> getAllUsers() {
        return UsersDTO.createUsersDTO(daoService.getAllUsers());
    }

    @PostMapping("/users/create")
    public UserDTO createUser(@RequestBody UserDTO userDTO) {
        UserR userR = UserR.createUserImpl(userDTO);
        userR = daoService.createUser(userR);
        return UserDTO.createUsersDTO(userR);
    }

    @GetMapping("/users/{userId}/full")
    public UserDTO getUserFromId(@PathVariable String userId) {
        return UserDTO.createUsersDTO(daoService.getUserFromId(userId));
    }

    @PutMapping("/users/{userId}/update")
    public UserDTO updateUser(@PathVariable String userId,
                              @RequestBody UserDTO userDTO) {

        return UserDTO.createUsersDTO(daoService.updateUser(UserR.createUserImpl(userDTO), userId));
    }

    @DeleteMapping("/users/{userId}/delete")
    public UserDTO deleteUser(@PathVariable String userId) {
        return UserDTO.createUsersDTO(daoService.deleteUser(userId));
    }

}
