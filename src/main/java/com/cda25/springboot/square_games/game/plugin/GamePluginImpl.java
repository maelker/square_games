package com.cda25.springboot.square_games.game.plugin;

import com.cda25.springboot.square_games.game.services.game_catalog.GameCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class GamePluginImpl implements GamePlugin{


    @Override
    public String GetName(Locale locale) {
        return null;
    }

}
