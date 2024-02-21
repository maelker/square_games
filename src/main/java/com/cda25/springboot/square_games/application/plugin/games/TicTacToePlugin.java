package com.cda25.springboot.square_games.application.plugin.games;

import com.cda25.springboot.square_games.application.plugin.GamePlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TicTacToePlugin implements GamePlugin {

    @Autowired
    private MessageSource messageSource;

//    @Value("game.tictactoe.default-player-count")
//    private int defaultPlayerCount;
//    @Value("game.tictactoe.board-size")
//    private int defaultBoardSize;

    @Override
    public String getName(Locale locale) {
        return messageSource.getMessage("game.tictactoe.factory-id", null, locale);
    }

}
