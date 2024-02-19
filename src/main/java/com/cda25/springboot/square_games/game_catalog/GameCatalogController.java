package com.cda25.springboot.square_games.game_catalog;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GameCatalogController {

    @Autowired
    private GameCatalog gameCatalog;

    @GetMapping("/games")
    public String getGameCatalog() {
        return gameCatalog.getGameIdentifiers().toString();
    }
    @GetMapping("/games/game")
    public String getGame(@RequestParam String name) {
        return gameCatalog.getGameIdentifier(name);
    }

}
