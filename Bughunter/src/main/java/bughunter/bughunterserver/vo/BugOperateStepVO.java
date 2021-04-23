package bughunter.bughunterserver.vo;

import bughunter.bughunterserver.model.entity.BugOperateStep;

public class BugOperateStepVO {

    private String bugId;

    private String[] operateStep;

    public BugOperateStepVO(BugOperateStep bugOperateStep) {
        this.bugId = bugOperateStep.getBugId();
        this.operateStep = bugOperateStep.getOperateStep().split("\\\\n");
    }

    public String getBugId() {
        return bugId;
    }

    public void setBugId(String bugId) {
        this.bugId = bugId;
    }

    public String[] getOperateStep() {
        return operateStep;
    }

    public void setOperateStep(String[] operateStep) {
        this.operateStep = operateStep;
    }
}
