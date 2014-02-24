package graph.engine.dto;

import graph.engine.common.Direction;

/**
 * User: a.radkov
 * Date: 20.02.14
 */
public class Road extends Identifiable {

    private Long roadLength;

    private String quantity;

    private String sourceCity;

    private String targetCity;

    private Direction direction;

    public Long getRoadLength() {
        return roadLength;
    }

    public void setRoadLength(Long roadLength) {
        this.roadLength = roadLength;
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

    public String getTargetCity() {
        return targetCity;
    }

    public void setTargetCity(String targetCity) {
        this.targetCity = targetCity;
    }

    public String getSourceCity() {
        return sourceCity;
    }

    public void setSourceCity(String sourceCity) {
        this.sourceCity = sourceCity;
    }
}
