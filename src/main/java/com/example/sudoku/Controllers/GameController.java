package com.example.sudoku.Controllers;

import com.example.sudoku.Board.BoardCreator;
import com.example.sudoku.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.sudoku.Board.BoardCreator.handleKeyPress;

public class GameController implements Initializable {

    @FXML
    private AnchorPane apContent;

    @FXML
    private FlowPane fpBoard;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BoardCreator.createBoard(fpBoard, null);
        fpBoard.setFocusTraversable(true);
        fpBoard.requestFocus();
    }

    @FXML
    void changeCellValue(KeyEvent event) {
        if (BoardCreator.selectedRow != -1 && BoardCreator.selectedCol != -1) {
            handleKeyPress(event);
        }
    }

    @FXML
    void goBack() {
        FileUtils.changeWindow("src/main/resources/com/example/sudoku/Menu.fxml");
    }

}
