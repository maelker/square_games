package com.cda25.springboot.square_games.game.controller.DTO;

import com.cda25.springboot.square_games.game.controller.parameters.GameParams;

public record GameCreatedDTO(String GameId, GameParams gameParameters) {
}
