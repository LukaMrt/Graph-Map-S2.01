package com.juka.graphmap.view.swing.components;

import javax.swing.*;
import java.awt.*;

/**
 * Swing JFileChooser builder.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class FileChooserBuilder {

    private final JFileChooser fileChooser;

    private FileChooserBuilder() {
        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
    }

    /**
     * Create a new JFileChooser builder.
     *
     * @return a new JFileChooser builder
     */
    public static FileChooserBuilder aFileChooser() {
        return new FileChooserBuilder();
    }

    /**
     * Set the start directory.
     *
     * @param directory the start directory
     * @return the FileChooserBuilder
     */
    public FileChooserBuilder withDirectory(String directory) {
        fileChooser.setCurrentDirectory(new java.io.File(directory));
        return this;
    }

    /**
     * Set the FileChooser size.
     *
     * @param width the width of the FileChooser
     * @param height the height of the FileChooser
     * @return the FileChooserBuilder
     */
    public FileChooserBuilder withSize(int width, int height) {
        fileChooser.setPreferredSize(new Dimension(width, height));
        fileChooser.setMaximumSize(new Dimension(width, height));
        fileChooser.setMinimumSize(new Dimension(width, height));
        fileChooser.setSize(new Dimension(width, height));
        return this;
    }

    /**
     * Build the final JFileChooser.
     *
     * @return the final JFileChooser
     */
    public JFileChooser build() {
        return fileChooser;
    }

}
