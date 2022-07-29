package bughunter.bughunterserver.vo;


import bughunter.bughunterserver.model.entity.BugDeviceInfo;

import javax.persistence.Entity;


public class BugDeviceInfoVO {

    private String bugId;

    //    获取当前手机系统语言
    private String systemLanguage;
    //    获取当前手机系统版本号
    private String systemVersion;
    //    获取手机型号
    private String systemModel;
    //    获取手机厂商
    private String deviceBrand;
    //    Role:Telecom service providers获取手机服务商信息
    private String providersName;
    //    获取屏幕分辨率格式：1024*798
    private String resolution;
    //    获取android当前可用内存大小
    private String availMemory;
    //    获取系统内存的大小
    private String totalMemory;
    //    获取手机网络状态
    private String networkType;

    //截图，图片存储地址
    private String screenshotAdr;


    public BugDeviceInfoVO(BugDeviceInfo bugDeviceInfo) {
        this.bugId = bugDeviceInfo.getBugId();
        this.systemLanguage = bugDeviceInfo.getSystemLanguage();
        this.systemVersion = bugDeviceInfo.getSystemLanguage();
        //    获取手机型号
        this.systemModel = bugDeviceInfo.getSystemModel();
        //    获取手机厂商
        this.deviceBrand = bugDeviceInfo.getDeviceBrand();
        //    Role:Telecom service providers获取手机服务商信息
        this.providersName = bugDeviceInfo.getProvidersName();
        //    获取屏幕分辨率格式：1024*798
        this.resolution = bugDeviceInfo.getResolution();
        //    获取android当前可用内存大小
        this.availMemory = bugDeviceInfo.getAvailMemory();
        //    获取系统内存的大小
        this.totalMemory = bugDeviceInfo.getTotalMemory();
        //    获取手机网络状态
        this.networkType = bugDeviceInfo.getNetworkType();
    }

    public String getBugId() {
        return bugId;
    }

    public void setBugId(String bugId) {
        this.bugId = bugId;
    }

    public String getSystemLanguage() {
        return systemLanguage;
    }

    public void setSystemLanguage(String systemLanguage) {
        this.systemLanguage = systemLanguage;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion;
    }

    public String getSystemModel() {
        return systemModel;
    }

    public void setSystemModel(String systemModel) {
        this.systemModel = systemModel;
    }

    public String getDeviceBrand() {
        return deviceBrand;
    }

    public void setDeviceBrand(String deviceBrand) {
        this.deviceBrand = deviceBrand;
    }

    public String getProvidersName() {
        return providersName;
    }

    public void setProvidersName(String providersName) {
        this.providersName = providersName;
    }

    public String getResolution() {
        return resolution;
    }

    public void setResolution(String resolution) {
        this.resolution = resolution;
    }

    public String getAvailMemory() {
        return availMemory;
    }

    public void setAvailMemory(String availMemory) {
        this.availMemory = availMemory;
    }

    public String getTotalMemory() {
        return totalMemory;
    }

    public void setTotalMemory(String totalMemory) {
        this.totalMemory = totalMemory;
    }

    public String getNetworkType() {
        return networkType;
    }

    public void setNetworkType(String networkType) {
        this.networkType = networkType;
    }

    public String getScreenshotAdr() {
        return screenshotAdr;
    }

    public void setScreenshotAdr(String screenshotAdr) {
        this.screenshotAdr = screenshotAdr;
    }

}
