package graph.engine.model;

import javax.persistence.CascadeType;
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
public class RoadEntity extends Identifiable {

    @Column
    private Long lenght;

    @Column
    private String quantity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private CityEntity leftCity;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private CityEntity rightCity;

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

    public CityEntity getRightCity() {
        return rightCity;
    }

    public void setRightCity(CityEntity rightCity) {
        this.rightCity = rightCity;
    }

    public CityEntity getLeftCity() {
        return leftCity;
    }

    public void setLeftCity(CityEntity leftCity) {
        this.leftCity = leftCity;
    }
}
