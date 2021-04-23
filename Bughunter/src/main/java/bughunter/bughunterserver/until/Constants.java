package bughunter.bughunterserver.until;

/**
 * 一些全局常量
 */
public class Constants {

    public static final String PSW = "bughunter";

    public static final String ERROR = "error";

    public static final String OKSTR = "ok";

    public static final String ID = "id";

    public static final String USER_ID = "uId";

    public static final String PWD = "pwd";

    public static final String NAME = "name";

    public static final String EMAIL = "email";

    public static final String TeleNumber = "teleNumber";

    public static final int STATUS_ACTIVE = 0;

    public static final int STATUS_NOT_ACTIVE = 1;


    public static final String APP_ID = "appId";

    public static final String TYPE = "type";

    public static final String CREATE_TIME = "cTime";

    public static final String SDK_VERSION = "SDKVersion";

    public static final String APP_KEY = "appKey";

    public static final String APP_Secret = "appSecret";


    public static final String BUG_ID = "bugId";

    public static final String STATUS = "status";

    public static final String PRIORITY = "priority";

    public static final String MODIFY_TIME = "mTime";

    //补充
    public static final String EDGE_ID = "edge_id";

    public static final String INFO_FLAG = "info_flag";

    public static final Long EDGE_NO_EXIST = 0L;

    public static final Integer INFO_FLAG_NO_EXIST = 0;


    public static final String ERROR_NO_EXIST = "no exist";

    public static final String ERROR_EXIST = "exist";

    public static final String ERROR_NO_ACTIVE = "no active";

    public static final String ERROR_PWD = "pwd error";

    public static final String NO_ERROR = "no error";

    public static final String CURRENT_ACTIVITY = "current";

    public static final int USER_NO_EXIST_ID = 0;

    public static final String BUG_STATUS_NEW = "新建";
    public static final String BUG_STATUS_PROCESS = "进行";
    public static final String BUG_STATUS_SOLVE = "解决";
    public static final String BUG_STATUS_CLOSE = "关闭";

    public static final String BUG_TYPE_FUNCTION = "功能错误";
    public static final String BUG_TYPE_INTERFACE = "界面修改";
    public static final String BUG_TYPE_SECURITY = "安全相关";
    public static final String BUG_TYPE_PERFORMANCE = "性能压力";

    public static final String BUG_PRIORITY_CRASH = "崩溃";
    public static final String BUG_PRIORITY_SERIOUS = "严重";
    public static final String BUG_PRIORITY_COMMON = "一般";
    public static final String BUG_PRIORITY_INFERIOR = "次要";

    public static String BUG_BASE_INFO = "BugBaseInfo";
    public static String BUG_CONSOLE_LOG = "BugConsoleLog";
    public static String BUG_DEVICE_INFO = "BugDeviceInfo";
    public static String BUG_NET_REQUEST = "BugNetRequest";
    public static String BUG_OPERATE_STEP = "BugOperateStep";
    public static String BUG_USER_DATA = "BugUserData";

    public static String LOG_STRING = "logString";
    public static String NET_REQUEST = "netRequest";
    public static String OPERATE_STEP = "operateStep";
    public static String DATA_STRING = "dataString";

    //    获取当前手机系统语言
    public static String SYSTEM_LANGUAGE = "systemLanguage";
    //    获取当前手机系统版本号
    public static String SYSTEM_VERSION = "systemVersion";
    //    获取手机型号
    public static String SYSTEM_MODEL = "systemModel";
    //    获取手机厂商
    public static String DEVICE_BRAND = "deviceBrand";
    //    Role:Telecom service providers获取手机服务商信息
    public static String PROVIDERS_NAME = "providersName";
    //    获取屏幕分辨率格式：1024*798
    public static String RESOLUTION = "resolution";
    //    获取android当前可用内存大小
    public static String AVAIL_MEMORY = "availMemory";
    //    获取系统内存的大小
    public static String TOTAL_MEMORY = "totalMemory";
    //    获取手机网络状态
    public static String NETWORK_TYPE = "networkType";
    //截图，图片存储地址
    public static String SCREENSHOT_ADR = "screenshotAdr";

    public static String SCREENSHOT = "screenshot";


    public static String SEND_EMAIL_FROM = "18260067905@163.com";

    public static String VERIFICATION_CODE = "verification_code";

    public static String ERROR_EMAIL = "email do not match id";

    public static String ERROR_VERIFICATION_CODE = "verification code is error";

    public static String DESCRIBE = "describe";

    public static String CREATER = "创建者";
    public static String ORDINARY_MEMBER = "普通成员";

    public static String SCREENSHOT_BASE_URL = "/home/bughunter/screenshot";

    public static String SCREENSHOT_NO_EXIST = "no exist";

    public static String APP_VERSION = "appVersion";
    //使报告时的bug_ID 和图片的文件名保持一致
    public static String bug_id = "";
}
