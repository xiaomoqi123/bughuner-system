package bughunter.bughunterserver.dao;

import bughunter.bughunterserver.model.entity.Edge;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author sean
 * @date 2019-01-23.
 */
@Transactional
public interface EdgeDao extends CrudRepository<Edge, Long> {

    @Query("SELECT e FROM Edge e WHERE e.appKey = :appKey " +
            "AND e.dataType = :dataType " +
            "ORDER BY e.number*0.2+weight*0.8 DESC")
    List<Edge> findByAppKeyAndDataType(@Param("appKey") String appKey,
                                       @Param("dataType") Integer dateType);


    @Query("SELECT e FROM Edge e WHERE e.sourceNode = :sourceWindow " +
            "AND e.targetNode = :targetWindow " +
            "ORDER BY e.number DESC")
    List<Edge> findBySourceNodeAndTargetNodeOrderByNumber(@Param("sourceWindow") String sourceWindow,
                                                          @Param("targetWindow") String targetWindow);

    //根据终点，找到起点，把所有的起点放进list中
    @Query("SELECT e FROM Edge e WHERE e.targetNode = :targetWindow ")
    List<Edge> findByTargetNode(@Param("targetWindow") String targetWindow);

    //    @Query("SELECT e FROM edge e WHERE e.sourceNode = :sourceNode and e.targetNode = :targetNode " +
//            "and e.callbacks = :callbacks and e.appKey = :appKey")
    Edge findBySourceNodeAndTargetNodeAndEventHandlersAndAppKey(String sourceNode, String targetNode, String eventHandlers, String appKey);

    @Query("select e from Edge e where e.sourceNode = :sourceWindow " +
            "and e.targetNode = :targetWindow" +
            " and e.dataType = :dataType " +
            "and e.number < 5 " +
            "ORDER BY e.number DESC")
    List<Edge> findBySourceNodeAndTargetNodeAndDataTypeOrderByNumber(
            @Param("sourceWindow") String sourceWindow,
            @Param("targetWindow") String targetWindow,
            @Param("dataType") int i);

    List<Edge> findByAppKey(String appKey);

    List<Edge> findBySourceNode(String window);

    Edge findByCreateTime(Timestamp createTime);

    Edge findByCreateTimeAndAssistTime(Timestamp createTime, int standard);

    Edge findById(long id);

    List<Edge> findBySourceNodeAndTargetNode(String currentWindow, String nextWindow);
}
