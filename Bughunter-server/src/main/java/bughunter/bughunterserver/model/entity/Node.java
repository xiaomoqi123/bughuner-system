package bughunter.bughunterserver.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author sean
 * @date 2019-01-22.
 */
@Entity(name = "node")
public class Node {

    private final static int infinite_dis = 1000;

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "app_key")
    private String appKey;

    @Column(name = "window")
    private String window;

    /**
     * ACT
     * DIALOG
     * Launcher
     */
    @Column(name = "type")
    private String type;

    @Column(name = "known")
    private boolean known; //此节点之前是否已知

    @Column(name = "adju_dist")
    private int adjuDist; //此节点距离

    @Column(name = "parent")
    private String parent; //当前从初始节点到此节点的最短路径下，的父节点。

    public Node() {
        this.known = false;
        this.adjuDist = infinite_dis;
        this.parent = null;
    }

    public Node(String name) {
        this.known = false;
        this.adjuDist = infinite_dis;
        this.parent = null;
        this.window = name;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getWindow() {
        return window;
    }

    public void setWindow(String window) {
        this.window = window;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isKnown() {
        return known;
    }

    public void setKnown(boolean known) {
        this.known = known;
    }

    public int getAdjuDist() {
        return adjuDist;
    }

    public void setAdjuDist(int adjuDist) {
        this.adjuDist = adjuDist;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node)) {
            throw new ClassCastException("an object to compare with a Node must be Node");
        }

        if (this.window == null) {
            throw new NullPointerException("name of Node to be compared cannot be null");
        }

        return this.window.equals(((Node) obj).getWindow());
    }

    @Override
    public int hashCode() {
        return this.getWindow().hashCode();
    }

}
