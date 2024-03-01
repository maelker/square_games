package com.cda25.springboot.square_games.dto.games;

import com.cda25.springboot.square_games.entities_do.GameParams;
import fr.le_campus_numerique.square_games.engine.Game;

import java.util.HashMap;
import java.util.Map;

public record OngoingOrFinishedGamesDTO(Map<String, GameParams> games) {
    public static OngoingOrFinishedGamesDTO createGamesOngoingOrFinishedGames(Map<String, Game> games) {
        Map<String, GameParams> gamesToDTO = new HashMap<>();
        if (games != null) {
            games.forEach((s, game) -> gamesToDTO.put(s, new GameParams(game.getFactoryId(), game.getPlayerIds().size(), game.getBoardSize())));
        }
        return games == null ? null : new OngoingOrFinishedGamesDTO(gamesToDTO);
    }
}
