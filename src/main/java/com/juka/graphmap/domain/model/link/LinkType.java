package com.juka.graphmap.domain.model.link;

/**
 * Enumeration of link types.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public enum LinkType {

    HIGHWAY("Autoroute"),
    NATIONAL("Route nationale"),
    DEPARTMENTAL("Route départementale");

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
     * Returns the String representation of the link type.
     *
     * @return the String representation of the link type
     */
    @Override
    public String toString() {
        return type;
    }

    /**
     * Parse a string to a link type.
     *
     * @param type String "A", "D" or "N" to parse
     * @return the corresponding link type, or null if the string is not recognized
     */
    public static LinkType of(String type) {
        return switch (type) {
            case "A" -> LinkType.HIGHWAY;
            case "D" -> LinkType.DEPARTMENTAL;
            case "N" -> LinkType.NATIONAL;
            default -> null;
        };
    }
}
