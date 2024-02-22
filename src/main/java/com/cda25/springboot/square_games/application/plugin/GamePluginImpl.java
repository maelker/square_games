package com.cda25.springboot.square_games.application.plugin;

import fr.le_campus_numerique.square_games.engine.GameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;

public abstract class GamePluginImpl implements GamePlugin{

    private GameFactory gameFactory;
    private String playerCount;
    private String boardSize;

    @Autowired
    private MessageSource messageSource;

    public GamePluginImpl(GameFactory gameFactory) {
        this.gameFactory = gameFactory;
    }

    @Override
    public GameFactory getGameFactory() {
        return gameFactory;
    }

    public MessageSource getMessageSource() {
        return messageSource;
    }

    @Override
    public String getDefaultValues(Locale locale) {
        return "{\n\tgameFactoryId : " + getName(locale) + "\n\tplayerCount : " + playerCount + "\n\tBoardSize : " + boardSize + "\n}";
    }
}
