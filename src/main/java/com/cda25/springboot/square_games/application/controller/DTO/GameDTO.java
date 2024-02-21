package com.cda25.springboot.square_games.application.controller.DTO;

import fr.le_campus_numerique.square_games.engine.Game;
import fr.le_campus_numerique.square_games.engine.GameStatus;
import fr.le_campus_numerique.square_games.engine.Token;


import java.util.*;

public record GameDTO(
        String gameName,
        Set<UUID> players,
        int boardSize,
        BoardDTO board,
        Collection<TokenDTO> availableTokens,
        GameStatus gameStatus,
        UUID currentPlayer,
        UUID winnerPlayer
) {


    public static GameDTO createGameDTO(Game game) {
        Collection<TokenDTO> availableTokens = findAvailableTokens(game);
        return game != null ? new GameDTO(
                game.getFactoryId(),
                game.getPlayerIds().isEmpty() ? null : game.getPlayerIds(),
                game.getBoardSize(),
                BoardDTO.createBoardDTO(game.getBoard()),
                availableTokens,
                game.getStatus(),
                game.getCurrentPlayerId(),
                game.getStatus() == GameStatus.TERMINATED ? game.getCurrentPlayerId() : null)
                : null;
    }

    private static Collection<TokenDTO> findAvailableTokens(Game game) {
        Collection<TokenDTO> availableTokens = new ArrayList<>();
        if(game != null) {
            game.getRemainingTokens().stream().filter(Token::canMove).map(TokenDTO::createTokenDTO).forEach(availableTokens::add);
            game.getBoard().values().stream().filter(Token::canMove).map(TokenDTO::createTokenDTO).forEach(availableTokens::add);
            game.getRemovedTokens().stream().filter(Token::canMove).map(TokenDTO::createTokenDTO).forEach(availableTokens::add);
        }
        return availableTokens;
    }

}
