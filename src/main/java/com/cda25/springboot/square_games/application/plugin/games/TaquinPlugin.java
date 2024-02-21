package com.cda25.springboot.square_games.application.plugin.games;

import com.cda25.springboot.square_games.application.plugin.GamePlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TaquinPlugin implements GamePlugin {

    @Autowired
    private MessageSource messageSource;

//    @Value("game.taquin.default-player-count")
//    private int defaultPlayerCount;
//    @Value("game.taquin.board-size")
//    private int defaultBoardSize;

    @Override
    public String getName(Locale locale) {
        return messageSource.getMessage("game.taquin.factory-id", null, locale);
    }

}
