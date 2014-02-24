package graph.engine.converter;

import graph.engine.dto.City;
import graph.engine.model.CityEntity;
import org.springframework.stereotype.Component;

/**
 * User: a.radkov
 * Date: 24.02.14
 */
@Component
public class CityConverter extends AbstractConverter<City, CityEntity> {
    @Override
    public CityEntity assemble(City city) {
        return null;
    }

    @Override
    public City disassemble(CityEntity cityEntity) {
        City city = new City();
        city.setId(cityEntity.getId());
        city.setName(cityEntity.getName());
        city.setPopulation(cityEntity.getPopulation());
        city.setName(cityEntity.getName());
        return city;
    }
}
