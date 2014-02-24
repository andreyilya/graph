package graph.ui.web;

import graph.engine.dto.City;
import graph.engine.service.api.CityService;
import graph.ui.util.Json;
import graph.ui.util.Routes;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    // CHECKSTYLE:OFF
    @RequestMapping(value = Routes.GRAPH_DATA)
    public
    @ResponseBody
    ResponseEntity<String> getGrahpData() {
        // CHECKSTYLE:ON
        return Json.createJsonResponse(cityService.queryGraph(new City(), 2));
    }

}
