package com.juka.graphmap.domain.model.graph;

/**
 * Builder of the characteristics of a graph.
 *
 * @author Luka Maret and Julien Linget
 * @since 0.1.0
 */
public class GraphCharacteristicsBuilder {

    /**
     * The number of locations in the graph.
     */
    public int cityCount;
    /**
     * The number of roads in the graph.
     */
    public int roadCount;
    /**
     * The percentage of city in the graph.
     */
    public float cityPercentage;
    /**
     * The percentage of restaurant in the graph.
     */
    public float restaurantPercentage;
    /**
     * The percentage of recreation center in the graph.
     */
    public float recreationPercentage;
    /**
     * The percentage of highway in the graph.
     */
    public float highwayPercentage;
    /**
     * The percentage of national road in the graph.
     */
    public float nationalPercentage;
    /**
     * The percentage of departemental road in the graph.
     */
    public float departementalPercentage;
    /**
     * The error message.
     */
    public boolean error;

    /**
     * Prevents the instantiation of this class with the default constructor from a different class.
     */
    private GraphCharacteristicsBuilder() {
    }

    /**
     * Builds the GraphCharacteristicsBuilder object.
     *
     * @return the GraphCharacteristicsBuilder object
     */
    public static GraphCharacteristicsBuilder aGraphCharacteristics() {
        return new GraphCharacteristicsBuilder();
    }

    /**
     * Sets the city count.
     *
     * @param cityCount the city count
     * @return the GraphCharacteristicsBuilder object
     */
    public GraphCharacteristicsBuilder withCityCount(int cityCount) {
        this.cityCount = cityCount;
        return this;
    }

    /**
     * Sets the road count.
     *
     * @param roadCount the road count
     * @return the GraphCharacteristicsBuilder object
     */
    public GraphCharacteristicsBuilder withRoadCount(int roadCount) {
        this.roadCount = roadCount;
        return this;
    }

    /**
     * Sets the city percentage.
     *
     * @param cityPercentage the city percentage
     * @return the GraphCharacteristicsBuilder object
     */
    public GraphCharacteristicsBuilder withCityPercentage(float cityPercentage) {
        this.cityPercentage = cityPercentage;
        return this;
    }

    /**
     * Sets the restaurant percentage.
     *
     * @param restaurantPercentage the restaurant percentage
     * @return the GraphCharacteristicsBuilder object
     */
    public GraphCharacteristicsBuilder withRestaurantPercentage(float restaurantPercentage) {
        this.restaurantPercentage = restaurantPercentage;
        return this;
    }

    /**
     * Sets the recreation center percentage.
     *
     * @param recreationPercentage the recreation center percentage
     * @return the GraphCharacteristicsBuilder object
     */
    public GraphCharacteristicsBuilder withRecreationPercentage(float recreationPercentage) {
        this.recreationPercentage = recreationPercentage;
        return this;
    }

    /**
     * Sets the highway percentage.
     *
     * @param highwayPercentage the highway percentage
     * @return the GraphCharacteristicsBuilder object
     */
    public GraphCharacteristicsBuilder withHighwayPercentage(float highwayPercentage) {
        this.highwayPercentage = highwayPercentage;
        return this;
    }

    /**
     * Sets the national road percentage.
     *
     * @param nationalPercentage the national road percentage
     * @return the GraphCharacteristicsBuilder object
     */
    public GraphCharacteristicsBuilder withNationalPercentage(float nationalPercentage) {
        this.nationalPercentage = nationalPercentage;
        return this;
    }

    /**
     * Sets the departemental road percentage.
     *
     * @param departementalPercentage the departemental road percentage
     * @return the GraphCharacteristicsBuilder object
     */
    public GraphCharacteristicsBuilder withDepartementalPercentage(float departementalPercentage) {
        this.departementalPercentage = departementalPercentage;
        return this;
    }

    /**
     * Sets the error flag.
     *
     * @param error the error flag
     * @return the GraphCharacteristicsBuilder object
     */
    public GraphCharacteristicsBuilder withError(boolean error) {
        this.error = error;
        return this;
    }

    /**
     * Builds the GraphCharacteristics object.
     *
     * @return the GraphCharacteristics object
     */
    public GraphCharacteristics build() {
        return new GraphCharacteristics(this);
    }

}
