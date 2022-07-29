package com.example.jingbin.cloudreader.BugHunter;

public class EdgeInfo {

    private String id;

    private String sourceNode;

    private String targetNode;

    private String eventHandlers;

    private String eventType;

    private Integer number;

    private Integer dataType;

    private String appKey;

    private String createTime;

    private Integer assistTime;

    private String message;

    private String path;

    private String imageUrl;

    private Integer count = 0;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
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

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public EdgeInfo(String id, String sourceNode, String targetNode, String eventHandlers, String message, String path, String imageUrl) {
        this.id = id;
        this.sourceNode = sourceNode;
        this.targetNode = targetNode;
        this.eventHandlers = eventHandlers;
        this.message = message;
        this.imageUrl = imageUrl;
        this.path = path;
    }
}
