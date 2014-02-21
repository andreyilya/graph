package graph.engine.service.api;

import graph.engine.dto.City;

/**
 * User: a.radkov
 * Date: 21.02.14
 */
public interface CityService extends CrudService<City> {
    City queryGraph(int recursionDepth);
}
