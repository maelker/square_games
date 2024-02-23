package com.cda25.springboot.square_games.application.services.game_catalog;

import fr.le_campus_numerique.square_games.engine.GameFactory;
import fr.le_campus_numerique.square_games.engine.connectfour.ConnectFourGameFactory;
import fr.le_campus_numerique.square_games.engine.taquin.TaquinGameFactory;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * Implementation of the {@link GameCatalog} interface, providing methods for managing a catalog of game factories.
 * The class maintains a map of game factories, where each factory is associated with a unique game identifier.
 * It allows retrieval of game identifiers and corresponding game factories.
 * </p>
 * <p>
 * This class is annotated with {@code @Service}, indicating that it may be used as a service within a Spring application context.
 * The class is responsible for populating the map of game factories with instances of various game factories,
 * such as Tic-Tac-Toe, Taquin, and Connect Four.
 * </p>
 */
@Deprecated
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

    /**
     * Populates the factories map with instances of various game factories, such as Tic-Tac-Toe, Taquin, and Connect Four.
     * This method is typically called when the factories map is empty, ensuring it contains a set of predefined game factories.
     */
    private void fillHashSet() {
        // Create instances of various game factories
        TicTacToeGameFactory ticTacToeGameFactory = new TicTacToeGameFactory();
        TaquinGameFactory taquinGameFactory = new TaquinGameFactory();
        ConnectFourGameFactory connectFourGameFactory = new ConnectFourGameFactory();

        // Add the game factories to the factories map
        factories.put(ticTacToeGameFactory.getGameFactoryId(), ticTacToeGameFactory);
        factories.put(taquinGameFactory.getGameFactoryId(), taquinGameFactory);
        factories.put(connectFourGameFactory.getGameFactoryId(), connectFourGameFactory);
    }


}
