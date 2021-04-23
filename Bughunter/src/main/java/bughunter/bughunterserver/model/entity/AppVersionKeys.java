package bughunter.bughunterserver.model.entity;

import javax.persistence.Id;
import java.io.Serializable;

public class AppVersionKeys implements Serializable {

    private String appKey;

    private String appVersion;

    public AppVersionKeys() {
    }

    public AppVersionKeys(String appKey, String appVersion) {
        this.appKey = appKey;
        this.appVersion = appVersion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AppVersionKeys that = (AppVersionKeys) o;

        if (appKey != null ? !appKey.equals(that.appKey) : that.appKey != null) return false;
        return appVersion != null ? appVersion.equals(that.appVersion) : that.appVersion == null;
    }

    @Override
    public int hashCode() {
        int result = appKey != null ? appKey.hashCode() : 0;
        result = 31 * result + (appVersion != null ? appVersion.hashCode() : 0);
        return result;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public String getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(String appVersion) {
        this.appVersion = appVersion;
    }

}
