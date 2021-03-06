package graph.engine.converter;

import graph.engine.dto.Road;
import graph.engine.model.RoadEntity;
import org.springframework.stereotype.Component;

/**
 * User: a.radkov
 * Date: 24.02.14
 */
@Component
public class RoadConverter extends AbstractConverter<Road, RoadEntity> {

    @Override
    public RoadEntity assemble(Road road) {
        RoadEntity roadEntity = new RoadEntity();
        roadEntity.setId(road.getId());
        roadEntity.setLength(road.getRoadLength());
        roadEntity.setQuantity(road.getQuantity());
        roadEntity.setDirection(road.getDirection());
        return roadEntity;
    }

    @Override
    public Road disassemble(RoadEntity roadEntity) {
        Road road = new Road();
        road.setId(roadEntity.getId());
        road.setRoadLength(roadEntity.getLength());
        road.setQuantity(roadEntity.getQuantity());
        road.setDirection(roadEntity.getDirection());
        if (roadEntity.getSourceCity() != null) {
            road.setSourceCity(roadEntity.getSourceCity().getId());
        }
        if (roadEntity.getTargetCity() != null) {
            road.setTargetCity(roadEntity.getTargetCity().getId());
        }

        return road;
    }
}
