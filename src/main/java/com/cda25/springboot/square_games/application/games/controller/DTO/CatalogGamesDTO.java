package com.cda25.springboot.square_games.application.games.controller.DTO;

import java.util.Collection;

public record CatalogGamesDTO(Collection<GameParamsWithRangeDTO> gameCatalog) {
}
