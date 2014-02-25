package graph.engine.repository.impl;

import graph.engine.common.Direction;
import graph.engine.model.CityEntity;
import graph.engine.model.RoadEntity;
import graph.engine.repository.api.CityRepository;
import graph.engine.repository.api.RoadRepository;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 20.02.14
 * Time: 17:41
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:META-INF/applicationContext.xml"})
@Ignore
public class CityDaoImplTest {
    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private RoadRepository roadRepository;

    @Test
    public void testSaveGraph() {
        CityEntity city1 = new CityEntity();
        city1.setName("city1");


        CityEntity city2 = new CityEntity();
        city2.setName("city2");


        CityEntity city3 = new CityEntity();
        city3.setName("city3");
        CityEntity city4 = new CityEntity();
        city4.setName("city4");

        RoadEntity road1 = new RoadEntity();
        road1.setLenght(1L);
        road1.setSourceCity(city1);
        road1.setTargetCity(city2);
        road1.setDirection(Direction.BOTH);


        RoadEntity road2 = new RoadEntity();
        road2.setLenght(10L);
        road2.setSourceCity(city2);
        road2.setTargetCity(city3);
        road2.setDirection(Direction.BOTH);

        RoadEntity road3 = new RoadEntity();
        road3.setLenght(2L);
        road3.setSourceCity(city3);
        road3.setTargetCity(city1);
        road3.setDirection(Direction.BOTH);

        RoadEntity road4 = new RoadEntity();
        road4.setLenght(4L);
        road4.setSourceCity(city3);
        road4.setTargetCity(city4);
        road4.setDirection(Direction.BOTH);


        cityRepository.save(city1);

        cityRepository.save(city2);
        cityRepository.save(city3);
        cityRepository.save(city4);

        city1.setRoads(Arrays.asList(road1, road2));
        city2.setRoads(Arrays.asList(road2, road3));
        city3.setRoads(Arrays.asList(road2, road3, road4));
        // city3.setRoads(Arrays.asList(road2));
        city4.setRoads(Arrays.asList(road4));

        roadRepository.save(road1);
        roadRepository.save(road2);
        roadRepository.save(road3);
        roadRepository.save(road4);

        cityRepository.save(city1);
        cityRepository.save(city2);
        cityRepository.save(city3);
        cityRepository.save(city4);


    }

    @Test
    @Transactional
    public void testQueryGraph() {
        CityEntity cityEntity = cityRepository.findOne("7660bd91-86ad-4580-bf8c-24d1ad69c711");
        Assert.assertNotNull(cityEntity);
    }

    @Test
    public void testSaveCity() {
        CityEntity cityRight = new CityEntity();
        cityRight.setName("cityRight");
        cityRepository.save(cityRight);
    }
}
