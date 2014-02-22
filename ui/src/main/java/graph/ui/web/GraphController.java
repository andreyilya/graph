package graph.ui.web;

import graph.engine.service.api.CityService;
import graph.ui.util.Routes;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 25.08.12
 * Time: 23:26
 */
@Controller
public class GraphController {

	@Autowired
	private CityService cityService;

	@RequestMapping(Routes.GRAPH_PAGE)
	public String getGraphPage(Map<String, Object> map) {
		return Routes.GRAPH_PAGE;
	}

	@RequestMapping(value = Routes.GRAPH_DATA)
	public
	@ResponseBody
	String getGrahpData() {
		return "{\n" +
				"            \"nodes\": [\n" +
				"                {\"name\": \"node_1\"},\n" +
				"                {\"name\": \"node_2\"},\n" +
				"                {\"name\": \"node_3\"},\n" +
				"                {\"name\": \"node_4\"},\n" +
				"                {\"name\": \"node_5\"},\n" +
				"                {\"name\": \"node_6\"},\n" +
				"                {\"name\": \"node_7\"},\n" +
				"                {\"name\": \"node_8\"},\n" +
				"                {\"name\": \"node_9\"},\n" +
				"                {\"name\": \"node_10\"}\n" +
				"            ],\n" +
				"            \"edges\": [\n" +
				"                {\"src\": \"node_3\", \"dest\": \"node_2\"},\n" +
				"                {\"src\": \"node_5\", \"dest\": \"node_3\"},\n" +
				"                {\"src\": \"node_8\", \"dest\": \"node_7\"},\n" +
				"                {\"src\": \"node_1\", \"dest\": \"node_4\"},\n" +
				"                {\"src\": \"node_7\", \"dest\": \"node_5\"},\n" +
				"                {\"src\": \"node_3\", \"dest\": \"node_9\"},\n" +
				"                {\"src\": \"node_2\", \"dest\": \"node_4\"},\n" +
				"                {\"src\": \"node_6\", \"dest\": \"node_5\"},\n" +
				"                {\"src\": \"node_9\", \"dest\": \"node_1\"},\n" +
				"                {\"src\": \"node_10\", \"dest\": \"node_2\"},\n" +
				"                {\"src\": \"node_1\", \"dest\": \"node_10\"}\n" +
				"            ]\n" +
				"        }";
	}


}
