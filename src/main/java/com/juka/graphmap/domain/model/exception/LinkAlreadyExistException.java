package com.juka.graphmap.domain.model.exception;

public class LinkAlreadyExistException extends RuntimeException {

    private final String link;

    public LinkAlreadyExistException(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "Link " + link + " already exist";
    }

}
