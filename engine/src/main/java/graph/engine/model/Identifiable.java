package graph.engine.model;

import org.springframework.data.annotation.Id;

/**
 * User: a.radkov
 * Date: 20.02.14
 */
public abstract class Identifiable {
    @Id
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
