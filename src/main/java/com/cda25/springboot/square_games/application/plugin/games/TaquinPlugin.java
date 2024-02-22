package com.cda25.springboot.square_games.application.plugin.games;

import com.cda25.springboot.square_games.application.plugin.GamePluginImpl;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TaquinPlugin extends GamePluginImpl {


    @Value("${game.taquin.default-player-count}")
    private String defaultPlayerCount;
    @Value("${game.taquin.default-board-size}")
    private String defaultBoardSize;

    public TaquinPlugin() {
        super(new TaquinGameFactory());
    }

    @Override
    public String getName(Locale locale) {
        return getMessageSource().getMessage("game.taquin.factory-id", null, locale);
    }

}
