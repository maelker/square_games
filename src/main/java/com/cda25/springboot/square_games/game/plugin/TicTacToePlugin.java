package com.cda25.springboot.square_games.game.plugin;

import com.cda25.springboot.square_games.game.services.game_catalog.GameCatalog;
import fr.le_campus_numerique.square_games.engine.tictactoe.TicTacToeGameFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Locale;

@Component
public class TicTacToePlugin implements GamePlugin{

    private TicTacToeGameFactory ticTacToeGameFactory;


    @Override
    public String GetName(Locale locale) {
        return null;
    }

    /**
     * @return
     */
    @Override
    public GameCatalog getGameCatalog() {
        return null;
    }
}
