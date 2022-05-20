package com.juka.graphmap.domain.model.comparaison;

public class Comparaison {

    public final String name;
    public final String best;
    public final String worst;

    public Comparaison(String name, String best, String worst) {
        this.name = name;
        this.best = best;
        this.worst = worst;
    }

    @Override
    public String toString() {
        return best + " est plus " + name + " que " + worst;
    }
}
