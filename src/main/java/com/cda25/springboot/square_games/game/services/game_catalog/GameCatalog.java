package com.cda25.springboot.square_games.game.services.game_catalog;

import fr.le_campus_numerique.square_games.engine.GameFactory;

import java.util.Collection;

public interface GameCatalog {

    Collection<String> getGameIdentifiers();

    GameFactory getGameFactory(String gameId);

}
