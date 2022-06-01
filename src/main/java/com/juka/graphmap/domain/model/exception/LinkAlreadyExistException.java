package com.juka.graphmap.domain.model.exception;

/**
 * Raised when the format of the link is invalid.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class LinkAlreadyExistException extends RuntimeException {

    /**
     * The link that raised the exception.
     */
    private final String link;

    /**
     * Constructor.
     *
     * @param link The link that already exist.
     */
    public LinkAlreadyExistException(String link) {
        this.link = link;
    }

    /**
     * Returns error message.
     * @return Error message.
     */
    @Override
    public String toString() {
        return "Link " + link + " already exist";
    }

}
