package com.cda25.springboot.square_games.game.controller.DTO;

import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Token;

import java.util.*;

public record BoardDTO(Collection<TokenDTO> tokens) {
    public static BoardDTO createBoardDTO(Map<CellPosition, Token> board) {
        Collection<TokenDTO> boardDTO = new ArrayList<TokenDTO>(9);
        board.forEach((position, token) -> {boardDTO.add(TokenDTO.createTokenDTO(token));});
        return new BoardDTO(boardDTO);
    }
}
