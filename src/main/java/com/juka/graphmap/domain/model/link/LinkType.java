package com.juka.graphmap.domain.model.link;

/**
 * Enumeration of link types.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public enum LinkType {

    /**
     * Type representing a Highway.
     */
    HIGHWAY("Autoroute"),
    /**
     * Type representing a national road.
     */
    NATIONAL("Route nationale"),
    /**
     * Type representing a departmental road.
     */
    DEPARTMENTAL("Route départementale");

    /**
     * The string representation of the link type.
     */
    private final String type;

    /**
     * Constructor of the LinkType enumeration.
     *
     * @param type String representation of the link type
     */
    LinkType(String type) {
        this.type = type;
    }

    /**
     * Parse a string to a link type.
     *
     * @param type String "A", "D" or "N" to parse
     * @return the corresponding link type, or null if the string is not recognized
     */
    public static LinkType of(String type) {
        switch (type) {
            case "A":
                return LinkType.HIGHWAY;
            case "D":
                return LinkType.DEPARTMENTAL;
            case "N":
                return LinkType.NATIONAL;
            default:
                return null;
        }
    }

    /**
     * Returns the String representation of the link type.
     *
     * @return the String representation of the link type
     */
    @Override
    public String toString() {
        return type;
    }
}
