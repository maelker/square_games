package com.cda25.springboot.square_games.plugin.games;

import com.cda25.springboot.square_games.plugin.GamePluginImpl;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TaquinPlugin extends GamePluginImpl {


    public TaquinPlugin() {
        super(new TaquinGameFactory());
    }

    @Override
    public String getName(Locale locale) {
        return getMessageSource().getMessage("game.taquin.factory-id", null, locale);
    }

    @Override
    @Value("${game.taquin.default-player-count}")
    public void setPlayerCount(Integer playerCount) {
        this.playerCount = playerCount;
    }

    @Override
    @Value("${game.taquin.default-board-size}")
    public void setBoardSize(Integer boardSize) {
        this.boardSize = boardSize;
    }

}
