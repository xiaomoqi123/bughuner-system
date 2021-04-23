package bughunter.bughunterserver.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author sean
 * @date 2019-01-22.
 */
@Entity
@Table(name = "edge")
public class Edge {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "source_node")
    private String sourceNode;

    @Column(name = "target_node")
    private String targetNode;

    @Column(name = "event_handlers")
    private String eventHandlers;

    @Column(name = "event_type")
    private String eventType;

    @Column(name = "number")
    private Integer number;

    private static int weight = 1;
    /**
     * 0:操作序列
     * 1:测试异常
     * 2:GATOR
     */
    @Column(name = "data_type")
    private Integer dataType;

    @Column(name = "app_key")
    private String appKey;

    @Column(name = "create_time")
    private Timestamp createTime;

    @Column(name = "assist_time")
    private Integer assistTime;

    @Column(name = "message")
    private String message = "Exception";

    @Column(name = "image_url")
    private String imageUrl = "test";

    @Column(name = "path")
    private String path;


    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSourceNode() {
        return sourceNode;
    }

    public void setSourceNode(String sourceNode) {
        this.sourceNode = sourceNode;
    }

    public String getTargetNode() {
        return targetNode;
    }

    public void setTargetNode(String targetNode) {
        this.targetNode = targetNode;
    }

    public String getEventHandlers() {
        return eventHandlers;
    }

    public void setEventHandlers(String eventHandlers) {
        this.eventHandlers = eventHandlers;
    }

    public Integer getDataType() {
        return dataType;
    }

    public void setDataType(Integer dataType) {
        this.dataType = dataType;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public static int getWeight() {
        return weight;
    }

    public static void setWeight(int weight) {
        Edge.weight = weight;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Integer getAssistTime() {
        return assistTime;
    }

    public void setAssistTime(Integer assistTime) {
        this.assistTime = assistTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public String toString() {
        return id + ":" + sourceNode + "->" + targetNode + ":" + eventHandlers;
    }
}
