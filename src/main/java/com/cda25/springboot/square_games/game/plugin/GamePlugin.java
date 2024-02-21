package com.cda25.springboot.square_games.game.plugin;

import com.cda25.springboot.square_games.game.services.game_catalog.GameCatalog;

import java.util.Locale;

public interface GamePlugin {

    String GetName(Locale locale);

    GameCatalog getGameCatalog();
}
