package bughunter.bughunterserver.vo;

import bughunter.bughunterserver.model.entity.BugConsoleLog;

public class BugConsoleLogVO {

    private String bugId;

    private String logString;


    public BugConsoleLogVO(BugConsoleLog bugConsoleLog) {
        this.bugId = bugConsoleLog.getBugId();
        this.logString = bugConsoleLog.getLogString();
    }


    public String getBugId() {
        return bugId;
    }

    public void setBugId(String bugId) {
        this.bugId = bugId;
    }

    public String getLogString() {
        return logString;
    }

    public void setLogString(String logString) {
        this.logString = logString;
    }
}
