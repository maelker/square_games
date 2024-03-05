package com.cda25.springboot.square_games.plugin;

import com.cda25.springboot.square_games.entities_do.GameParams;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameFactory;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;

import java.util.Locale;
import java.util.Optional;

public abstract class GamePluginImpl implements GamePlugin {

    private final GameFactory gameFactory;
    protected Integer playerCount;
    protected Integer boardSize;

    @Getter
    @Autowired
    private MessageSource messageSource;

    public GamePluginImpl(GameFactory gameFactory) {
        this.gameFactory = gameFactory;
    }

    @Override
    public GameFactory getGameFactory() {
        return gameFactory;
    }

    @Override
    public GameParams getDefaultValues(Locale locale) {
        return new GameParams(getName(locale), playerCount, boardSize);
    }

    @Override
    public Game createGame(GameParams gameParams) {
        return getGameFactory().createGame(
                Optional.ofNullable(gameParams.playerCount()).orElse(this.playerCount),
                Optional.ofNullable(gameParams.boardSize()).orElse(this.boardSize)
        );
    }

}
