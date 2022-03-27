package com.juka.graphmap.domain.model.graph;

import java.util.Objects;

public class GraphCharacteristics {

    public int cityCount;
    public int roadCount;
    public float cityPercentage;
    public float restaurantPercentage;
    public float recreationPercentage;
    public float highwayPercentage;
    public float nationalPercentage;
    public float departementalPercentage;
    public boolean error;

    public GraphCharacteristics(GraphCharacteristicsBuilder graphCharacteristicsBuilder) {
        this.cityCount = graphCharacteristicsBuilder.cityCount;
        this.roadCount = graphCharacteristicsBuilder.roadCount;
        this.cityPercentage = graphCharacteristicsBuilder.cityPercentage;
        this.restaurantPercentage = graphCharacteristicsBuilder.restaurantPercentage;
        this.recreationPercentage = graphCharacteristicsBuilder.recreationPercentage;
        this.highwayPercentage = graphCharacteristicsBuilder.highwayPercentage;
        this.nationalPercentage = graphCharacteristicsBuilder.nationalPercentage;
        this.departementalPercentage = graphCharacteristicsBuilder.departementalPercentage;
        this.error = graphCharacteristicsBuilder.error;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GraphCharacteristics that = (GraphCharacteristics) o;
        return cityCount == that.cityCount && roadCount == that.roadCount && Float.compare(that.cityPercentage, cityPercentage) == 0 && Float.compare(that.restaurantPercentage, restaurantPercentage) == 0 && Float.compare(that.recreationPercentage, recreationPercentage) == 0 && Float.compare(that.highwayPercentage, highwayPercentage) == 0 && Float.compare(that.nationalPercentage, nationalPercentage) == 0 && Float.compare(that.departementalPercentage, departementalPercentage) == 0 && error == that.error;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cityCount, roadCount, cityPercentage, restaurantPercentage, recreationPercentage, highwayPercentage, nationalPercentage, departementalPercentage, error);
    }

}
