package com.cda25.springboot.square_games.game_catalog;

import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@Service
public class GameCatalogDummyImpl implements GameCatalog {

    private final TicTacToeGameFactory ticTacToeGameFactory = new TicTacToeGameFactory();

    private final  TaquinGameFactory taquinGameFactory = new TaquinGameFactory();

    private final ConnectFourGameFactory connectFourGameFactory = new ConnectFourGameFactory();

    private List<String> ids;

    @Override
    public Collection<String> getGameIdentifiers() {
        ids = new ArrayList<>();
        ids.add(ticTacToeGameFactory.getGameId());
        ids.add(taquinGameFactory.getGameId());
        ids.add(connectFourGameFactory.getGameId());
        return ids;
    }




}
