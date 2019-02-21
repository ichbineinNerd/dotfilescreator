package com.nerdyyy.dotfilesgen;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("DotfilesGenerator");
        ScrollPane root = new ScrollPane();


        root.setContent(new gitignore().GenerateLayout());
        root.setFitToHeight(true);

        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
