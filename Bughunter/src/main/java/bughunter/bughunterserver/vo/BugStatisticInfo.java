package bughunter.bughunterserver.vo;

import org.json.JSONObject;

import java.util.Map;

public class BugStatisticInfo {

    //bug数量
    private int bugAmount;

    //user数量
    private int userAmount;

    //bug解决率
    private float bugSolve;

    //b崩溃次数
    private int crashAmount;

    //崩溃影响用户数
    private int crashUserAmount;

    //崩溃影响机型数量
    private int crashDeviceAmount;

    //近两周崩溃统计
    private Map<String, Integer> bugRecentAmount;

    //
    private int[] bugStatus = new int[4];

    private int[] bugType = new int[4];

    private int[] bugPriority = new int[4];


    public int getBugAmount() {
        return bugAmount;
    }

    public void setBugAmount(int bugAmount) {
        this.bugAmount = bugAmount;
    }

    public int getUserAmount() {
        return userAmount;
    }

    public void setUserAmount(int userAmount) {
        this.userAmount = userAmount;
    }

    public float getBugSolve() {
        return bugSolve;
    }

    public void setBugSolve(float bugSolve) {
        this.bugSolve = bugSolve;
    }

    public int getCrashAmount() {
        return crashAmount;
    }

    public void setCrashAmount(int crashAmount) {
        this.crashAmount = crashAmount;
    }

    public int getCrashUserAmount() {
        return crashUserAmount;
    }

    public void setCrashUserAmount(int crashUserAmount) {
        this.crashUserAmount = crashUserAmount;
    }

    public int[] getBugStatus() {
        return bugStatus;
    }

    public void setBugStatus(int[] bugStatus) {
        this.bugStatus = bugStatus;
    }

    public int[] getBugType() {
        return bugType;
    }

    public void setBugType(int[] bugType) {
        this.bugType = bugType;
    }

    public int[] getBugPriority() {
        return bugPriority;
    }

    public void setBugPriority(int[] bugPriority) {
        this.bugPriority = bugPriority;
    }

    public int getCrashDeviceAmount() {
        return crashDeviceAmount;
    }

    public void setCrashDeviceAmount(int crashDeviceAmount) {
        this.crashDeviceAmount = crashDeviceAmount;
    }

    public Map<String, Integer> getBugRecentAmount() {
        return bugRecentAmount;
    }

    public void setBugRecentAmount(Map<String, Integer> bugRecentAmount) {
        this.bugRecentAmount = bugRecentAmount;
    }
}
