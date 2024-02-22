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

    String getDefaultValues(String game_id, Locale locale);

    Game createGame(GameParams gameCreationParams);

    GamePlugin getGamePluginFromId(String game_id);

    Collection<String> getGamesIdentifiers();

    Game getGame(String gameId);

    Game makeMove(String game_id, TokenPosMove tokenPosMove);

    boolean deleteGame(String gameId);

    Map<String, Game> getGamesOngoing();

    Map<String, Game> getGamesFinished();

}
