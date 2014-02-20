package graph.engine.repository.impl;

import graph.engine.model.CityEntity;
import graph.engine.repository.api.CityDao;
import graph.engine.repository.jpa.JpaSupport;

/**
 * User: a.radkov
 * Date: 20.02.14
 */
public class CityRepositoryImpl extends JpaSupport<CityEntity, String> implements CityDao {
}
