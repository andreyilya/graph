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

	public CityGraph addNode(City city){
		if(nodes ==null) {
			nodes = new ArrayList<>();
		}
		nodes.add(city);
		return this;
	}

	public CityGraph addEdge(Road road){
		if(edges ==null) {
			edges = new ArrayList<>();
		}
		edges.add(road);
		return this;
	}

	public List<City> getNodes() {
		return nodes;
	}

	public List<Road> getEdges() {
		return edges;
	}
}
