package graph.engine.converter;

import graph.engine.dto.CityGraph;
import graph.engine.model.CityEntity;
import org.springframework.stereotype.Component;

/**
 * User: a.radkov
 * Date: 24.02.14
 */
@Component
public class GraphConverter {
    public CityGraph disassemble(CityEntity cityEntity, int recursionDepth) {
        CityGraph cityGraph = new CityGraph();
        return updateGraph(cityGraph,cityEntity,recursionDepth);
    }

    private CityGraph updateGraph(CityGraph cityGraph, CityEntity cityEntity, int recursionDepth) {
        //recursion here
        return cityGraph;
    }
}
