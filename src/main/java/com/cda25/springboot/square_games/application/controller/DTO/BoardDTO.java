package com.cda25.springboot.square_games.application.controller.DTO;

import fr.le_campus_numerique.square_games.engine.CellPosition;
import fr.le_campus_numerique.square_games.engine.Token;

import java.util.*;

public record BoardDTO(Collection<TokenDTO> tokens) {
    public static BoardDTO createBoardDTO(Map<CellPosition, Token> board) {
        Collection<TokenDTO> boardWithTokenDTO = new ArrayList<TokenDTO>();
        if(board != null){
            board.forEach((position, token) -> {boardWithTokenDTO.add(TokenDTO.createTokenDTO(token));});
        }
        return board != null ? new BoardDTO(boardWithTokenDTO) : null;
    }
}
