package com.juka.graphmap.domain.model.link;

/**
 * @author Luka Maret
 * @since 0.1.0
 *
 * Enumeration of link types.
 */
public enum LinkType {

    HIGHWAY,
    NATIONAL,
    DEPARTMENTAL;

    public static LinkType of(String type) {
        return switch (type) {
            case "A" -> LinkType.HIGHWAY;
            case "D" -> LinkType.DEPARTMENTAL;
            case "N" -> LinkType.NATIONAL;
            default -> null;
        };
    }
}
