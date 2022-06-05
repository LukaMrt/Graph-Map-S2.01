package com.juka.graphmap.domain.model.view;

/**
 * Swing title of a screen.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class Title {

    /**
     * Title of the screen.
     */
    public final String title;
    /**
     * Number of the screen.
     */
    public final int screenNumber;

    /**
     * Constructor of the Title class.
     *
     * @param title        Title of the screen.
     * @param screenNumber Number of the screen.
     */
    public Title(String title, int screenNumber) {
        this.title = title;
        this.screenNumber = screenNumber;
    }

}
