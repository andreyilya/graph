package graph.engine.repository.impl;

import graph.engine.model.CityEntity;
import graph.engine.repository.api.CityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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
	public void testSaveCity(){
        CityEntity cityEntity = new CityEntity();
        cityRepository.save(cityEntity);
	}
}
