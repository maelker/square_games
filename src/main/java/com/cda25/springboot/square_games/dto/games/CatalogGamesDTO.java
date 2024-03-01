package com.cda25.springboot.square_games.dto.games;

import java.util.Collection;

public record CatalogGamesDTO(Collection<GameParamsWithRangeDTO> gameCatalog) {
}
