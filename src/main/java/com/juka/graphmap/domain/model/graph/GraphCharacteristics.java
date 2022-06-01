package com.juka.graphmap.domain.model.graph;

import java.util.Objects;

/**
 * Represents the characteristics of a graph.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class GraphCharacteristics {

    /**
     * The number of locations
     */
    public int locationCount;

    /**
     * The number of roads
     */
    public int roadCount;

    /**
     * The percentage of cities
     */
    public float cityPercentage;

    /**
     * The percentage of restaurants
     */
    public float restaurantPercentage;

    /**
     * The percentage of recreation location
     */
    public float recreationPercentage;

    /**
     * The percentage of highways
     */
    public float highwayPercentage;

    /**
     * The percentage of national roads
     */
    public float nationalPercentage;

    /**
     * The percentage of departemental roads
     */
    public float departementalPercentage;

    /**
     * The error message
     */
    public String error;

    /**
     * Constructor of the GraphCharacteristics object.
     *
     * @param builder the builder of the characteristics
     */
    public GraphCharacteristics(GraphCharacteristicsBuilder builder) {
        this.locationCount = builder.cityCount;
        this.roadCount = builder.roadCount;
        this.cityPercentage = Math.round(builder.cityPercentage * 10_000) / 100F;
        this.restaurantPercentage = Math.round(builder.restaurantPercentage * 10_000) / 100F;
        this.recreationPercentage = Math.round(builder.recreationPercentage * 10_000) / 100F;
        this.highwayPercentage = Math.round(builder.highwayPercentage * 10_000) / 100F;
        this.nationalPercentage = Math.round(builder.nationalPercentage * 10_000) / 100F;
        this.departementalPercentage = Math.round(builder.departementalPercentage * 10_000) / 100F;
        this.error = builder.error ? "Erreur détectée" : "Aucune erreur détectée";
    }

    /**
     * Check if the object is equal to the current object.
     *
     * @param o the object to compare
     * @return true if the object is equal to the current object, false otherwise
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphCharacteristics that = (GraphCharacteristics) o;
        return locationCount == that.locationCount
                && roadCount == that.roadCount
                && Float.compare(that.cityPercentage, cityPercentage) == 0
                && Float.compare(that.restaurantPercentage, restaurantPercentage) == 0
                && Float.compare(that.recreationPercentage, recreationPercentage) == 0
                && Float.compare(that.highwayPercentage, highwayPercentage) == 0
                && Float.compare(that.nationalPercentage, nationalPercentage) == 0
                && Float.compare(that.departementalPercentage, departementalPercentage) == 0
                && error.equals(that.error);
    }

    /**
     * Return the hash code of the object.
     *
     * @return the hash code of the object
     */
    @Override
    public int hashCode() {
        return Objects.hash(locationCount, roadCount, cityPercentage, restaurantPercentage, recreationPercentage, highwayPercentage, nationalPercentage, departementalPercentage, error);
    }

}
