package com.example.jingbin.cloudreader.BugHunter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jingbin.cloudreader.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Response;

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    // 系统默认的UncaughtException处理类
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    // CrashHandler实例
    private static CrashHandler INSTANCE = new CrashHandler();
    // 程序的Context对象
    private Context mContext;

    private HashMap<String, String> infos = new HashMap<String, String>();

    private DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss SSS");


    //保证只有一个CrashHandler实例
    private CrashHandler() {

    }

    //获取CrashHandler实例 ,单例模式
    public static CrashHandler getInstance() {
        if (INSTANCE == null)
            INSTANCE = new CrashHandler();
        return INSTANCE;
    }

    /**
     * 初始化
     *
     * @param context
     */
    public void init(Context context) {
        mContext = context;
        // 获取系统默认的UncaughtException处理器
        mDefaultHandler = Thread.getDefaultUncaughtExceptionHandler();
        // 设置该CrashHandler为程序的默认处理器
        Thread.setDefaultUncaughtExceptionHandler(this);
    }

    /**
     * 当UncaughtException发生时会转入该重写的方法来处理
     */
    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        if (!handleException(ex) && mDefaultHandler != null) {
            // 如果自定义的没有处理则让系统默认的异常处理器来处理
            Log.i("flaotingButtonAction", "uncaughtException: handle by system ");
            mDefaultHandler.uncaughtException(thread, ex);
        }
    }

    /**
     * 自定义错误处理,收集错误信息 发送错误报告等操作均在此完成.
     *
     * @param ex 异常信息
     * @return true 如果处理了该异常信息;否则返回false.
     */
    public boolean handleException(Throwable ex) {
        if (ex == null || mContext == null)
            return false;
//        final String crashReport = getCrashReport(mContext, ex);
//
        Log.i("floatingButtonAction","handleException: 正常");
//        return true;
        final String crashReport = getCrashReport(mContext, ex);
        Log.i(crashReport, "CrashHandler捕获的异常");
        new Thread() {
            public void run() {
                Looper.prepare();

                sendAppCrashReport(mContext, crashReport);
                Looper.loop();
            }

        }.start();


        return true;
    }

    public void sendAppCrashReport(Context mContext, String crashReport) {
        Toast.makeText(mContext, "bug捕获到了", Toast.LENGTH_SHORT).show();
        Log.i(crashReport, "CrashHandler捕获的异常");
        JSONObject submitjson = new JSONObject();
        JSONObject bugbaseinfo = new JSONObject();
        JSONObject bugconsolelog = new JSONObject();
        JSONObject bugdeviceinfo = new JSONObject();
        JSONObject bugoperatestep = new JSONObject();
        Log.e("ExceptionRecord", crashReport);
        try {
            bugconsolelog.put("logString", crashReport);
            submitjson.put("BugConsoleLog", bugconsolelog);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            bugbaseinfo.put("appKey", Appkey.getApplicationMetaData(mContext.getApplicationContext()));
            bugbaseinfo.put("appVersion", Appkey.getVersionName(mContext.getApplicationContext()));
            bugbaseinfo.put("describe", "app crashed");
            bugbaseinfo.put("uId", FloatingActionButton.uid);
            Activity a = FloatingActionButton.getCurrentActivity();
            if (a != null) {
                bugbaseinfo.put("current", FloatingActionButton.getCurrentActivity().getClass().getName());
            } else {
                bugbaseinfo.put("current", "UnknownActivity");
            }
            bugbaseinfo.put("status", "New");
            bugbaseinfo.put("priority", "Crash");
            bugbaseinfo.put("type", "Security");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            submitjson.put("BugBaseInfo", bugbaseinfo);
            bugdeviceinfo.put("systemLanguage", SystemUtil.getSystemLanguage());
            bugdeviceinfo.put("systemVersion", SystemUtil.getSystemVersion());
            bugdeviceinfo.put("systemModel", SystemUtil.getSystemModel());
            bugdeviceinfo.put("deviceBrand", SystemUtil.getDeviceBrand());
            bugdeviceinfo.put("providersName", "中国移动");
            bugdeviceinfo.put("resolution", SystemUtil.getResolution(mContext.getApplicationContext()));
            bugdeviceinfo.put("availMemory", SystemUtil.getAvailMemory(mContext.getApplicationContext()));
            bugdeviceinfo.put("totalMemory", SystemUtil.getTotalMemory(mContext.getApplicationContext()));
            bugdeviceinfo.put("networkType", SystemUtil.GetNetworkType(mContext.getApplicationContext()));
            submitjson.put("BugDeviceInfo", bugdeviceinfo);

            //操作步骤
            bugoperatestep.put("operateStep", "null");
            submitjson.put("BugOperateStep", bugoperatestep);
            Log.e("json", submitjson.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        OkHttpRequest.sendOkPostFormData(submitjson.toString(),
                "http://118.178.18.181:58015/app/bug/submit",
                new okhttp3.Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.e("f4", e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Log.e("f5", response.message());

                    }
                });

        Log.e("yy", "xxxxx");
    }

    public void backinfo(final Context mContext) {
        LayoutInflater factory = LayoutInflater.from(mContext);
        View textEntryView = factory.inflate(R.layout.myedit, null);
        // removeView();
        //内部局部类，只能访问方法的final类型的变量
        final EditText mname_edit = (EditText) textEntryView
                .findViewById(R.id.info_edit);
        final RadioGroup mname_radio = (RadioGroup) textEntryView.findViewById(R.id.radioGroup_edit);
        TextView mname_text = (TextView) textEntryView.findViewById(R.id.myedit_text);
        // 不是用这个方法获取EditText的内容的
        // mname_edit.addTextChangedListener(mTextWatcher);

        // create a dialog
        new AlertDialog.Builder(mContext)
                .setIcon(R.drawable.button_action_blue)
                .setTitle("Submit")
                .setView(textEntryView)
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                                Toast.makeText(mContext, "canceled", Toast.LENGTH_SHORT).show();

                            }

                        })
                .setPositiveButton("submit",
                        new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                for (int i = 0; i < mname_radio.getChildCount(); i++) {
                                    RadioButton rd = (RadioButton) mname_radio.getChildAt(i);
                                    if (rd.isChecked()) {
                                        Toast.makeText(mContext, "点击提交按钮,获取你选择的是:" + rd.getText(), Toast.LENGTH_LONG).show();
                                        break;
                                    }
                                }
                            }

                        }).show();
    }


    /**
     * 获取APP崩溃异常报告
     *
     * @param ex
     * @return
     */
    private String getCrashReport(Context context, Throwable ex) {
        PackageInfo pinfo = getPackageInfo(context);
        StringBuffer exceptionStr = new StringBuffer();
        exceptionStr.append("Version:" + pinfo.versionName + "("
                + pinfo.versionCode + ")");
        exceptionStr.append("Android:" + android.os.Build.VERSION.RELEASE
                + "(" + android.os.Build.MODEL + ")");
        exceptionStr.append("Exception:" + ex.getMessage());
        StackTraceElement[] elements = ex.getStackTrace();
        for (int i = 0; i < elements.length; i++) {
            exceptionStr.append(elements[i].toString());
        }
        return exceptionStr.toString();
    }

    /**
     * 获取App安装包信息
     *
     * @return
     */
    private PackageInfo getPackageInfo(Context context) {
        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }
}
