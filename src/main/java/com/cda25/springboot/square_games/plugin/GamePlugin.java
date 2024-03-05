package com.cda25.springboot.square_games.plugin;

import com.cda25.springboot.square_games.entities_do.GameParams;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;

import java.util.Locale;

public interface GamePlugin {

    String getName(Locale locale);

    GameFactory getGameFactory();

    void setPlayerCount(Integer playerCount);

    void setBoardSize(Integer boardSize);

    GameParams getDefaultValues(Locale locale);

    Game createGame(GameParams gameCreationParams);

}
