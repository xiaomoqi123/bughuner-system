package bughunter.bughunterserver.vo;

import bughunter.bughunterserver.model.entity.AppBaseInfo;
import bughunter.bughunterserver.model.entity.BugBaseInfo;
import bughunter.bughunterserver.model.entity.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;
import java.sql.Timestamp;

public class BugBaseInfoVO {

    private String bugId;

    private String appVersion;

    private String status;

    private String type;

    private String describe;

    private String current;

    private String priority;

    private UserVO user;

    private String cTime;

    private String mTime;

    private String screenshotAdr;

    public BugBaseInfoVO(BugBaseInfo bugBaseInfo, User user) {
        this.bugId = bugBaseInfo.getBugId();
        this.appVersion = bugBaseInfo.getAppVersion();
        this.status = bugBaseInfo.getStatus();
        this.type = bugBaseInfo.getType();
        this.current = bugBaseInfo.getCurrent();
        this.priority = bugBaseInfo.getPriority();
        this.cTime = bugBaseInfo.getcTime().toString();
        this.mTime = bugBaseInfo.getmTime().toString();
        this.describe = bugBaseInfo.getBugDescribe();
        this.screenshotAdr = bugBaseInfo.getScreenshotAdr();
        if (user != null)
            this.user = new UserVO(user);
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

    public UserVO getUser() {
        return user;
    }

    public void setUser(UserVO user) {
        this.user = user;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getCurrent() {
        return current;
    }

    public void setCurrent(String current) {
        this.current = current;
    }

    public String getBugId() {
        return bugId;
    }

    public void setBugId(String bugId) {
        this.bugId = bugId;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }


    public String getcTime() {
        return cTime;
    }

    public void setcTime(String cTime) {
        this.cTime = cTime;
    }

    public String getmTime() {
        return mTime;
    }

    public void setmTime(String mTime) {
        this.mTime = mTime;
    }

    public String getScreenshotAdr() {
        return screenshotAdr;
    }

    public void setScreenshotAdr(String screenshotAdr) {
        this.screenshotAdr = screenshotAdr;
    }
}
