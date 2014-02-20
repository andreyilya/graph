package graph.engine.model;

import javax.persistence.Entity;

/**
 * User: a.radkov
 * Date: 20.02.14
 */
@Entity
public class CityEntity {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
