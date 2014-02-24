package graph.engine.model;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

/**
 * User: a.radkov
 * Date: 20.02.14
 */
@Entity
public class CityEntity extends IdentifiableEntity {

    @Column
    private String name;

    @Column
    private Integer population;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<RoadEntity> roads;

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

    public List<RoadEntity> getRoads() {
        return roads;
    }

    public void setRoads(List<RoadEntity> roads) {
        this.roads = roads;
    }
}
