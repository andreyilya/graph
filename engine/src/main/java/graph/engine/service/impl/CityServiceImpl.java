package graph.engine.service.impl;

import graph.engine.converter.GraphConverter;
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

    @Autowired
    private GraphConverter graphConverter;

    @Override
    public CityGraph queryGraph(City targetPoint, int recursionDepth) {
//        CityEntity cityEntity = cityRepository.findOne(targetPoint.getId());
        //  return graphConverter.disassemble(cityEntity, recursionDepth);
        return getStaticCityGraph();
    }

    private CityGraph getStaticCityGraph() {
        City city1 = new City();
        city1.setName("city1");
        city1.setId("city1");

        City city2 = new City();
        city2.setName("city2");
        city2.setId("city2");


        City city3 = new City();
        city3.setName("city3");
        city3.setId("city3");


        City city4 = new City();
        city4.setName("city4");
        city4.setId("city4");

        CityGraph cityGraph = new CityGraph();


        cityGraph.addNode(city1);
        cityGraph.addNode(city2);
        cityGraph.addNode(city3);
        cityGraph.addNode(city4);

        Road road1 = new Road();
        road1.setRoadLength(4L);
        road1.setSourceCity(city1.getId());
        road1.setTargetCity(city2.getId());

        Road road2 = new Road();
        road2.setRoadLength(12L);

        road2.setSourceCity(city2.getId());
        road2.setTargetCity(city3.getId());

        Road road3 = new Road();
        road3.setRoadLength(12L);
        road3.setSourceCity(city3.getId());
        road3.setTargetCity(city1.getId());

        Road road4 = new Road();
        road4.setRoadLength(15L);
        road4.setSourceCity(city3.getId());
        road4.setTargetCity(city4.getId());

        cityGraph.addEdge(road1);
        cityGraph.addEdge(road2);
        cityGraph.addEdge(road3);
        cityGraph.addEdge(road4);

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
