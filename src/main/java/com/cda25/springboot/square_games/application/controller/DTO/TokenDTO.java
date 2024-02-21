package com.cda25.springboot.square_games.application.controller.DTO;

import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Token;

import java.util.Set;
import java.util.UUID;

public record TokenDTO(
        java.util.@jakarta.validation.constraints.NotNull Optional<UUID> OwnerID,
        String name,
        CellPosition coords,
        Set<CellPosition> allowedMoves
) {

    public static TokenDTO createTokenDTO(Token token) {
        return new TokenDTO(token.getOwnerId(), token.getName(), token.getPosition(), token.getAllowedMoves());
    }
}
