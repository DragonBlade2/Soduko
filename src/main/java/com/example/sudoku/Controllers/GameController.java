package com.example.sudoku.Controllers;

import com.example.sudoku.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable {

    @FXML
    private AnchorPane apContent;

    @FXML
    private StackPane spBoard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    void goBack() {
        FileUtils.changeWindow("src/main/resources/com/example/sudoku/Menu.fxml");
    }

}
