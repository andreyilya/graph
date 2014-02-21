package graph.ui.web;

import graph.engine.service.api.CityService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @RequestMapping("/graph")
    public String getGraphPage(Map<String, Object> map) {
        return "/graph";
    }


}
