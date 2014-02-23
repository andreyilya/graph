package graph.engine.service.impl;

import graph.engine.dto.City;
import graph.engine.dto.CityGraph;
import graph.engine.dto.Road;
import graph.engine.repository.api.CityRepository;
import graph.engine.service.api.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: a.radkov
 * Date: 21.02.14
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Override
    public CityGraph queryGraph(City targetPoint, int recursionDepth) {
		City city1 = new City();
		city1.setName("city1");

		City city2 = new City();
		city2.setName("city2");

		City city3 = new City();
		city3.setName("city3");
		CityGraph cityGraph = new CityGraph();

		cityGraph.addNode(city1);
		cityGraph.addNode(city2);
		cityGraph.addNode(city3);

		Road road1 = new Road();
		road1.setLenght(1L);
		road1.setSourceCity(city1);
		road1.setTargetCity(city2);

		Road road2 = new Road();
		road2.setLenght(1L);

		road2.setSourceCity(city2);
		road2.setTargetCity(city3);

		Road road3 = new Road();
		road3.setLenght(1L);
		road3.setSourceCity(city3);
		road3.setTargetCity(city1);

		cityGraph.addEdge(road1);
		cityGraph.addEdge(road2);
		cityGraph.addEdge(road3);

		return cityGraph;
    }

    @Override
    public City save(City city) {
        return null;
    }

    @Override
    public City findOne(String id) {
        return null;
    }
}
