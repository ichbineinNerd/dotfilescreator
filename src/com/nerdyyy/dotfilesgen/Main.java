package com.nerdyyy.dotfilesgen;

import javafx.application.Application;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    private ObjectProperty<IDotFileGenerator> currentGenerator = new SimpleObjectProperty<>();

    private Node createSpacer() {
        final Region spacer = new Region();
        VBox.setVgrow(spacer, Priority.ALWAYS);
        return spacer;
    }

    private void AddTypeLabels(VBox vb) {
        vb.setPadding(new Insets(20));
        Button gitignore = new Button(".gitignore");
        gitignore.prefWidthProperty().bind(vb.widthProperty());
        gitignore.setOnAction(event -> currentGenerator.setValue(new gitignore()));





        vb.getChildren().addAll(createSpacer(), gitignore, createSpacer());
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("DotfilesGenerator");
        GridPane root = new GridPane();
        ScrollPane sp = new ScrollPane();

        root.setVgap(5);
        root.setHgap(5);

        //sp.setFitToHeight(true);
        sp.setPrefViewportWidth(Integer.MAX_VALUE); //looks weird with Double.MAX_VALUE, and 2.1 billion should still be large enough for most purposes.
        sp.setMaxWidth(Double.MAX_VALUE);
        sp.setFitToWidth(true);
        sp.setFitToHeight(true);
        sp.setStyle("-fx-box-border: transparent;");


        sp.prefWidthProperty().bind(root.widthProperty().divide(3).multiply(2));
        root.add(sp, 0, 0, 1, 1);
        VBox filetypes = new VBox();
        filetypes.prefWidthProperty().bind(root.widthProperty().divide(3));
        filetypes.prefHeightProperty().bind(root.heightProperty());
        AddTypeLabels(filetypes);
        root.add(filetypes, 1, 0, 2, 1);


        Scene sc = new Scene(root, 640, 480);
        primaryStage.setScene(sc);

        currentGenerator.addListener(
                (observable, oldValue, newValue) ->{
                    if (newValue == null)
                        return;
                    Node tmp = newValue.GenerateLayout();
                    ((VBox)tmp).prefWidthProperty().bind(sc.widthProperty());
                    sp.setContent(tmp);
                });
        //currentGenerator.setValue(new gitignore());

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
