package bughunter.bughunterserver.model.entity;

import java.io.Serializable;

public class BugInfoKeys implements Serializable {

    private String appKey;

    private String bugId;

    public BugInfoKeys() {
    }

    public BugInfoKeys(String appKey, String bugId) {
        this.appKey = appKey;
        this.bugId = bugId;
    }

    @Override
    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = PRIME * result + ((appKey == null) ? 0 : appKey.hashCode());
        result = PRIME * result + ((bugId == null) ? 0 : bugId.hashCode());
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

        final BugInfoKeys other = (BugInfoKeys) obj;
        if (appKey == null) {
            if (other.appKey != null) {
                return false;
            }
        } else if (!appKey.equals(other.appKey)) {
            return false;
        }
        if (bugId == null) {
            if (other.bugId != null) {
                return false;
            }
        } else if (!bugId.equals(other.bugId)) {
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

    public String getBugId() {
        return bugId;
    }

    public void setBugId(String bugId) {
        this.bugId = bugId;
    }
}
