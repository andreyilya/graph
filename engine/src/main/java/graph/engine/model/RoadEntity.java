package graph.engine.model;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
 * User: a.radkov
 * Date: 20.02.14
 */
@Entity
public class RoadEntity extends Identifiable {

    @Column
    private String lenght;

    @Column
    private String quantity;

    public String getLenght() {
        return lenght;
    }

    public void setLenght(String lenght) {
        this.lenght = lenght;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }
}
