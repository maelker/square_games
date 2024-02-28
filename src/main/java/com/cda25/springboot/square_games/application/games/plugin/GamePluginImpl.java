package com.cda25.springboot.square_games.application.games.plugin;

import com.cda25.springboot.square_games.application.games.controller.parameters.GameParams;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;
import java.util.Optional;

public abstract class GamePluginImpl implements GamePlugin {

    private final GameFactory gameFactory;
    protected Integer playerCount;
    protected Integer boardSize;

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
    public GameParams getDefaultValues(Locale locale) {
        return new GameParams(getName(locale), playerCount, boardSize);
    }

    @Override
    public Game createGame(Integer playerCount, Integer boardSize) {
        return getGameFactory().createGame(
                playerCount == null ? this.playerCount : playerCount,
                Optional.ofNullable(boardSize).orElse(this.boardSize)
        );
    }

}
