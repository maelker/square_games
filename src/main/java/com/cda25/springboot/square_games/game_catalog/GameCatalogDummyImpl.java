package com.cda25.springboot.square_games.game_catalog;

import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Service
public class GameCatalogDummyImpl implements GameCatalog {

    private final TicTacToeGameFactory ticTacToeGameFactory = new TicTacToeGameFactory();

    private final  TaquinGameFactory taquinGameFactory = new TaquinGameFactory();

    private final ConnectFourGameFactory connectFourGameFactory = new ConnectFourGameFactory();

    private HashSet<String> ids;

    @Override
    public Collection<String> getGameIdentifiers() {
        fillHashSet();
        return ids;
    }

    @Override
    public String getGameIdentifier(String game) {
        fillHashSet();
        return ids.contains(game) ? "true" : "false";
    }

    private void fillHashSet() {
        ids = new HashSet<>();
        ids.add(ticTacToeGameFactory.getGameId());
        ids.add(taquinGameFactory.getGameId());
        ids.add(connectFourGameFactory.getGameId());
    }


}
