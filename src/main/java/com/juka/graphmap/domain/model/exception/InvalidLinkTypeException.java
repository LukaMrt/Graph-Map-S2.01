package com.juka.graphmap.domain.model.exception;

/**
 * Raised when the type of the link is invalid.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class InvalidLinkTypeException extends RuntimeException {

    /**
     * Not found type.
     */
    public final String type;

    /**
     * Constructor.
     *
     * @param type not found type
     */
    public InvalidLinkTypeException(String type) {
        this.type = type;
    }

}
