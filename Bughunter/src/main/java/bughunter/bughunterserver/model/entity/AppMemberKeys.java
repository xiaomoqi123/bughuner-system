package bughunter.bughunterserver.model.entity;

import java.io.Serializable;


public class AppMemberKeys implements Serializable {

    private String appKey;

    private Integer userId;

    public AppMemberKeys() {
    }

    public AppMemberKeys(String appKey, Integer userId) {
        this.appKey = appKey;
        this.userId = userId;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((appKey == null) ? 0 : appKey.hashCode());
        result = PRIME * result + ((userId == null) ? 0 : userId.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }

        final AppMemberKeys other = (AppMemberKeys) obj;
        if (appKey == null) {
            if (other.appKey != null) {
                return false;
            }
        } else if (!appKey.equals(other.appKey)) {
            return false;
        }
        if (userId == null) {
            if (other.userId != null) {
                return false;
            }
        } else if (!userId.equals(other.userId)) {
            return false;
        }

        return true;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }
}
