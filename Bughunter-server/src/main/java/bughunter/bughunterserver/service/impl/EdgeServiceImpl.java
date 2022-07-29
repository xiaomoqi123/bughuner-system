package bughunter.bughunterserver.service.impl;

import bughunter.bughunterserver.DTO.GraphDTO;
import bughunter.bughunterserver.dao.Edge2UserDao;
import bughunter.bughunterserver.dao.EdgeDao;
import bughunter.bughunterserver.dao.NodeDao;
import bughunter.bughunterserver.model.entity.Edge;
import bughunter.bughunterserver.model.entity.Edge2User;
import bughunter.bughunterserver.model.entity.Node;
import bughunter.bughunterserver.service.EdgeService;
import bughunter.bughunterserver.vo.EdgeVO;
import bughunter.bughunterserver.wrapper.EdgeVOWrapper;
import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author sean
 * @date 2019-01-23.
 */
@Service
public class EdgeServiceImpl implements EdgeService {

    public static int CROWD_WORKER_NUMBER = 100;

    @Autowired
    private EdgeDao edgeDao;

    @Autowired
    private NodeDao nodeDao;

    @Autowired
    private Edge2UserDao edge2UserDao;

    @Autowired
    private EdgeVOWrapper edgeVOWrapper;

    @Override
    public Edge save(Edge edge) {
        return edgeDao.save(edge);
    }


    @Override
    public Edge getNextBugHint(String currentWindow, String nextWindow, Long edgeId) {
        List<Edge> edges = edgeDao.findBySourceNodeAndTargetNode(currentWindow, nextWindow);
        edges = edges.stream().filter(edge -> edge.getId() < edgeId).collect(Collectors.toList());
        edges.sort((x, y) -> Integer.compare(x.getNumber(), y.getNumber()));//这方法需要jdk1.8以上
        return edges.get(edges.size() - 1);
    }

    //改进版，这样应该能够正常进行推荐了
    public EdgeVO getNextBugHint_3(String currentWindow, String nextWindow, Long edgeId) {
        Edge edge = edgeDao.findById(edgeId);
        if (currentWindow.equals(nextWindow)) {
            return edgeVOWrapper.wrap(edge);
        } else {
            List<String> list = new ArrayList<String>();
            list.add(nextWindow);
            int i = 0;
            while (i < list.size()) {
                List<Edge> temp = edgeDao.findByTargetNode(list.get(i));
                for (Edge e : temp) {
                    String temp_source = e.getSourceNode();
                    if (temp_source.equals(currentWindow)) {
                        return edgeVOWrapper.wrap(e);
                    } else {
                        list.add(temp_source);
                        i++;
                    }
                }
                // 死循环了
                if (i > 100) {
                    break;
                }
            }
            return edgeVOWrapper.wrap(edge);
        }

    }

    //自己写一个推荐的东西进来看看
    public EdgeVO getNextBugHint_2(String currentWindow, String nextWindow, Long edgeId) {
        if (currentWindow.equals(nextWindow)) {
            Edge edge = edgeDao.findById(edgeId);
            return edgeVOWrapper.wrap(edge);
        } else {
            List<Edge> edges = edgeDao.findBySourceNodeAndTargetNode(currentWindow, nextWindow);
            Edge temp = new Edge();
            Date max_date = new Date(1999 - 10 - 10);
            for (Edge e : edges) {
                if (!e.getCreateTime().before(max_date)) {
                    System.out.println(e + "-----------------------------");
                    max_date = e.getCreateTime();
                    temp = e;
                }
            }
            return edgeVOWrapper.wrap(temp);

        }

    }

