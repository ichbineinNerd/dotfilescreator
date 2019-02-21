package com.nerdyyy.dotfilesgen;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;

class gitignore implements IDotFileGenerator {

    private TextArea ta;
    private Node createSpacer() {
        final Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);
        return spacer;
    }
    private void AddRow(VBox vb) {
        HBox hbox = new HBox();
        hbox.setMaxWidth(Double.MAX_VALUE);
        hbox.setPadding(new Insets(0, 5, 0, 5));
        hbox.setSpacing(2);

        TextField txt = new TextField("file/directory");
        txt.setMinWidth(100d);
        txt.textProperty().addListener((a, b, c) -> ta.setText(getGeneratedFileContents()));

        CheckBox cb = new CheckBox("case-sensitive");
        cb.setOnAction(event -> ta.setText(getGeneratedFileContents()));
        cb.setMinWidth(75d);

        Button delete = new Button("X");
        delete.setStyle("-fx-border-color: red");
        delete.setOnAction(event -> {if (textFields.size() > 1) {textFields.remove(txt); checkBoxes.remove(cb); vb.getChildren().remove(hbox);} ta.setText(getGeneratedFileContents());});
        textFields.add(txt);
        checkBoxes.add(cb);

        delete.setMinWidth(50d);
        hbox.getChildren().addAll(txt, createSpacer(), cb, createSpacer(), delete);
        HBox.setHgrow(txt, Priority.ALWAYS);
        HBox.setHgrow(cb, Priority.ALWAYS);
        HBox.setHgrow(delete, Priority.ALWAYS);


        vb.getChildren().add(vb.getChildren().size() - 2, hbox);
        ta.setText(getGeneratedFileContents());
    }

    private List<TextField> textFields = new ArrayList<>();
    private List<CheckBox> checkBoxes = new ArrayList<>();

    public VBox GenerateLayout() {
        VBox vbox = new VBox();
        vbox.setPadding(new Insets(10));
        vbox.setSpacing(10);
        vbox.setMaxWidth(Double.MAX_VALUE);
        vbox.setPrefWidth(Integer.MAX_VALUE);
        vbox.setFillWidth(true);
        HBox hbox = new HBox();
        Button newRow = new Button("+");
        newRow.setStyle("-fx-color: green;");
        HBox.setHgrow(newRow, Priority.ALWAYS);
        newRow.setMaxWidth(Double.MAX_VALUE);
        newRow.setOnAction(event -> AddRow(vbox));
        hbox.getChildren().add(newRow);
        vbox.getChildren().add(hbox);

        ta = new TextArea();
        ta.setEditable(false);
        vbox.getChildren().add(ta);

        AddRow(vbox);

        ta.setText(getGeneratedFileContents());

        vbox.setStyle("-fx-box-border: transparent;");
        return vbox;
    }
    public String getGeneratedFileContents() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < textFields.size(); i++) {
            if (checkBoxes.get(i).isSelected()) {
                for (int j = 0; j < textFields.get(i).getText().length(); j++) {
                    char c = textFields.get(i).getText().charAt(j);
                    if (Character.isAlphabetic(c)) {
                        sb.append('[');
                        sb.append(Character.toLowerCase(c));
                        sb.append(Character.toUpperCase(c));
                        sb.append(']');
                    }else {
                        sb.append(c);
                    }
                }
                sb.append('\n');
            }else {
                sb.append(textFields.get(i).getText());
                sb.append('\n');
            }
        }
        return sb.toString();
    }
}
