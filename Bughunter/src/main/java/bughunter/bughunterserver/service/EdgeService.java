package bughunter.bughunterserver.service;

import bughunter.bughunterserver.model.entity.Edge;
import bughunter.bughunterserver.vo.EdgeVO;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author sean
 * @date 2019-01-23.
 */

public interface EdgeService {

    Edge save(Edge edge);

    Edge getNextBugHint(String currentWindow, String nextWindow, Long edgeId);

    //自己写推荐2
    EdgeVO getNextBugHint_2(String currentWindow, String nextWindow, Long edgeId);

    //自己写推荐3
    EdgeVO getNextBugHint_3(String currentWindow, String nextWindow, Long edgeId);

    List<EdgeVO> getRecommBugs(String appKey, String currentWindow, Integer isCovered, Integer userId);

    //自己写的
    List<EdgeVO> getRecommBugs_2(String appKey, String currentWindow, Integer isCovered, Integer userId);

    List<Edge> getEdgeBySourceNodeAndTargetNode(String sourceNode, String targetNode);

    List<Edge> getRecommActivities(String appKey, String currentWindow);

    List<Edge> getBugEdgeBySourceNodeAndTargetNode(String currentWindow, String window);

    Edge updateEdge(Long id);

    Edge getEdgesByCreateTime(Timestamp createTime);

    Edge getEdgeByCreateTimeAndAssistTime(Timestamp createTime, int standard);

    List<Edge> getEdgesByAppKey(String jianDou);


    List<Edge> getEdgesByAppKeyAndDataType(String appKey, int i);

    Edge getNextHint(String currentWindow, String nextWindow);
}
