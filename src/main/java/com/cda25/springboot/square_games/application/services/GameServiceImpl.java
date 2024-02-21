package com.cda25.springboot.square_games.application.services;

import com.cda25.springboot.square_games.application.controller.parameters.GameParams;
import com.cda25.springboot.square_games.application.controller.parameters.TokenPosMove;
import com.cda25.springboot.square_games.application.plugin.GamePlugin;
import com.cda25.springboot.square_games.application.services.game_catalog.GameCatalog;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameServiceImpl implements GameService{

    @Autowired
    private GameCatalog gameCatalog;

    @Autowired
    private List<GamePlugin> gamePlugins;

    private final Map<String, Game> games = new HashMap<String, Game>();

    @Override
    public String getInterName(Locale locale) {
        return gamePlugins.iterator().next().getName(locale);
    }

    @Override
    public Game createGame(GameParams gameCreationParams){
        Game game = null;
        GameFactory gameFactory = gameCatalog.getGameFactory(gameCreationParams.game());
        if (gameFactory != null ) {
            game = gameFactory.createGame(gameCreationParams.playerCount(), gameCreationParams.boardSize());
            games.put(game.getId().toString(), game);
        }
        return game;
    }

    @Override
    public Collection<String> getGamesIdentifiers() {
         return gameCatalog.getGameIdentifiers();
    }

    @Override
    public Map<String, Game> getGamesOngoing() {
        return games;
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
    public Game makeMove(String game_id, TokenPosMove tokenPosMove) {
        Game game = getGame(game_id);
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
