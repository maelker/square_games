package com.cda25.springboot.square_games.game.controller.DTO;

import com.cda25.springboot.square_games.game.controller.GameParams;
import fr.le_campus_numerique.square_games.engine.Game;

import java.util.HashMap;
import java.util.Map;

public record OngoingGamesDTO(Map<String, GameParams> games) {
    public static OngoingGamesDTO createGamesOngoingGames(Map<String, Game> games) {
        Map<String, GameParams> gamesToDTO = new HashMap<>();
        games.forEach((s, game) -> gamesToDTO.put(s, new GameParams(game.getFactoryId(), game.getPlayerIds().size(), game.getBoardSize())));
        return new OngoingGamesDTO(gamesToDTO);
    }
}
