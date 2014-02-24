package graph.engine.converter;

import graph.engine.dto.Road;
import graph.engine.model.RoadEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: a.radkov
 * Date: 24.02.14
 */
@Component
public class RoadConverter extends AbstractConverter<Road, RoadEntity> {

    @Autowired
    private CityConverter cityConverter;

    @Override
    public RoadEntity assemble(Road road) {
        return null;
    }

    @Override
    public Road disassemble(RoadEntity roadEntity) {
        Road road = new Road();
        road.setId(roadEntity.getId());
        road.setRoadLength(roadEntity.getLenght());
        road.setQuantity(roadEntity.getQuantity());
        road.setDirection(roadEntity.getDirection());
        road.setSourceCity(cityConverter.disassemble(roadEntity.getSourceCity()));
        road.setTargetCity(cityConverter.disassemble(roadEntity.getTargetCity()));

        return road;
    }
}
