package bughunter.bughunterserver.model.entity;

import javax.persistence.*;

@Entity(name = "app_version_info")
@IdClass(AppVersionKeys.class)
public class AppVersion {

    private String appKey;

    private String appVersion;


    @Id
    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    @Id
    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

}
