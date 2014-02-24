package graph.engine.service.api;

import graph.engine.dto.City;
import graph.engine.dto.CityGraph;

/**
 * User: a.radkov
 * Date: 21.02.14
 */
public interface CityService extends CrudService<City> {
    CityGraph queryGraph(City targetPoint, int recursionDepth);
}
