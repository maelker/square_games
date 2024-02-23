package com.cda25.springboot.square_games.application.services.game_catalog;

import fr.le_campus_numerique.square_games.engine.GameFactory;

import java.util.Collection;

/**
 * <p>
 * Interface for managing a catalog of game factories. It defines methods to retrieve game identifiers
 * and corresponding game factories.
 * </p>
 * <p>
 * Implementations of this interface are responsible for populating the map of game factories with instances
 * of various game factories, such as Tic-Tac-Toe, Taquin, and Connect Four.
 * </p>
 */
@Deprecated
public interface GameCatalog {

    /**
     * Retrieves a collection of game identifiers from the factories map.
     * If the factories map is empty, it populates the map with game identifiers before retrieving them.
     *
     * @return A {@link Collection} of {@link String} objects representing the game identifiers.
     */
    Collection<String> getGameIdentifiers();

    /**
     * Retrieves the {@link GameFactory} associated with the specified game identifier from the factories map.
     * If the factories map is empty, it populates the map with game identifiers before retrieving the factory.
     *
     * @param gameId The identifier of the game factory to be retrieved.
     * @return The {@link GameFactory} object associated with the provided game identifier, or {@code null} if no such factory is found.
     */
    GameFactory getGameFactory(String gameId);

}
