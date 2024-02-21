package com.cda25.springboot.square_games.game.controller.DTO;

import java.util.Collection;

public record CatalogGamesDTO(Collection<String> gameFactoryIds) {
}
