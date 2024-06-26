package com.example.sudoku;

import com.example.sudoku.utils.FileUtils;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Launcher extends Application {
    double x,y = 1;

    //Window Variables
    public static final int originalTileSize = 16; // 16x16 Tile
    public static final int scale = 4;
    public static final int tileSize = originalTileSize * scale; //64x64 Tile

    public static final int gameScreenRow = 9;
    public static final int settingScreenRow = 4;
    public static final int maxScreenCol = 9;
    public static final int maxScreenRow = gameScreenRow + settingScreenRow;
    public static final int screenWidth = tileSize * maxScreenCol;
    public static final int screenHeight = tileSize * maxScreenRow;


    @Override
    public void start(Stage primaryStage) throws Exception{

        String filePath = "src/main/resources/com/example/sudoku/Main.fxml";
        Parent root =  FXMLLoader.load(FileUtils.getFilePathURL(filePath));
        primaryStage.initStyle(StageStyle.UNDECORATED);

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - x);
            primaryStage.setY(event.getScreenY() - y);
        });
        System.out.println("Width: " + screenWidth + " Height: " + screenHeight);

        primaryStage.setScene(new Scene(root, screenWidth, screenHeight));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}