package com.cda25.springboot.square_games.application.plugin.games;

import com.cda25.springboot.square_games.application.plugin.GamePluginImpl;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ConnectFourPlugin extends GamePluginImpl {

    @Value("${game.connect4.default-player-count}")
    private String defaultPlayerCount;
    @Value("${game.connect4.default-board-size}")
    private String defaultBoardSize;

    public ConnectFourPlugin() {
        super(new ConnectFourGameFactory());
    }

    @Override
    public String getName(Locale locale) {
        return getMessageSource().getMessage("game.connect4.factory-id", null, locale);
    }


}
