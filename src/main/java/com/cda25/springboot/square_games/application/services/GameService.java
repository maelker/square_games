package com.cda25.springboot.square_games.application.services;

import com.cda25.springboot.square_games.application.controller.parameters.GameParams;
import com.cda25.springboot.square_games.application.controller.parameters.GameParamsWithRange;
import com.cda25.springboot.square_games.application.controller.parameters.TokenPosMove;
import com.cda25.springboot.square_games.application.plugin.GamePlugin;
import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.InvalidPositionException;

import java.util.Collection;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * Service interface for managing game-related operations.
 */
public interface GameService {

    /**
     * Retrieves a collection of game parameters along with their corresponding ranges from the registered game plugins.
     *
     * @return A {@link Collection} of {@link GameParamsWithRange} objects representing game parameters and their ranges,
     *         obtained from the registered game plugins.
     */
    Collection<GameParamsWithRange> getCatalog();

    /**
     * Creates a new game using the specified game creation parameters and stores it in the games map.
     * If the game creation is successful, the created game is stored in the games map; otherwise, null is returned.
     *
     * @param gameCreationParams An instance of {@link GameParams} containing the parameters required to create the game.
     * @return The {@link Game} object representing the newly created game if successful, otherwise null.
     */
    Game createGame(GameParams gameCreationParams);

    /**
     * Retrieves the {@link GamePlugin} associated with the specified game ID from the list of registered game plugins.
     *
     * @param gameId The ID of the game for which the corresponding {@link GamePlugin} is to be retrieved.
     * @return The {@link GamePlugin} object corresponding to the provided game ID, or null if no matching game plugin is found.
     * @throws NoSuchElementException if no matching game plugin is found for the provided game ID.
     */
    GamePlugin getGamePluginFromId(String gameId);

    /**
     * Retrieves the {@link Game} object associated with the specified game ID from the games map.
     *
     * @param gameId The ID of the game to be retrieved.
     * @return The {@link Game} object corresponding to the provided game ID if it exists in the games map, otherwise returns null.
     */
    Game getGameWithGameId(String gameId);

    /**
     * Makes a move in the game identified by the specified game ID using the provided {@link TokenPosMove}.
     *
     * @param gameId The ID of the game in which the move is to be made.
     * @param tokenPosMove The {@link TokenPosMove} object representing the move to be made.
     * @return The updated {@link Game} object after the move is made.
     * @throws RuntimeException If an {@link InvalidPositionException} occurs while making the move.
     */
    Game makeMove(String gameId, TokenPosMove tokenPosMove);

    /**
     * Deletes the game associated with the specified game ID from the games map.
     *
     * @param gameId The ID of the game to be deleted.
     * @return The {@link Game} object that was removed from the games map, or {@code null} if no game was associated with the provided game ID.
     */
    Game deleteGame(String gameId);

    /**
     * Retrieves a map of finished games from the games collection.
     *
     * @return A {@link Map} containing the IDs and corresponding {@link Game} objects of finished games,
     *         or {@code null} if no games have been marked as finished.
     */
    Map<String, Game> getGamesOngoing();

    /**
     * Retrieves a map of ongoing games from the games collection.
     *
     * @return A {@link Map} containing the IDs and corresponding {@link Game} objects of ongoing games,
     *         or {@code null} if no games are currently ongoing.
     */
    Map<String, Game> getGamesFinished();

    /**
     * This method takes the language and the gameId from the client and returns the name of the game translated
     * @param gameId
     * @param locale
     * @return
     */
    GameParams getDefaultValues(String gameId, Locale locale);
}
