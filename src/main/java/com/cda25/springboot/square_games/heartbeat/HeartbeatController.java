package com.cda25.springboot.square_games.heartbeat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@Deprecated
@RestController
public class HeartbeatController {
    @Autowired
    private HeartbeatSensor heartBeatSensor;

    @GetMapping("/heartbeat")
    public int getHeartBeat() {
        return heartBeatSensor.get();
    }

}
