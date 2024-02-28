package com.cda25.springboot.square_games.application.games.controller.DTO;

import com.cda25.springboot.square_games.application.games.controller.parameters.GameParams;

public record GameCreatedDTO(String GameId, GameParams gameParameters) {
}
