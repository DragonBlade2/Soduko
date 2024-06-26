package com.example.sudoku.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class FileUtils {

    public static URL getFilePathURL (String filepath){
        URL fileUrl;
        try {
            File file = new File(filepath);
            fileUrl = file.toURI().toURL();
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        return fileUrl;
    }

    public static void changeWindow(String filepath){
        try {
            Parent fxml =  FXMLLoader.load(getFilePathURL(filepath));
            Const.ContentArea.getChildren().add(fxml);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
