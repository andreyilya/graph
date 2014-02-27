package graph.engine.converter;

import graph.engine.dto.CityGraph;
import graph.engine.model.CityEntity;
import graph.engine.model.RoadEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: a.radkov
 * Date: 24.02.14
 */
@Component
public class GraphConverter {
    @Autowired
    private CityConverter cityConverter;

    @Autowired
    private RoadConverter roadConverter;


    public CityGraph disassemble(CityEntity cityEntity, int recursionDepth) {
        CityGraph cityGraph = new CityGraph();
        return updateGraph(cityGraph, cityEntity, recursionDepth);
    }

    private CityGraph updateGraph(CityGraph cityGraph, CityEntity cityEntity, int recursionDepth) {
        if (recursionDepth > 0) {
            cityGraph.addNode(cityConverter.disassemble(cityEntity));
            cityGraph.addEdges(roadConverter.disassembleList(cityEntity.getRoads()));
            for (RoadEntity roadEntity : cityEntity.getRoads()) {
                if (roadEntity.getTargetCity() != null && !cityGraph.containsNode(roadEntity.getTargetCity())) {
                    updateGraph(cityGraph, roadEntity.getTargetCity(), recursionDepth - 1);
                }
                if (roadEntity.getSourceCity() != null && !cityGraph.containsNode(roadEntity.getSourceCity())) {
                    updateGraph(cityGraph, roadEntity.getSourceCity(), recursionDepth - 1);
                }
            }
        }
        //TODO: clear edges without any city
        return cityGraph;
    }
}
