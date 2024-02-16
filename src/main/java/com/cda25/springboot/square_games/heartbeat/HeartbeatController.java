package com.cda25.springboot.square_games.heartbeat;

import com.cda25.springboot.square_games.game_catalog.GameCatalog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@RestController
public class HeartbeatController {
    @Autowired
    private HeartbeatSensor heartBeatSensor;

    @Autowired
    private GameCatalog gameCatalog;

    @GetMapping("/heartbeat")
    public int getHeartBeat() {
        return heartBeatSensor.get();
    }
    @GetMapping("/games")
    public String getGameCatalog() {
        return gameCatalog.getGameIdentifiers().toString();
    }

}
