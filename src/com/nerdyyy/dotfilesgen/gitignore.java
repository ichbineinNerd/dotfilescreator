package com.nerdyyy.dotfilesgen;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

class gitignore implements IDotFileGenerator {
    private void AddRow(VBox vb) {
        HBox hbox = new HBox();
        hbox.setPadding(new Insets(0, 5, 0, 5));
        hbox.setSpacing(2);

        TextField txt = new TextField("file name");

        Button delete = new Button("X");
        delete.setStyle("-fx-border-color: red");
        delete.setOnAction(event -> {if (textFields.size() > 1) {textFields.remove(txt); vb.getChildren().remove(hbox);}});
        textFields.add(txt);

        hbox.getChildren().addAll(txt, new CheckBox("case-sensitive"), delete);

        vb.getChildren().add(vb.getChildren().size() - 1, hbox);
    }

    private List<TextField> textFields = new ArrayList<TextField>();

    public VBox GenerateLayout() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);

        HBox hbox = new HBox();
        Button newRow = new Button("+");
        newRow.setStyle("-fx-color: green;");
        HBox.setHgrow(newRow, Priority.ALWAYS);
        newRow.setMaxWidth(Double.MAX_VALUE);
        newRow.setOnAction(event -> AddRow(vbox));
        hbox.getChildren().add(newRow);
        vbox.getChildren().add(hbox);

        AddRow(vbox);

        return vbox;
    }
    public String getGeneratedFileContents() {
        return "";
    }
}