    //自己写一个试一试
    public List<EdgeVO> getRecommBugs_2(String appKey, String currentWindow, Integer isCovered, Integer userId) {
        List<Edge> edges = edgeDao.findByAppKeyAndDataType(appKey, isCovered);
        List<EdgeVO> edgeVOs = new ArrayList<>();
        System.out.println("=====================================" + edges);

        if (isCovered == 0) {
            int i = 0;
            for (Edge e : edges) {
                Edge2User byUserIdAndEdgeId = edge2UserDao.findByUserIdAndEdgeId(userId, e.getId());
                if (byUserIdAndEdgeId == null) {
                    if (e.getSourceNode().equals(currentWindow)) {
                        List<String> list = new ArrayList();
                        list.add(e.getTargetNode());
                        list.add(e.getSourceNode());
                        EdgeVO edgeVO = edgeVOWrapper.wrap(e);
                        edgeVO.setPath(e.getSourceNode() + " -> " + e.getTargetNode());
                        edgeVOs.add(edgeVO);
                        i++;
                        if (i > 3) {
                            break;
                        }
                    }
                }
            }

        } else {
            int i = 0;
            Set<List<String>> set = new HashSet<>();
            for (Edge e : edges) {
                Edge2User byUserIdAndEdgeId = edge2UserDao.findByUserIdAndEdgeId(userId, e.getId());
                if (byUserIdAndEdgeId == null) {
                    List<String> list = new ArrayList();
                    list.add(e.getTargetNode());
                    list.add(e.getSourceNode());
                    if (!set.contains(list)) {
                        set.add(list);
                        EdgeVO edgeVO = edgeVOWrapper.wrap(e);
                        edgeVO.setPath(e.getSourceNode() + " -> " + e.getTargetNode());
                        edgeVOs.add(edgeVO);
                        i++;
//                        if(i>5)
//                        {
//
//                            break;
//                        }
                    }

                }
            }
        }
//        return edgeVOWrapper.wrap(edges);
        return edgeVOs;
    }


