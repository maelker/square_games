package com.cda25.springboot.square_games.game.services;

import com.cda25.springboot.square_games.game.controller.GameParams;
import fr.le_campus_numerique.square_games.engine.Game;

import java.util.Collection;
import java.util.Map;

public interface GameService {
    Game createGame(GameParams gameCreationParams);

    Collection<String> getGamesIdentifiers();

    Game getGame(String gameId);

    boolean deleteGame(String gameId);

    Map<String, Game> getGamesOngoing();

}
