package bughunter.bughunterserver.model.entity;


import javax.persistence.*;

@Entity(name = "app_member")
@IdClass(AppMemberKeys.class)
public class AppMember {

    private String appKey;

    private int userId;

    private String type;

    @Id
    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    @Id
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
