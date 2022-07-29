package com.example.jingbin.cloudreader.BugHunter;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jingbin.cloudreader.R;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class Camera {
    public Camera() {
    }

    ;

    public void camera(final Activity activity) {
        //获取当前屏幕的大小
        int width = activity.getWindow().getDecorView().getRootView().getWidth();
        int height = activity.getWindow().getDecorView().getRootView().getHeight();
        //生成相同大小的图片
        Bitmap temBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //找到当前页面的跟布局
        View view = activity.getWindow().getDecorView().getRootView();
        //设置缓存
        view.setDrawingCacheEnabled(true);
        view.buildDrawingCache();
        //从缓存中获取当前屏幕的图片
        temBitmap = view.getDrawingCache();

        LayoutInflater factory = LayoutInflater.from(activity);
        View textEntryView = factory.inflate(R.layout.mypicture, null);

        EditText edit = (EditText) textEntryView.findViewById(R.id.info_pic);
        TextView textname = (TextView) textEntryView.findViewById(R.id.mypicture_text);
        ImageView image = (ImageView) textEntryView.findViewById(R.id.image_info);
        image.setImageBitmap(temBitmap);
        final RadioGroup mname_radio = (RadioGroup) textEntryView.findViewById(R.id.radioGroup_picture);
        TextView mname_text = (TextView) textEntryView.findViewById(R.id.mypicture_text2);
        final RadioGroup bugitem = (RadioGroup) textEntryView.findViewById(R.id.radioGroup_pictureitem);
        new AlertDialog.Builder(activity)
                .setIcon(R.drawable.button_action_blue)
                .setTitle("Submit with screenshot")
                .setView(textEntryView)
                .setNegativeButton("cancel",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                // TODO Auto-generated method stub
                                Toast.makeText(activity, "canceled", Toast.LENGTH_SHORT).show();

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
                                        Toast.makeText(activity.getApplicationContext(), "点击提交按钮,获取你选择的是:" + rd.getText(), Toast.LENGTH_LONG).show();
                                        break;
                                    }
                                }
                            }

                        })
                .show();

    }

    public static Activity getCurrentActivity() {
        try {
            Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(
                    null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            Map activities = (Map) activitiesField.get(activityThread);
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Activity activity = (Activity) activityField.get(activityRecord);
                    return activity;
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
