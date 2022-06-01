package com.juka.graphmap.view.swing.components;

import javax.swing.*;
import java.awt.*;

public class FileChooserBuilder {

    private final JFileChooser fileChooser;

    private FileChooserBuilder() {
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
    }

    public static FileChooserBuilder aFileChooser() {
        return new FileChooserBuilder();
    }

    public FileChooserBuilder withDirectory(String directory) {
        fileChooser.setCurrentDirectory(new java.io.File(directory));
        return this;
    }

    public JFileChooser build() {
        return fileChooser;
    }

    public FileChooserBuilder withSize(int width, int height) {
        fileChooser.setPreferredSize(new Dimension(width, height));
        fileChooser.setMaximumSize(new Dimension(width, height));
        fileChooser.setMinimumSize(new Dimension(width, height));
        fileChooser.setSize(new Dimension(width, height));
        return this;
    }

}