    @Override
    public List<EdgeVO> getRecommBugs(String appKey, String currentWindow, Integer isCovered, Integer userId) {
//        List<Edge> edges = edgeDao.findByAppKeyAndDataType(appKey, isCovered);
//        List<EdgeVO> edgeVOs = new ArrayList<>();
//        for(Edge e: edges){
//            EdgeVO edgeVO = edgeVOWrapper.wrap(e);
//            edgeVO.setPath(e.getSourceNode() + " -> " +e.getTargetNode());
//        }
//        return edgeVOWrapper.wrap(edges);

        //当前用户所在节点
        Node currNode = nodeDao.findByWindow(currentWindow);
        HashMap<Node, List<Edge>> map = new HashMap<>();
        //待测App所有覆盖边的目标节点
        List<Node> nodeList = nodeDao.findByAppKey(appKey);
        List<Edge> edgeList = new ArrayList<>();
        for (Node node : nodeList) {
            List<Edge> handledEdges = new ArrayList<>();
            //节点对应的有向边
            List<Edge> edges = edgeDao.findBySourceNode(node.getWindow());
            //两个节点一个方向下,只能存在一条有向边
            for (Edge edge : edges) {
                if (handledEdges.stream().noneMatch(edge1 -> edge1.getSourceNode().equals(edge.getSourceNode())
                        && edge1.getTargetNode().equals(edge.getTargetNode()))) {
                    //去环
                    if (!edge.getSourceNode().equals(edge.getTargetNode())) {
                        edgeList.add(edge);
                        handledEdges.add(edge);
                    }

                }
            }
            List<Edge> resultEdge = new ArrayList<>();
            for (Edge e : handledEdges) {
                if (edgeList.stream().noneMatch(edge -> edge.getTargetNode().equals(e.getSourceNode())
                        && edge.getSourceNode().equals(e.getTargetNode()))) {
                    resultEdge.add(e);
                }
            }
            map.put(node, resultEdge);
        }

        System.out.println("----------------------------------------------------------");
        System.out.println(map);


        GraphDTO graphDTO = new GraphDTO(nodeList, map, appKey);

        List<List<Node>> recommNodes = new ArrayList<>();
        //寻找测试用例/普通跳转
        List<Edge> edgesContainsTC = edgeDao.findByAppKeyAndDataType(appKey, isCovered);
        List<Node> nodes = new ArrayList<>();
        int z = 0;
        for (Edge edge : edgesContainsTC) {
            Node node = nodeDao.findByWindow(edge.getTargetNode());
            if (nodes.stream().noneMatch(node1 -> node1.equals(node))) {
                nodes.add(node);
            }
        }
        //当前节点到目标节点的最短路径
        List<Edge> targetEdges = edgeDao.findByAppKeyAndDataType(appKey, isCovered);
        List<String> destNodes = targetEdges.stream().map(edge -> edge.getSourceNode()).collect(Collectors.toList());
        for (String n : destNodes) {
            if (!n.equals(currentWindow)) {
                int startIndex = nodeList.indexOf(nodeDao.findByWindow(currentWindow));
                int destIndex = nodeList.indexOf(nodeDao.findByWindow(n));
                List<Node> pathNodes = dijkstraTravasal(startIndex, destIndex, graphDTO);
                //排除不可达节点
                if (pathNodes.size() != 1 || pathNodes.get(0).equals(currNode)) {
                    recommNodes.add(pathNodes);
                }
            }

        }

        List<EdgeVO> results = new ArrayList<>();
        List<EdgeVO> resultSelfEdges = new ArrayList<>();
        //先是当前页面自身到自身的边
        List<Edge> selfEdges = edgeDao.findBySourceNodeAndTargetNodeAndDataTypeOrderByNumber
                (currentWindow, currentWindow, isCovered);
        if (selfEdges != null && selfEdges.size() != 0) {
            for (Edge edge : selfEdges) {
                EdgeVO edgeVO = edgeVOWrapper.wrap(edge);
                edgeVO.setPath(currentWindow + "->" + currentWindow);
                if (edge2UserDao.findByUserIdAndEdgeId(userId, edge.getId()) == null)
                    resultSelfEdges.add(edgeVO);
            }
        }
        int selfSize = resultSelfEdges.size();
        int size = 5 - selfSize;
        resultSelfEdges.sort((x, y) -> Integer.compare(x.getNumber(), y.getNumber()));//这方法需要jdk1.8以上
        if (selfSize >= 5) {
            results.add(resultSelfEdges.get(selfSize - 1));
            results.add(resultSelfEdges.get(selfSize - 2));
            results.add(resultSelfEdges.get(selfSize - 3));
            results.add(resultSelfEdges.get(selfSize - 4));
            results.add(resultSelfEdges.get(selfSize - 5));
        } else {
            results.addAll(resultSelfEdges);
            List<EdgeVO> resultEdges = new ArrayList<>();
            for (List<Node> nodePath : recommNodes) {
                StringBuilder temp = new StringBuilder();
                for (Node node : nodePath) {
                    temp.append(node.getWindow() + "->");
                }
                String path = temp.toString().substring(0, temp.length() - 3);
                //排除不可达节点
                if (nodePath.size() != 1 || nodePath.get(0).equals(currNode)) {
                    List<Edge> recommEdges;
                    Node sourceNode;
                    Node targetNode;

                    //可达节点自身到自身的边
                    if (nodePath.size() == 1 && nodePath.get(0).equals(currNode)) {
                        sourceNode = currNode;
                        targetNode = currNode;
                    } else {
                        //正常情况
                        sourceNode = nodePath.get(nodePath.size() - 2);
                        targetNode = nodePath.get(nodePath.size() - 1);
                    }
                    recommEdges = edgeDao.findBySourceNodeAndTargetNodeAndDataTypeOrderByNumber(
                            sourceNode.getWindow(), targetNode.getWindow(), isCovered);
                    recommEdges.stream().filter(edge -> edge.getNumber() < 0.6 * CROWD_WORKER_NUMBER);

                    //该用户还没验证过
                    for (Edge e : recommEdges) {
                        if (edge2UserDao.findByUserIdAndEdgeId(userId, e.getId()) == null) {
                            EdgeVO edgeVO = edgeVOWrapper.wrap(e);
                            edgeVO.setPath(path);
                            resultEdges.add(edgeVO);
                        }
                    }

                }
            }

            if (resultEdges.size() == 0 && resultEdges == null) {
                List<Edge> edges = edgeDao.findByAppKeyAndDataType(appKey, isCovered);
                for (Edge e : edges) {
                    if (edge2UserDao.findByUserIdAndEdgeId(userId, e.getId()) == null) {
                        EdgeVO edgeVO = edgeVOWrapper.wrap(e);
                        edgeVO.setPath(e.getSourceNode() + "->" + e.getTargetNode());
                        resultEdges.add(edgeVO);
                    }
                }
            }
            resultEdges.sort((x, y) -> Integer.compare(x.getNumber(), y.getNumber()));
            if (resultEdges.size() > size) {
                for (int i = 0; i < size; i++) {
                    results.add(resultEdges.get(resultEdges.size() - 1 - i));
                }
            } else
                results.addAll(resultEdges);
            if (isCovered == 0 && resultEdges.size() < 5) {
                List<EdgeVO> edges = edgeVOWrapper.wrap(edgeDao.findByAppKeyAndDataType(appKey, 0));
                for (EdgeVO edgeVO : edges) {
                    edgeVO.setPath(edgeVO.getSourceNode() + "->" + edgeVO.getTargetNode());
                }
                results = edges;
            }
        }
        return results;
    }


    @Override
    public List<Edge> getEdgeBySourceNodeAndTargetNode(String sourceNode, String targetNode) {
        return edgeDao.findBySourceNodeAndTargetNodeOrderByNumber(sourceNode, targetNode);
    }

