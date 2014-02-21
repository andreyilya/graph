package graph.engine.dto;

import graph.engine.common.Direction;

/**
 * User: a.radkov
 * Date: 20.02.14
 */
public class Road extends Identifiable {

    private Long lenght;

    private String quantity;

    private City leftCity;

    private City rightCity;

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

    public City getRightCity() {
        return rightCity;
    }

    public void setRightCity(City rightCity) {
        this.rightCity = rightCity;
    }

    public City getLeftCity() {
        return leftCity;
    }

    public void setLeftCity(City leftCity) {
        this.leftCity = leftCity;
    }
}
