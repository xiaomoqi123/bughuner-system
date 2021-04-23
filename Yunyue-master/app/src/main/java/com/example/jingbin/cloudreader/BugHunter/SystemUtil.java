//package org.horaapps.leafpic.BuggerHunter;
package com.example.jingbin.cloudreader.BugHunter;
import android.Manifest;
import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;
import android.text.format.Formatter;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Locale;

public class SystemUtil {
    /**
     * 获取当前手机系统语言。
     *
     * @return 返回当前系统语言。例如：当前设置的是“中文-中国”，则返回“zh-CN”
     */
    public static String getSystemLanguage() {
        return Locale.getDefault().getLanguage();
    }

    /**
     * 获取当前手机系统版本号
     *
     * @return  系统版本号
     */
    public static String getSystemVersion() {
        return Build.VERSION.RELEASE;
    }

    /**
     * 获取手机型号
     *
     * @return  手机型号
     */
    public static String getSystemModel() {
        return Build.MODEL;
    }

    /**
     * 获取手机厂商
     *
     * @return  手机厂商
     */
    public static String getDeviceBrand() {
        return Build.BRAND;
    }

    // /**
    //  * 获取手机IMEI(需要“android.permission.READ_PHONE_STATE”权限)
    //  *
    //  * @return  手机IMEI
    //  */
    // public static String getIMEI(Context ctx) {
    //     TelephonyManager tm = (TelephonyManager) ctx.getSystemService(Activity.TELEPHONY_SERVICE);
    //     if (tm != null) {
    //         return tm.getDeviceId();
    //     }
    //     return null;
    // }

    /*
     * Role:Telecom service providers获取手机服务商信息
     * 需要加入权限<uses-permission
     * android:name="android.permission.READ_PHONE_STATE"/> <BR>
     */
    public static String getProvidersName(Context context) {
        String[] permissions = {Manifest.permission.READ_PHONE_STATE};
        String ProvidersName = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

            // 检查该权限是否已经获取
            int i = ContextCompat.checkSelfPermission(context, permissions[0]);
            // 权限是否已经 授权 GRANTED---授权  DINIED---拒绝
            if (i != PackageManager.PERMISSION_GRANTED) {
                // 如果没有授予该权限，就去提示用户请求
                Toast.makeText(context,"请开启手机状态权限",Toast.LENGTH_SHORT);
            }
        }
        // 返回唯一的用户ID;就是这张卡的编号神马的
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        String ope = tm.getSubscriberId();
        if (ope == null || ope.equals("")) {
            return ProvidersName;
        }
        // IMSI号前面3位460是国家，紧接着后面2位00 02是中国移动，01是中国联通，03是中国电信。
        if (ope.startsWith("46000") || ope.startsWith("46002")) {
            ProvidersName = "中国移动";
        } else if (ope.startsWith("46001")) {
            ProvidersName = "中国联通";
        } else if (ope.startsWith("46003")) {
            ProvidersName = "中国电信";
        }
        return ProvidersName;
    }

    /*
    /*
    *获取屏幕分辨率,格式：1080*1920
    */
    public static String getResolution(Context context){
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        int height =dm.heightPixels;
        String result= String.valueOf(width)+"*"+String.valueOf(height);
        return result;
    }

    public static int getResolutionWidth(Context context){
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();
        int width = dm.widthPixels;
        return width;
    }

    public static int getResolutionHeight(Context context){
        DisplayMetrics dm = new DisplayMetrics();
        dm = context.getResources().getDisplayMetrics();
        int height = dm.heightPixels;
        return height;
    }
    /*
        获取android当前可用内存大小
    */
    public static String getAvailMemory(Context context) {

        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        am.getMemoryInfo(mi);
        //mi.availMem; 当前系统的可用内存

        return Formatter.formatFileSize(context, mi.availMem);// 将获取的内存大小规格化
    }

  	/*
  	获取系统内存的大小
  	 */

    public static String getTotalMemory(Context context) {
        String str1 = "/proc/meminfo";// 系统内存信息文件
        String str2;
        String[] arrayOfString;
        long initial_memory = 0;

        try {
            FileReader localFileReader = new FileReader(str1);
            BufferedReader localBufferedReader = new BufferedReader(
                    localFileReader, 8192);
            str2 = localBufferedReader.readLine();// 读取meminfo第一行，系统总内存大小

            arrayOfString = str2.split("\\s+");
            for (String num : arrayOfString) {
                Log.i(str2, num + "\t");
            }

            initial_memory = Integer.valueOf(arrayOfString[1]).intValue() * 1024;// 获得系统总内存，单位是KB，乘以1024转换为Byte
            localBufferedReader.close();

        } catch (IOException e) {
        }
        return Formatter.formatFileSize(context, initial_memory);// Byte转换为KB或者MB，内存大小规格化
    }

  	/*
  	获取手机网络状态
  	 */

    public static String GetNetworkType(Context context){
        String strNetworkType = "";
        ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = mConnectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected())
        {
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI)
            {
                strNetworkType = "WIFI";
            }
            else if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE)
            {
                String _strSubTypeName = networkInfo.getSubtypeName();

                Log.e("cocos2d-x", "Network getSubtypeName : " + _strSubTypeName);

                // TD-SCDMA   networkType is 17
                int networkType = networkInfo.getSubtype();
                switch (networkType) {
                    case TelephonyManager.NETWORK_TYPE_GPRS:
                    case TelephonyManager.NETWORK_TYPE_EDGE:
                    case TelephonyManager.NETWORK_TYPE_CDMA:
                    case TelephonyManager.NETWORK_TYPE_1xRTT:
                    case TelephonyManager.NETWORK_TYPE_IDEN: //api<8 : replace by 11
                        strNetworkType = "2G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_UMTS:
                    case TelephonyManager.NETWORK_TYPE_EVDO_0:
                    case TelephonyManager.NETWORK_TYPE_EVDO_A:
                    case TelephonyManager.NETWORK_TYPE_HSDPA:
                    case TelephonyManager.NETWORK_TYPE_HSUPA:
                    case TelephonyManager.NETWORK_TYPE_HSPA:
                    case TelephonyManager.NETWORK_TYPE_EVDO_B: //api<9 : replace by 14
                    case TelephonyManager.NETWORK_TYPE_EHRPD:  //api<11 : replace by 12
                    case TelephonyManager.NETWORK_TYPE_HSPAP:  //api<13 : replace by 15
                        strNetworkType = "3G";
                        break;
                    case TelephonyManager.NETWORK_TYPE_LTE:    //api<11 : replace by 13
                        strNetworkType = "4G";
                        break;
                    default:
                        // http://baike.baidu.com/item/TD-SCDMA 中国移动 联通 电信 三种3G制式
                        if (_strSubTypeName.equalsIgnoreCase("TD-SCDMA") || _strSubTypeName.equalsIgnoreCase("WCDMA") || _strSubTypeName.equalsIgnoreCase("CDMA2000"))
                        {
                            strNetworkType = "3G";
                        }
                        else
                        {
                            strNetworkType = _strSubTypeName;
                        }

                        break;
                }

                Log.e("cocos2d-x", "Network getSubtype : " + Integer.valueOf(networkType).toString());
            }
        }

        Log.e("cocos2d-x", "Network Type : " + strNetworkType);

        return strNetworkType;
    }


}
