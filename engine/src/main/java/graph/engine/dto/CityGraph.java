package graph.engine.dto;

import graph.engine.model.CityEntity;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 23.02.14
 * Time: 8:55
 */
public class CityGraph {
    private List<City> nodes = new ArrayList<>();

    private List<Road> edges = new ArrayList<>();

    public CityGraph addNode(City city) {
        if (!nodes.contains(city)) {
            nodes.add(city);
        }
        return this;
    }

    public CityGraph addEdge(Road road) {
        if (!edges.contains(road)) {
            edges.add(road);
        }
        return this;
    }

    public CityGraph addEdges(List<Road> roads) {
        for (Road road : roads) {
            if (!edges.contains(road)) {
                edges.add(road);
            }
        }
        return this;
    }

    public List<City> getNodes() {
        return nodes;
    }

    public List<Road> getEdges() {
        return edges;
    }

    public boolean containsNode(CityEntity targetCity) {
        City city = new City();
        city.setId(targetCity.getId());
        return nodes.contains(city);
    }
}
