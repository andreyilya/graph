package graph.engine.dto;

/**
 * User: a.radkov
 * Date: 20.02.14
 */
public class City extends Identifiable {

    private String name;

    private Integer population;

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

}
