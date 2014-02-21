package graph.engine.service.impl;

import graph.engine.dto.City;
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
    public City queryGraph(int recursionDepth) {
        return null;
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
