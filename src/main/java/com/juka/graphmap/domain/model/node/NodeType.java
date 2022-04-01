package com.juka.graphmap.domain.model.node;

/**
 * @author Luka Maret
 * @since 0.1.0
 *
 * Enumeration of node types.
 */
public enum NodeType {
    
    CITY,
    RESTAURANT,
    RECREATION_CENTER;

    public static NodeType of(String type) {
        return switch (type) {
            case "V" -> NodeType.CITY;
            case "R" -> NodeType.RESTAURANT;
            case "P" -> NodeType.RECREATION_CENTER;
            default -> null;
        };
    }
}
