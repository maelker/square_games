package com.cda25.springboot.square_games.application.services.game_catalog;

import fr.le_campus_numerique.square_games.engine.GameFactory;

import java.util.Collection;

@Deprecated
public interface GameCatalog {

    Collection<String> getGameIdentifiers();

    GameFactory getGameFactory(String gameId);

}
