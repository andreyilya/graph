package graph.ui.web;

import graph.engine.dto.City;
import graph.engine.dto.Road;
import graph.engine.service.api.CityService;
import graph.engine.service.api.RoadService;
import graph.ui.util.Json;
import graph.ui.util.Routes;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

    @Autowired
    private RoadService roadService;

    @RequestMapping(Routes.GRAPH_PAGE)
    public String getGraphPage(Map<String, Object> map) {
        return Routes.GRAPH_PAGE;
    }

    // CHECKSTYLE:OFF
    @RequestMapping(value = Routes.GRAPH_DATA)
    public
    @ResponseBody
    ResponseEntity<String> getGraphData(@PathVariable(Routes.TARGET_ID) String targetId, @PathVariable(Routes.RECURSION_DEPTH) int recursionDepth) {
        // CHECKSTYLE:ON
        return Json.createJsonResponse(cityService.queryGraph(targetId, recursionDepth));
    }

    // CHECKSTYLE:OFF
    @RequestMapping(value = Routes.ADD_NODE,method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> addNode(@ModelAttribute(Routes.CITY) City city, BindingResult result) {
        // CHECKSTYLE:ON
        return Json.createJsonResponse(cityService.save(city));
    }

     // CHECKSTYLE:OFF
    @RequestMapping(value = Routes.DELETE_NODE)
    public
    @ResponseBody
    void deleteNode(@PathVariable(Routes.TARGET_ID) String targetId) {
        // CHECKSTYLE:ON
        cityService.delete(targetId);
    }

    // CHECKSTYLE:OFF
    @RequestMapping(value = Routes.ADD_EDGE, method = RequestMethod.POST)
    public
    @ResponseBody
    ResponseEntity<String> addEdge(@ModelAttribute(Routes.EDGE) Road road, BindingResult result) {
        // CHECKSTYLE:ON
        //TODO: correct save to db
        return Json.createJsonResponse(roadService.save(road));
    }

}
