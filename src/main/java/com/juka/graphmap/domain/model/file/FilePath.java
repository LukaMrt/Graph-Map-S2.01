package com.juka.graphmap.domain.model.file;

/**
 * Represents a file path.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class FilePath {

    private final String path;

    /**
     * Constructor of the FilePath.
     *
     * @param path the path
     */
    public FilePath(String path) {
        this.path = path;
    }

    /**
     * Returns the path.
     *
     * @return the path
     */
    public String path() {
        return path;
    }

}
