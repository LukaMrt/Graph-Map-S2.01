package com.juka.graphmap.domain.model.link;

public class LinkCharacteristics {

    public final String name;
    public final String start;
    public final String end;
    public final String type;
    public final int distance;

    public LinkCharacteristics(String name, String start, String end, String type, int distance) {
        this.name = name;
        this.start = start;
        this.end = end;
        this.type = type;
        this.distance = distance;
    }

    public static LinkCharacteristics empty() {
        return new LinkCharacteristics("", "", "", "", 0);
    }

}
