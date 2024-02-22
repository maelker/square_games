package com.cda25.springboot.square_games.application.plugin.games;

import com.cda25.springboot.square_games.application.plugin.GamePluginImpl;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TicTacToePlugin extends GamePluginImpl {



    @Value("${game.tictactoe.default-player-count}")
    private String defaultPlayerCount;
    @Value("${game.tictactoe.default-board-size}")
    private String defaultBoardSize;

    public TicTacToePlugin() {
        super(new TicTacToeGameFactory());
    }

    @Override
    public String getName(Locale locale) {
        return getMessageSource().getMessage("game.tictactoe.factory-id", null, locale);
    }


}
