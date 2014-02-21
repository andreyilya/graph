package graph.engine.dto;

/**
 * User: a.radkov
 * Date: 20.02.14
 */
public abstract class Identifiable {
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
