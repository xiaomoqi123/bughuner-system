package bughunter.bughunterserver.vo;


import bughunter.bughunterserver.model.entity.AppBaseInfo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Date;


public class AppBaseInfoVO {

    private String name;

    private String type;

    private Date cTime;

    private Date mTime;

    private double SDKVersion;

    private String appKey;

    private String appSecret;

    private int versionAmount;

    private int bugSubmitAmount;

    public AppBaseInfoVO(AppBaseInfo appBaseInfo) {
        this.name = appBaseInfo.getName();
        this.type = appBaseInfo.getType();
        this.cTime = appBaseInfo.getcTime();
        this.SDKVersion = appBaseInfo.getSDKVersion();
        this.appKey = appBaseInfo.getAppKey();
        this.appSecret = appBaseInfo.getAppSecret();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSDKVersion() {
        return SDKVersion;
    }

    public void setSDKVersion(double SDKVersion) {
        this.SDKVersion = SDKVersion;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public Date getcTime() {
        return cTime;
    }

    public void setcTime(Date cTime) {
        this.cTime = cTime;
    }

    public Date getmTime() {
        return mTime;
    }

    public void setmTime(Date mTime) {
        this.mTime = mTime;
    }

    public int getVersionAmount() {
        return versionAmount;
    }

    public void setVersionAmount(int versionAmount) {
        this.versionAmount = versionAmount;
    }

    public int getBugSubmitAmount() {
        return bugSubmitAmount;
    }

    public void setBugSubmitAmount(int bugSubmitAmount) {
        this.bugSubmitAmount = bugSubmitAmount;
    }
}
