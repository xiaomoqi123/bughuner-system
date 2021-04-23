package bughunter.bughunterserver.DTO;

import bughunter.bughunterserver.model.entity.Edge;
import bughunter.bughunterserver.model.entity.Node;

import java.util.HashMap;
import java.util.List;

/**
 * @author sean
 * @date 2019-01-30.
 */
public class GraphDTO {

    private List<Node> nodeDTOs;   //图的顶点集
    private HashMap<Node, List<Edge>> node_edgeList_map;  //图的每个顶点对应的有向边
    private String appKey;

    public GraphDTO(List<Node> vertexList, HashMap<Node, List<Edge>> ver_edgeList_map, String appKey) {
        super();
        this.nodeDTOs = vertexList;
        this.appKey = appKey;
        this.node_edgeList_map = ver_edgeList_map;
    }

    public List<Node> getNodeDTOs() {
        return nodeDTOs;
    }

    public void setNodeDTOs(List<Node> nodeDTOs) {
        this.nodeDTOs = nodeDTOs;
    }

    public HashMap<Node, List<Edge>> getNode_edgeList_map() {
        return node_edgeList_map;
    }

    public void setNode_edgeList_map(HashMap<Node, List<Edge>> node_edgeList_map) {
        this.node_edgeList_map = node_edgeList_map;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

}
