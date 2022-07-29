package bughunter.bughunterserver.model.entity;

import bughunter.bughunterserver.until.Constants;
import org.json.JSONObject;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;

import java.sql.Date;
import java.sql.Timestamp;

public class BugInfo {

    private String appKey;

    public String getAppKey() {
        return appKey;
    }

    private BugBaseInfo bugBaseInfo;

    private BugConsoleLog bugConsoleLog;

    private BugDeviceInfo bugDeviceInfo;

    private BugOperateStep bugOperateStep;

    public BugInfo(JSONObject jsonObject, String screenshotAdr) {

        JSONObject bugBaseInfoJSON = jsonObject.getJSONObject(Constants.BUG_BASE_INFO);
        bugBaseInfo = new BugBaseInfo();
        this.appKey = bugBaseInfoJSON.getString(Constants.APP_KEY);
        bugBaseInfo.setAppKey(appKey);
        bugBaseInfo.setPriority(bugBaseInfoJSON.getString(Constants.PRIORITY));
        bugBaseInfo.setStatus(bugBaseInfoJSON.getString(Constants.STATUS));
        bugBaseInfo.setType(bugBaseInfoJSON.getString(Constants.TYPE));
        bugBaseInfo.setAppVersion(bugBaseInfoJSON.getString(Constants.APP_VERSION));
        bugBaseInfo.setBugDescribe(bugBaseInfoJSON.getString(Constants.DESCRIBE));
        bugBaseInfo.setCurrent(bugBaseInfoJSON.getString(Constants.CURRENT_ACTIVITY));
        bugBaseInfo.setcTime(new Timestamp(System.currentTimeMillis()));
        bugBaseInfo.setmTime(bugBaseInfo.getcTime());
        if (bugBaseInfoJSON.has(Constants.USER_ID))
            bugBaseInfo.setUserId(bugBaseInfoJSON.getInt(Constants.USER_ID));
        else
            bugBaseInfo.setUserId(Constants.USER_NO_EXIST_ID);
        //这两个是后来添加的
        if (bugBaseInfoJSON.has(Constants.EDGE_ID))
            bugBaseInfo.setEdge_id(bugBaseInfoJSON.getLong(Constants.EDGE_ID));
        else
            bugBaseInfo.setEdge_id(Constants.EDGE_NO_EXIST);
        if (bugBaseInfoJSON.has(Constants.INFO_FLAG))
            bugBaseInfo.setInfo_flag(bugBaseInfoJSON.getInt(Constants.INFO_FLAG));
        else
            bugBaseInfo.setInfo_flag(Constants.INFO_FLAG_NO_EXIST);

        bugBaseInfo.setScreenshotAdr(screenshotAdr);
//        int id=bugService.addBug(bugBaseInfo);

        if (jsonObject.has(Constants.BUG_CONSOLE_LOG)) {
            JSONObject bugConsoleLogJSON = jsonObject.getJSONObject(Constants.BUG_CONSOLE_LOG);
            bugConsoleLog = new BugConsoleLog();
            bugConsoleLog.setAppKey(appKey);
            this.bugConsoleLog.setLogString(bugConsoleLogJSON.getString(Constants.LOG_STRING));
        }
        if (jsonObject.has(Constants.BUG_DEVICE_INFO)) {
            JSONObject bugDeviceInfoJSON = jsonObject.getJSONObject(Constants.BUG_DEVICE_INFO);
            bugDeviceInfo = new BugDeviceInfo();
            bugDeviceInfo.setAppKey(appKey);
            this.bugDeviceInfo.setSystemLanguage(bugDeviceInfoJSON.getString(Constants.SYSTEM_LANGUAGE));
            this.bugDeviceInfo.setSystemVersion(bugDeviceInfoJSON.getString(Constants.SYSTEM_VERSION));
            this.bugDeviceInfo.setSystemModel(bugDeviceInfoJSON.getString(Constants.SYSTEM_MODEL));
            this.bugDeviceInfo.setDeviceBrand(bugDeviceInfoJSON.getString(Constants.DEVICE_BRAND));
            this.bugDeviceInfo.setProvidersName(bugDeviceInfoJSON.getString(Constants.PROVIDERS_NAME));
            this.bugDeviceInfo.setResolution(bugDeviceInfoJSON.getString(Constants.RESOLUTION));
            this.bugDeviceInfo.setAvailMemory(bugDeviceInfoJSON.getString(Constants.AVAIL_MEMORY));
            this.bugDeviceInfo.setTotalMemory(bugDeviceInfoJSON.getString(Constants.TOTAL_MEMORY));
            this.bugDeviceInfo.setNetworkType(bugDeviceInfoJSON.getString(Constants.NETWORK_TYPE));
        }
        if (jsonObject.has(Constants.BUG_OPERATE_STEP)) {
            JSONObject bugOperateStepJSON = jsonObject.getJSONObject(Constants.BUG_OPERATE_STEP);
            bugOperateStep = new BugOperateStep();
            bugOperateStep.setAppKey(appKey);
            this.bugOperateStep.setOperateStep(bugOperateStepJSON.getString(Constants.OPERATE_STEP));

        }

    }

    public BugBaseInfo getBugBaseInfo() {
        return bugBaseInfo;
    }

    public BugConsoleLog getBugConsoleLog() {
        return bugConsoleLog;
    }

    public BugDeviceInfo getBugDeviceInfo() {
        return bugDeviceInfo;
    }


    public BugOperateStep getBugOperateStep() {
        return bugOperateStep;
    }


    public void setBugId(String bugId) {
        this.bugBaseInfo.setBugId(bugId);
        if (bugConsoleLog != null)
            this.bugConsoleLog.setBugId(bugId);
        if (bugDeviceInfo != null)
            this.bugDeviceInfo.setBugId(bugId);
        if (bugOperateStep != null)
            this.bugOperateStep.setBugId(bugId);
    }

    public BugInfoKeys getBugInfoKeys() {
        return new BugInfoKeys(bugBaseInfo.getAppKey(), bugBaseInfo.getBugId());
    }

    public void setBugBaseInfo(BugBaseInfo bugBaseInfo) {
        this.bugBaseInfo = bugBaseInfo;
    }

    public void setBugConsoleLog(BugConsoleLog bugConsoleLog) {
        this.bugConsoleLog = bugConsoleLog;
    }

    public void setBugDeviceInfo(BugDeviceInfo bugDeviceInfo) {
        this.bugDeviceInfo = bugDeviceInfo;
    }

    public void setBugOperateStep(BugOperateStep bugOperateStep) {
        this.bugOperateStep = bugOperateStep;
    }

}
