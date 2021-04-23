package bughunter.bughunterserver.model.entity;


import javax.persistence.*;

@Entity(name = "bug_device_info")
@IdClass(BugInfoKeys.class)
public class BugDeviceInfo {


    private String bugId;

    private String appKey;

    // 当前手机系统语言
    private String systemLanguage;
    //当前手机系统版本号
    private String systemVersion;
    // 手机型号
    private String systemModel;
    // 手机厂商
    private String deviceBrand;
    // 手机服务商信息
    private String providersName;
    // 屏幕分辨率格式：1024*798
    private String resolution;
    // android当前可用内存大小
    private String availMemory;
    // 系统内存的大小
    private String totalMemory;
    // 手机网络状态
    private String networkType;


    @Id
    public String getBugId() {
        return bugId;
    }

    public void setBugId(String bugId) {
        this.bugId = bugId;
    }

    @Id
    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
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

}
