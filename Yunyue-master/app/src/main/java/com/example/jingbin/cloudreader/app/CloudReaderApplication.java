package com.example.jingbin.cloudreader.app;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import android.util.Log;

import com.example.http.HttpUtils;
import com.example.jingbin.cloudreader.BugHunter.CrashHandler;

/**
 * Created by jingbin on 2016/11/22.
 */

public class CloudReaderApplication extends MultiDexApplication {

    private static CloudReaderApplication cloudReaderApplication;

    public static CloudReaderApplication getInstance() {
        return cloudReaderApplication;
    }
    private static Context context;
    @SuppressWarnings("unused")
    @Override
    public void onCreate() {
        super.onCreate();
        cloudReaderApplication = this;
        context = getApplicationContext();
//        if (LeakCanary.isInAnalyzerProcess(this)) {
//            return;
//        }
//        LeakCanary.install(this);
        HttpUtils.getInstance().init(this);
        initData();
//        CrashReport.initCrashReport(getApplicationContext(), "3977b2d86f", BuildConfig.DEBUG);

    }
    public static Context getContext() {
        return context;
    }

    private void initData() {
        //当程序发生Uncaught异常的时候,由该类来接管程序,一定要在这里初始化
        CrashHandler.getInstance().init(this);
        Log.e("bug捕获", "start!");
    }


}
