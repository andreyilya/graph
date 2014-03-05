package graph.engine.repository.api;

import graph.engine.model.RoadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * User: a.radkov
 * Date: 20.02.14
 */
public interface RoadRepository extends JpaRepository<RoadEntity, String> {
    @Query("delete from RoadEntity road where road.id in (select road2.id from RoadEntity road2 join road2.targetCity targetCity join road2.sourceCity sourceCity where sourceCity.id = :cityId or targetCity.id= :cityId)")
    @Modifying
    void deleteRoads(@Param("cityId")String cityId);
}
