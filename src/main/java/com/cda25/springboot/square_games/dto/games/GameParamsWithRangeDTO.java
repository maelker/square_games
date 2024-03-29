package com.cda25.springboot.square_games.dto.games;

import fr.le_campus_numerique.square_games.engine.IntRange;

public record GameParamsWithRangeDTO(String game, IntRange playerCount, IntRange boardSize) {
}
