package com.cda25.springboot.square_games.application.services;

import com.cda25.springboot.square_games.application.controller.parameters.GameParams;
import com.cda25.springboot.square_games.application.controller.parameters.TokenPosMove;
import com.cda25.springboot.square_games.application.plugin.GamePlugin;
import fr.le_campus_numerique.square_games.engine.Game;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;

public interface GameService {
    String getInterName(Locale locale);

    Game createGame(GameParams gameCreationParams);

    GamePlugin getGamePluginFromId(String gameId);

    Collection<String> getGamesIdentifiers();

    Game getGame(String gameId);

    Game makeMove(String gameId, TokenPosMove tokenPosMove);

    boolean deleteGame(String gameId);

    Map<String, Game> getGamesOngoing();

    Map<String, Game> getGamesFinished();

    String getDefaultValues(String gameId, Locale locale);
}
