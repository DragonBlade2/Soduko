package com.example.sudoku.Controllers;

import com.example.sudoku.utils.Const;
import com.example.sudoku.utils.FileUtils;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private AnchorPane apContent;

    @FXML
    private StackPane apParentArea;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        System.out.println("Initializing...");

        apParentArea.getChildren().add(Const.ContentArea);
        FileUtils.changeWindow("src/main/resources/com/example/sudoku/Menu.fxml");
    }
}
