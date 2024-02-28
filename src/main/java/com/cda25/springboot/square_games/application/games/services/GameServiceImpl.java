package com.cda25.springboot.square_games.application.games.services;

import com.cda25.springboot.square_games.application.games.controller.parameters.GameParams;
import com.cda25.springboot.square_games.application.games.controller.parameters.GameParamsWithRange;
import com.cda25.springboot.square_games.application.games.controller.parameters.TokenPosMove;
import com.cda25.springboot.square_games.application.games.plugin.GamePlugin;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service implementation for managing game-related operations.
 * <p>
 * This class provides methods for creating games, retrieving game plugins, accessing games by their IDs,
 * making moves in games, deleting games, retrieving default game parameters, and retrieving catalogs of game parameters.
 * </p>
 * <p>
 * It is annotated with {@code @Service}, indicating that it may be used as a service within a Spring application context.
 * </p>
 */
@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private List<GamePlugin> gamePlugins;

    private final Map<String, Game> games = new HashMap<>();

    @Override
    public Game createGame(final GameParams gameCreationParams) {
        final GamePlugin gamePlugin = getGamePluginFromId(gameCreationParams.game());
        Game game = null;
        if (gamePlugin != null) {
            game = gamePlugin.createGame(gameCreationParams.playerCount(), gameCreationParams.boardSize());
            games.put(game.getId().toString(), game);
        }
        return game;
    }

    @Override
    public GamePlugin getGamePluginFromId(String gameId) {
        return gamePlugins.stream()
                .filter(
                        gamePlugin -> Objects.equals(gamePlugin.getGameFactory().getGameFactoryId(), gameId)
                )
                .toList()
                .getFirst();
    }

    @Override
    public Game getGameWithGameId(String gameId) {
        Game gameFromId = null;
        if (games.containsKey(gameId)) {
            gameFromId = games.get(gameId);
        }
        return gameFromId;
    }


    @Override
    public GameParams getDefaultValues(String gameId, Locale locale) {
        return gamePlugins.stream()
                .filter(
                        gamePlugin -> Objects.equals(gamePlugin.getGameFactory().getGameFactoryId(), gameId)
                )
                .toList()
                .getFirst()
                .getDefaultValues(locale);
    }

    @Override
    public Collection<GameParamsWithRange> getCatalog() {
        return gamePlugins.stream()
                .map(
                        gamePlugin -> new GameParamsWithRange(gamePlugin.getGameFactory().getGameFactoryId(),
                                gamePlugin.getGameFactory().getPlayerCountRange(),
                                gamePlugin.getGameFactory().getBoardSizeRange(gamePlugin.getGameFactory().getPlayerCountRange().min()))
                )
                .toList();
    }

    @Override
    public Map<String, Game> getGamesOngoing() {
        final Map<String, Game> ongoingGames = new HashMap<>();
        if (!games.isEmpty()) {
            games.forEach((s, game) -> {
                if (game.getStatus() == GameStatus.ONGOING) {
                    ongoingGames.put(s, game);
                }
            });
        }
        return ongoingGames.isEmpty() ? null : ongoingGames;
    }

    @Override
    public Map<String, Game> getGamesFinished() {
        Map<String, Game> finishedGames = new HashMap<>();
        if (!games.isEmpty()) {
            games.forEach((s, game) -> {
                if (game.getStatus() == GameStatus.TERMINATED) {
                    finishedGames.put(s, game);
                }
            });
        }
        return finishedGames.isEmpty() ? null : finishedGames;
    }

    @Override
    public Game makeMove(String gameId, TokenPosMove tokenPosMove) {
        Game game = getGamesOngoing().get(gameId);
        try {
            if (tokenPosMove.initPos() == null && !game.getRemainingTokens().isEmpty()) {
                game.getRemainingTokens().iterator().next().moveTo(tokenPosMove.finalPos());
            } else if (tokenPosMove.initPos() != null && !game.getBoard().isEmpty()) {
                game.getBoard().get(tokenPosMove.initPos()).moveTo(tokenPosMove.finalPos());
            }
        } catch (InvalidPositionException e) {
            throw new RuntimeException(e);
        }
        return game;
    }

    @Override
    public Game deleteGame(String gameId) {
        return games.remove(gameId);
    }

}
