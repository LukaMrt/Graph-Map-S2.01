package com.juka.graphmap.domain.model.node;

/**
 * Enumeration of node types.
 *
 * @author Luka Maret
 * @since 0.1.0
 */
public enum NodeType {

    /**
     * Type representing a city.
     */
    CITY("Ville"),
    /**
     * Type representing a restaurant.
     */
    RESTAURANT("Restaurant"),
    /**
     * Type representing a recreation center.
     */
    RECREATION_CENTER("Centre de loisirs");

    private final String type;

    /**
     * Constructor of the NodeType enumeration.
     *
     * @param type type of the node
     */
    NodeType(String type) {
        this.type = type;
    }

    /**
     * Parse a string to a node type.
     *
     * @param type String "V", "R" or "L"
     * @return the matching node type, null if no match
     */
    public static NodeType of(String type) {
        return switch (type) {
            case "V" -> NodeType.CITY;
            case "R" -> NodeType.RESTAURANT;
            case "L" -> NodeType.RECREATION_CENTER;
            default -> null;
        };
    }

    /**
     * Returns the string representation of the node type.
     *
     * @return the string representation of the node type
     */
    @Override
    public String toString() {
        return type;
    }
}
