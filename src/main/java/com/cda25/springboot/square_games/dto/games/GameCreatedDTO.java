package com.cda25.springboot.square_games.dto.games;

import com.cda25.springboot.square_games.entities_do.GameParams;

public record GameCreatedDTO(String GameId, GameParams gameParameters) {
}
