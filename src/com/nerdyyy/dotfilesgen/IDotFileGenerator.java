package com.nerdyyy.dotfilesgen;

import javafx.scene.Node;

public interface IDotFileGenerator {
    Node GenerateLayout();
    String getGeneratedFileContents();
}
