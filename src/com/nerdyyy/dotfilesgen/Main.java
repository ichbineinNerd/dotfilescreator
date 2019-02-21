package com.nerdyyy.dotfilesgen;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;

public class Main extends Application {

    private ObjectProperty<IDotFileGenerator> currentGenerator = null;

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("DotfilesGenerator");
        ScrollPane root = new ScrollPane();

        currentGenerator.addListener((observable, oldValue, newValue) -> root.setContent(newValue.GenerateLayout()));
        currentGenerator.setValue(new gitignore());

        root.setFitToHeight(true);

        primaryStage.setScene(new Scene(root, 400, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
