package graph.engine.repository.impl;

import graph.engine.model.CityEntity;
import graph.engine.model.Direction;
import graph.engine.model.RoadEntity;
import graph.engine.repository.api.CityRepository;
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
public class CityDaoImplTest {
    @Autowired
    private CityRepository cityRepository;

    @Test
    public void testSaveGraph() {
        CityEntity cityRight = new CityEntity();
        cityRight.setName("cityRight");
        CityEntity cityLeft = new CityEntity();
        cityLeft.setName("cityLeft");
        RoadEntity roadEntity = new RoadEntity();
        roadEntity.setLenght(1L);
        roadEntity.setLeftCity(cityLeft);
        roadEntity.setRightCity(cityRight);
        roadEntity.setDirection(Direction.BOTH);
        cityRight.setRoads(Arrays.asList(roadEntity));
        cityRepository.save(cityRight);
    }

    @Test
    @Transactional
    @Ignore(value = "uncorrect id")
    public void testQueryGraph() {
        CityEntity cityEntity = cityRepository.findOne("6185c287-ad09-455c-a621-a824664097bf");
        Assert.assertNotNull(cityEntity);
    }

    @Test
    public void testSaveCity() {
        CityEntity cityRight = new CityEntity();
        cityRight.setName("cityRight");
        cityRepository.save(cityRight);
    }
}
