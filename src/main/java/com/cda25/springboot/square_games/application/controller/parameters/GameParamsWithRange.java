package com.cda25.springboot.square_games.application.controller.parameters;

import fr.le_campus_numerique.square_games.engine.IntRange;

public record GameParamsWithRange(String game, IntRange playerCount, IntRange boardSize) {
}
