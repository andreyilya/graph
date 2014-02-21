package graph.engine.dto;

import java.util.List;

/**
 * User: a.radkov
 * Date: 20.02.14
 */
public class City extends Identifiable {

    private String name;

    private Integer population;

    private List<Road> roads;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    public List<Road> getRoads() {
        return roads;
    }

    public void setRoads(List<Road> roads) {
        this.roads = roads;
    }
}
