package graph.engine.service.impl;

import graph.engine.converter.CityConverter;
import graph.engine.converter.GraphConverter;
import graph.engine.dto.City;
import graph.engine.dto.CityGraph;
import graph.engine.dto.Road;
import graph.engine.model.CityEntity;
import graph.engine.repository.api.CityRepository;
import graph.engine.repository.api.RoadRepository;
import graph.engine.service.api.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: a.radkov
 * Date: 21.02.14
 */
@Service
public class CityServiceImpl implements CityService {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RoadRepository roadRepository;

    @Autowired
    private GraphConverter graphConverter;

    @Autowired
    private CityConverter cityConverter;

    @Override
    @Transactional
    public CityGraph queryGraph(String targetPoint, int recursionDepth) {
        CityEntity cityEntity = cityRepository.findOne(targetPoint);
        return graphConverter.disassemble(cityEntity, recursionDepth);
        // return getStaticCityGraph();
    }

    @Override
    @Transactional
    public CityGraph getRoute(String targetId, String sourceId) {
        int recursionDepth = 10;
        CityEntity cityEntity = cityRepository.findOne(targetId);
        CityGraph cityGraph = graphConverter.disassemble(cityEntity, recursionDepth);
        return createRoute(cityGraph,recursionDepth);
    }

    private CityGraph createRoute(CityGraph cityGraph, int recursionDepth) {
        return cityGraph;
    }


    @Override
    public City save(City city) {
        CityEntity cityEntity = cityConverter.assemble(city);
        return cityConverter.disassemble(cityRepository.save(cityEntity));
    }

    @Override
    public City findOne(String id) {
        return cityConverter.disassemble(cityRepository.findOne(id));

    }

    @Override
    @Transactional
    public void delete(String id) {

        roadRepository.deleteRoads(id);
        cityRepository.delete(id);
    }
}
