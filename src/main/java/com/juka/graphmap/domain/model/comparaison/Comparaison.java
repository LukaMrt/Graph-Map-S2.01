package com.juka.graphmap.domain.model.comparaison;

/**
 * Represents the result of a comparison between two nodes.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class Comparaison {

    /**
     * Name of the comparaison's characteristic.
     */
    public final String name;

    /**
     * Name of the best city regarding characteristic.
     */
    public final String best;

    /**
     * Name of the worst city regarding characteristic.
     */
    public final String worst;

    /**
     * Constructor of the comparaison object.
     *
     * @param name The criteria of the comparison.
     * @param best The best node.
     * @param worst The worst node.
     */
    public Comparaison(String name, String best, String worst) {
        this.name = name;
        this.best = best;
        this.worst = worst;
    }

    /**
     * Returns the result of the comparison.
     * @return The result of the comparison.
     */
    @Override
    public String toString() {
        return best + " est plus " + name + " que " + worst;
    }

    /**
     * Return a short description of the comparison.
     * @return A short description of the comparison.
     */
    public String toShortString() {
        return best + " est plus " + name;
    }

}