    @Override
    public List<Edge> getRecommActivities(String appKey, String currentWindow) {
        Node node = nodeDao.findByWindow(currentWindow);

        return null;
    }

    @Override
    public List<Edge> getBugEdgeBySourceNodeAndTargetNode(String currentWindow, String window) {
        return edgeDao.findBySourceNodeAndTargetNodeAndDataTypeOrderByNumber(currentWindow, window, 1);
    }


    //设置起始节点
    public void setRoot(Node v) {
        v.setParent(null);
        v.setAdjuDist(0);
        nodeDao.save(v);
    }

    public List<Node> dijkstraTravasal(int startIndex, int destIndex, GraphDTO graphDTO) {
        Node start = graphDTO.getNodeDTOs().get(startIndex);
        Node dest = graphDTO.getNodeDTOs().get(destIndex);

        String path = "[" + dest.getWindow() + "]";

        setRoot(start);
        updateChildren(graphDTO.getNodeDTOs().get(startIndex), graphDTO);

        int shortest_length = dest.getAdjuDist();

        List<Node> result = new ArrayList<>();
        start = nodeDao.findByWindow(start.getWindow());
        dest = nodeDao.findByWindow(dest.getWindow());
        result.add(start);
        while ((dest.getParent() != null) && (!dest.equals(start))) {
            result.add(dest);
            path = "[" + dest.getParent() + "] --> " + path;
            dest = nodeDao.findByWindow(dest.getParent());
        }

        System.out.println("[" + start.getWindow() + "] to [" +
                dest.getWindow() + "] dijkstra shortest path :: " + path);
        System.out.println("shortest length::" + shortest_length);
        return result;
    }

    /**
     * 从初始节点开始递归更新邻接表
     *
     * @param v
     */
    private void updateChildren(Node v, GraphDTO graphDTO) {

        HashMap<Node, List<Edge>> map = graphDTO.getNode_edgeList_map();
        if (v == null) {
            return;
        }

        if (map.get(v) == null || map.get(v).size() == 0) {
            return;
        }
        //用来保存每个可达的节点
        List<Node> childrenList = new LinkedList<Node>();
        for (Edge e : map.get(v)) {
            Node node = nodeDao.findByWindow(e.getTargetNode());

            //如果子节点之前未知，则进行初始化，
            //把当前边的开始点默认为子节点的父节点，长度默认为边长加边的起始节点的长度，并修改该点为已经添加过，表示不用初始化
            if (!node.isKnown()) {
                node.setKnown(true);
                node.setAdjuDist(v.getAdjuDist() + e.getWeight());
                node.setParent(v.getWindow());
                nodeDao.save(node);
                childrenList.add(node);
            }

            //此时该子节点的父节点和之前到该节点的最小长度已经知道了，则比较该边起始节点到该点的距离是否小于子节点的长度，
            //只有小于的情况下，才更新该点为该子节点父节点,并且更新长度。
            int nowDist = v.getAdjuDist() + e.getWeight();
            if (nowDist >= node.getAdjuDist()) {
                continue;
            } else {
                node.setAdjuDist(nowDist);
                node.setParent(v.getWindow());
            }
        }

        //更新每一个子节点
        for (Node vc : childrenList) {
            updateChildren(vc, graphDTO);
        }
    }

    @Override
    public Edge updateEdge(Long id) {
        Edge edge = edgeDao.findOne(id);
        edge.setNumber(edge.getNumber() + 1);
        edge = edgeDao.save(edge);
        return edge;
    }


    @Override
    public Edge getEdgesByCreateTime(Timestamp createTime) {
        return edgeDao.findByCreateTime(createTime);
    }

    @Override
    public Edge getEdgeByCreateTimeAndAssistTime(Timestamp createTime, int standard) {
        return edgeDao.findByCreateTimeAndAssistTime(createTime, standard);
    }

    @Override
    public List<Edge> getEdgesByAppKey(String appKey) {
        return edgeDao.findByAppKey(appKey);
    }

    @Override
    public List<Edge> getEdgesByAppKeyAndDataType(String appKey, int i) {
        return edgeDao.findByAppKeyAndDataType(appKey, i);
    }

    @Override
    public Edge getNextHint(String currentWindow, String nextWindow) {
        List<Edge> edges = edgeDao.findBySourceNodeAndTargetNode(currentWindow, nextWindow);
        edges = edges.stream().filter(edge -> edge.getDataType() == 1).collect(Collectors.toList());
        return edges.get(0);
    }
}
