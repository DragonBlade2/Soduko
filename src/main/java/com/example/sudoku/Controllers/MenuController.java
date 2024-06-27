package com.example.sudoku.Controllers;

import com.example.sudoku.GameMechanics.Game;
import com.example.sudoku.utils.Const;
import com.example.sudoku.utils.DebugUtils;
import com.example.sudoku.utils.FileUtils;
import com.example.sudoku.utils.GameMode;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;


public class MenuController {

    @FXML
    private AnchorPane apContent;

    @FXML
    void competitionMode() {
        goToBoard();
        GameMode.currentMode = GameMode.COMPETITION;
        System.out.println(GameMode.currentMode);
    }

    @FXML
    void multiplayerMode() {
        goToBoard();
        GameMode.currentMode = GameMode.MULTIPLAYER;
        System.out.println(GameMode.currentMode);
    }

    @FXML
    void normalMode() {
        goToBoard();
        GameMode.currentMode = GameMode.NORMAL;
        System.out.println(GameMode.currentMode);
    }

    private void goToBoard(){
        FileUtils.changeWindow("src/main/resources/com/example/sudoku/Board.fxml");
    }

    @FXML
    void quit() {
        DebugUtils.quit(Const.STAND_QUIT);
    }
}
