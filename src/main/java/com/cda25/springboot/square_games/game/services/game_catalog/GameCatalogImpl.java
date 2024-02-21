package com.cda25.springboot.square_games.game.services.game_catalog;

import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class GameCatalogImpl implements GameCatalog {

    private final Map<String, GameFactory> factories = new HashMap<>();

    @Override
    public Collection<String> getGameIdentifiers() {
        if (factories.isEmpty()) {
            fillHashSet();
        }
        return factories.keySet();
    }

    @Override
    public GameFactory getGameFactory(String gameId) {
        if (factories.isEmpty()) {
            fillHashSet();
        }
        return factories.get(gameId);
    }

    private void fillHashSet() {
        TicTacToeGameFactory ticTacToeGameFactory = new TicTacToeGameFactory();
        TaquinGameFactory taquinGameFactory = new TaquinGameFactory();
        ConnectFourGameFactory connectFourGameFactory = new ConnectFourGameFactory();

        factories.put(ticTacToeGameFactory.getGameFactoryId(), ticTacToeGameFactory);
        factories.put(taquinGameFactory.getGameFactoryId(), taquinGameFactory);
        factories.put(connectFourGameFactory.getGameFactoryId(), connectFourGameFactory);
    }


}
