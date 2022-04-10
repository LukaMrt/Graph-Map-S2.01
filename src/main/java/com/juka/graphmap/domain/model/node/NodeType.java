package com.juka.graphmap.domain.model.node;

/**
 * @author Luka Maret
 * @since 0.1.0
 *
 * Enumeration of node types.
 */
public enum NodeType {
    
    CITY("Ville"),
    RESTAURANT("Restaurant"),
    RECREATION_CENTER("Centre de loisirs");

    private final String type;

    NodeType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return type;
    }

    public static NodeType of(String type) {
        return switch (type) {
            case "V" -> NodeType.CITY;
            case "R" -> NodeType.RESTAURANT;
            case "L" -> NodeType.RECREATION_CENTER;
            default -> null;
        };
    }
}
