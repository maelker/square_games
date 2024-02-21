package com.cda25.springboot.square_games.application.controller.DTO;

import com.cda25.springboot.square_games.application.controller.parameters.GameParams;

public record GameCreatedDTO(String GameId, GameParams gameParameters) {
}
