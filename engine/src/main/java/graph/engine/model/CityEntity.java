package graph.engine.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * User: a.radkov
 * Date: 20.02.14
 */
@Entity
public class CityEntity {

    @Column
    private String name;

    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //TODO: uid id, identifiable entity
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
