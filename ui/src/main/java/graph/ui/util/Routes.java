package graph.ui.util;

/**
 * Created by IntelliJ IDEA.
 * User: user
 * Date: 22.02.14
 * Time: 15:36
 */
public final class Routes {

    private Routes() {

    }

    //TODO route controller
    public static final String GRAPH_PAGE = "/graph";
    public static final String GRAPH_DATA = "/graph-data/{targetId}/{recursionDepth}";
    public static final String ADD_NODE = "/add-node";
    public static final String TARGET_ID = "targetId";
    public static final String RECURSION_DEPTH = "recursionDepth";
    public static final String CITY = "city";
    public static final String EDGE = "edge";
    public static final String DELETE_NODE = "/delete-node/{targetId}";

    public static final String ADD_EDGE = "/add-edge";


}
