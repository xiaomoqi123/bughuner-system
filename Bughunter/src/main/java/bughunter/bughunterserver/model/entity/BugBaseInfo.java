package bughunter.bughunterserver.model.entity;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity(name = "app_bug_info")
@IdClass(BugInfoKeys.class)
public class BugBaseInfo {

    private String bugId;

    private String appKey;

    private String appVersion;

    private String status;

    private String type;

    private String bugDescribe;

    private String current;

    private String priority;

    private int userId;

    private User user;

    private Timestamp cTime;

    private Timestamp mTime;

    //截图，图片存储地址
    private String screenshotAdr;

    //记录测试的edge_id
    private Long edge_id;

    public Long getEdge_id() {
        return edge_id;
    }

    public void setEdge_id(Long edge_id) {
        this.edge_id = edge_id;
    }

    public Integer getInfo_flag() {
        return info_flag;
    }

    public void setInfo_flag(Integer info_flag) {
        this.info_flag = info_flag;
    }

    //区分是通过复现bug提交的报告，还是自己在使用过程中遇到异常时提交的报告
    private Integer info_flag;


    @Id
    public String getBugId() {
        return bugId;
    }

    public void setBugId(String bugId) {
        this.bugId = bugId;
    }


    @Id
    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getBugDescribe() {
        return bugDescribe;
    }

    public void setBugDescribe(String bugDescribe) {
        this.bugDescribe = bugDescribe;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getScreenshotAdr() {
        return screenshotAdr;
    }

    public void setScreenshotAdr(String screenshotAdr) {
        this.screenshotAdr = screenshotAdr;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

    public Timestamp getcTime() {
        return cTime;
    }

    public void setcTime(Timestamp cTime) {
        this.cTime = cTime;
    }

    public Timestamp getmTime() {
        return mTime;
    }

    public void setmTime(Timestamp mTime) {
        this.mTime = mTime;
    }
}
