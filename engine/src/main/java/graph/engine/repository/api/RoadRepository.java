package graph.engine.repository.api;

import graph.engine.model.RoadEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * User: a.radkov
 * Date: 20.02.14
 */
public interface RoadRepository extends JpaRepository<RoadEntity, String>, CityDao {
}
