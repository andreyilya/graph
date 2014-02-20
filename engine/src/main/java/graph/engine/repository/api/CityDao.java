package graph.engine.repository.api;

import graph.engine.model.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: a.radkov
 * Date: 20.02.14
 */
public interface CityDao extends JpaRepository<CityEntity, Long> {
}
