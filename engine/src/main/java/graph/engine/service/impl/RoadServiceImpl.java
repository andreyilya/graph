package graph.engine.service.impl;

import graph.engine.converter.RoadConverter;
import graph.engine.dto.Road;
import graph.engine.model.CityEntity;
import graph.engine.model.RoadEntity;
import graph.engine.repository.api.CityRepository;
import graph.engine.repository.api.RoadRepository;
import graph.engine.service.api.RoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: a.radkov
 * Date: 21.02.14
 */
@Service
@Transactional
public class RoadServiceImpl implements RoadService {

    @Autowired
    private RoadRepository roadRepository;

    @Autowired
    private RoadConverter roadConverter;

    @Autowired
    private CityRepository cityRepository;

    @Override
    public Road save(Road road) {
        RoadEntity roadEntity = roadConverter.assemble(road);
        CityEntity targetCityEntity = cityRepository.findOne(road.getTargetCity());
        CityEntity sourceCityEntity = cityRepository.findOne(road.getSourceCity());

        roadEntity.setSourceCity(sourceCityEntity);
        roadEntity.setTargetCity(targetCityEntity);

        targetCityEntity.addRoad(roadEntity);
        sourceCityEntity.addRoad(roadEntity);

        roadRepository.save(roadEntity);
        cityRepository.save(targetCityEntity);
        cityRepository.save(sourceCityEntity);

        return roadConverter.disassemble(roadEntity);
    }

    @Override
    public Road findOne(String id) {
        return roadConverter.disassemble(roadRepository.findOne(id));
    }

    @Override
    public void delete(String id) {
        roadRepository.delete(id);
    }
}
