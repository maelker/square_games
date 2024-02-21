package com.cda25.springboot.square_games.application.plugin.games;

import com.cda25.springboot.square_games.application.plugin.GamePlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class ConnectFourPlugin implements GamePlugin {

    @Autowired
    private MessageSource messageSource;

    @Value("game.connect4.default-player-count")
    private String defaultPlayerCount;
    @Value("game.connect4.board-size")
    private String defaultBoardSize;

    @Override
    public String getName(Locale locale) {
        return messageSource.getMessage("game.connect4.factory-id", null, locale);
    }

}
