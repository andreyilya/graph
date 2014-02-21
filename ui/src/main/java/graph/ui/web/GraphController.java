package graph.ui.web;

import java.util.Map;
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

    private static final String USER = "user";
    private static final String USER_ID = "userId";


    @RequestMapping("test")
    public String listUsers(Map<String, Object> map) {
        return "test";
    }


}
