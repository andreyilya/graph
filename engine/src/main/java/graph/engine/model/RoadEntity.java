package graph.engine.model;

import graph.engine.common.Direction;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

/**
 * User: a.radkov
 * Date: 20.02.14
 */
@Entity
public class RoadEntity extends IdentifiableEntity {

    @Column
    private Long lenght;

    @Column
    private String quantity;

    @ManyToOne(fetch = FetchType.LAZY)
    private CityEntity sourceCity;

    @ManyToOne(fetch = FetchType.LAZY)
    private CityEntity targetCity;

    @Enumerated(EnumType.STRING)
    @Column
    private Direction direction;

    public Long getLenght() {
        return lenght;
    }

    public void setLenght(Long lenght) {
        this.lenght = lenght;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public CityEntity getTargetCity() {
        return targetCity;
    }

    public void setTargetCity(CityEntity targetCity) {
        this.targetCity = targetCity;
    }

    public CityEntity getSourceCity() {
        return sourceCity;
    }

    public void setSourceCity(CityEntity sourceCity) {
        this.sourceCity = sourceCity;
    }
}
