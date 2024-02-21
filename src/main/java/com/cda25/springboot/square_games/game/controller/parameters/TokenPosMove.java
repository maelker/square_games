package com.cda25.springboot.square_games.game.controller.parameters;

import fr.le_campus_numerique.square_games.engine.CellPosition;

public record TokenPosMove(CellPosition initPos, CellPosition finalPos) {
}
