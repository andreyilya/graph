package graph.engine.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * User: a.radkov
 * Date: 20.02.14
 */
@Entity
public class CityEntity extends Identifiable {

    @Column
    private String name;

    @Column
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
