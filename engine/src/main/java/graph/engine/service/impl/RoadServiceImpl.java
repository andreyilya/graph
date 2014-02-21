package graph.engine.service.impl;

import graph.engine.dto.Road;
import graph.engine.repository.api.RoadRepository;
import graph.engine.service.api.RoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * User: a.radkov
 * Date: 21.02.14
 */
@Service
public class RoadServiceImpl implements RoadService {

    @Autowired
    private RoadRepository roadRepository;

    @Override
    public Road save(Road city) {
        return null;
    }

    @Override
    public Road findOne(String id) {
        return null;
    }
}
