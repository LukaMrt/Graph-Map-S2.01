package com.juka.graphmap.domain.model;

public class GraphCharacteristicsBuilder {

    public int cityCount;
    public int roadCount;
    public float cityPercentage;
    public float restaurantPercentage;
    public float recreationPercentage;
    public float highwayPercentage;
    public float nationalPercentage;
    public float departementalPercentage;
    public boolean error;

    private GraphCharacteristicsBuilder() {
    }

    public static GraphCharacteristicsBuilder aGraphCharacteristics() {
        return new GraphCharacteristicsBuilder();
    }

    public GraphCharacteristicsBuilder withCityCount(int cityCount) {
        this.cityCount = cityCount;
        return this;
    }

    public GraphCharacteristicsBuilder withRoadCount(int roadCount) {
        this.roadCount = roadCount;
        return this;
    }

    public GraphCharacteristicsBuilder withCityPercentage(float cityPercentage) {
        this.cityPercentage = cityPercentage;
        return this;
    }

    public GraphCharacteristicsBuilder withRestaurantPercentage(float restaurantPercentage) {
        this.restaurantPercentage = restaurantPercentage;
        return this;
    }

    public GraphCharacteristicsBuilder withRecreationPercentage(float recreationPercentage) {
        this.recreationPercentage = recreationPercentage;
        return this;
    }

    public GraphCharacteristicsBuilder withHighwayPercentage(float highwayPercentage) {
        this.highwayPercentage = highwayPercentage;
        return this;
    }

    public GraphCharacteristicsBuilder withNationalPercentage(float nationalPercentage) {
        this.nationalPercentage = nationalPercentage;
        return this;
    }

    public GraphCharacteristicsBuilder withDepartementalPercentage(float departementalPercentage) {
        this.departementalPercentage = departementalPercentage;
        return this;
    }

    public GraphCharacteristicsBuilder withError(boolean error) {
        this.error = error;
        return this;
    }

    public GraphCharacteristics build() {
        return new GraphCharacteristics(this);
    }

}
