package graph.engine.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 23.02.14
 * Time: 8:55
 */
public class CityGraph {
    private List<City> nodes;

    private List<Road> edges;

    public CityGraph addNode(City city) {
        if (nodes == null) {
            nodes = new ArrayList<>();
        }
        if (!nodes.contains(city)) {
            nodes.add(city);
        }
        return this;
    }

    public CityGraph addEdge(Road road) {
        if (edges == null) {
            edges = new ArrayList<>();
        }
        if (!edges.contains(road)) {
            edges.add(road);
        }
        return this;
    }

    public CityGraph addEdges(List<Road> roads) {
        if (edges == null) {
            edges = new ArrayList<>();
        }
        for (Road road : roads) {
            if (!edges.contains(road)) {
                edges.add(road);
            }
        }
        return this;
    }

    public void setNodes(List<City> nodes) {
        this.nodes = nodes;
    }

    public void setEdges(List<Road> edges) {
        this.edges = edges;
    }

    public List<City> getNodes() {
        return nodes;
    }

    public List<Road> getEdges() {
        return edges;
    }
}
