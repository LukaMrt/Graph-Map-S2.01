package com.juka.graphmap.domain.model.graph;

import java.util.Objects;

public class GraphCharacteristics {

    public int locationCount;
    public int roadCount;
    public float cityPercentage;
    public float restaurantPercentage;
    public float recreationPercentage;
    public float highwayPercentage;
    public float nationalPercentage;
    public float departementalPercentage;
    public String error;

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

    @Override
    public int hashCode() {
        return Objects.hash(locationCount, roadCount, cityPercentage, restaurantPercentage, recreationPercentage, highwayPercentage, nationalPercentage, departementalPercentage, error);
    }

}
