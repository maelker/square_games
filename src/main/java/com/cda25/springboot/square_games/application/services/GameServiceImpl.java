package com.cda25.springboot.square_games.application.services;

import com.cda25.springboot.square_games.application.controller.parameters.GameParams;
import com.cda25.springboot.square_games.application.controller.parameters.TokenPosMove;
import com.cda25.springboot.square_games.application.plugin.GamePlugin;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameServiceImpl implements GameService{

    @Autowired
    private List<GamePlugin> gamePlugins;

    private final Map<String, Game> games = new HashMap<>();

    @Override
    public String getInterName(Locale locale) {
        return gamePlugins.iterator().next().getName(locale);
    }

    @Override
    public String getDefaultValues(String game_id, Locale locale) {
        return gamePlugins.stream()
                .filter(
                        gamePlugin -> Objects.equals(gamePlugin.getGameFactory().getGameFactoryId(), game_id)
                )
                .toList()
                .getFirst()
                .getDefaultValues(locale);
    }

    @Override
    public Collection<String> getGamesIdentifiers() {
        return gamePlugins.stream()
                .map(
                        gamePlugin -> gamePlugin.getGameFactory().getGameFactoryId()
                )
                .toList();
    }

    @Override
    public GamePlugin getGamePluginFromId(String game_id) {
        return gamePlugins.stream()
                .filter(
                        gamePlugin -> Objects.equals(gamePlugin.getGameFactory().getGameFactoryId(), game_id)
                )
                .toList()
                .getFirst();
    }

    @Override
    public Game createGame(GameParams gameCreationParams){
        GamePlugin gamePlugin = getGamePluginFromId(gameCreationParams.game());
        Game game = null;
        if (gamePlugin != null) {
            game = gamePlugin.createGame();
            games.put(game.getId().toString(), game);
        }
        return game;
    }

    @Override
    public Game getGame(String game_id) {
        Game gameFromId = null;
        if (games.containsKey(game_id)) {
            gameFromId = games.get(game_id);
        }
        return gameFromId;
    }

    @Override
    public Map<String, Game> getGamesOngoing() {
        Map<String, Game> ongoingGames = new HashMap<>();
        if(!games.isEmpty()){
            games.forEach((s, game) -> {if (game.getStatus()== GameStatus.ONGOING){ongoingGames.put(s, game);}});
        }
        return ongoingGames.isEmpty() ? null : ongoingGames;
    }

    @Override
    public Map<String, Game> getGamesFinished() {
        Map<String, Game> finishedGames = new HashMap<>();
        if(!games.isEmpty()){
            games.forEach((s, game) -> {if (game.getStatus()== GameStatus.TERMINATED){finishedGames.put(s, game);}});
        }
        return finishedGames.isEmpty() ? null : finishedGames;
    }

    @Override
    public Game makeMove(String game_id, TokenPosMove tokenPosMove) {
        Game game = getGamesOngoing().get(game_id);
        try {
            if(tokenPosMove.initPos() == null && !game.getRemainingTokens().isEmpty()) {
                game.getRemainingTokens().iterator().next().moveTo(tokenPosMove.finalPos());
            } else if (tokenPosMove.initPos() != null && !game.getBoard().isEmpty()){
                game.getBoard().get(tokenPosMove.initPos()).moveTo(tokenPosMove.finalPos());
            }
        } catch (InvalidPositionException e) {
            throw new RuntimeException(e);
        }
        return game;
    }

    @Override
    public boolean deleteGame(String gameId) {
        return games.remove(gameId, games.get(gameId));
    }

}
