package bughunter.bughunterserver.model.entity;

import javax.persistence.*;

/**
 * @author sean
 * @date 2019-03-11.
 */
@Entity
@Table(name = "edge_user")
public class Edge2User {
    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "edge_id")
    private Long edgeId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getEdgeId() {
        return edgeId;
    }

    public void setEdgeId(Long edgeId) {
        this.edgeId = edgeId;
    }
}
