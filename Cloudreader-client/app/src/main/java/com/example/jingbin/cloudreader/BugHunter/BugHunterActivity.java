package com.example.jingbin.cloudreader.BugHunter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class BugHunterActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        Log.e("123MainActivity","项目开始运行");

        FloatingActionButton button=new FloatingActionButton(Camera.getCurrentActivity());
        ScreenShotListenManager manager = ScreenShotListenManager.newInstance(Camera.getCurrentActivity());
        //设置截屏监听
        manager.setListener(
                new ScreenShotListenManager.OnScreenShotListener() {
                    public void onShot(String imagePath) {
                        // do something
                        Camera camera=new Camera();
                        camera.camera(Camera.getCurrentActivity());
                    }
                }
        );
        manager.startListen();

    }


//    public void camera(){
//        //获取当前屏幕的大小
//        int width = getWindow().getDecorView().getRootView().getWidth();
//        int height = getWindow().getDecorView().getRootView().getHeight();
//        //生成相同大小的图片
//        Bitmap temBitmap = Bitmap.createBitmap( width, height, Bitmap.Config.ARGB_8888 );
//        //找到当前页面的跟布局
//        View view =  getWindow().getDecorView().getRootView();
//        //设置缓存
//        view.setDrawingCacheEnabled(true);
//        view.buildDrawingCache();
//        //从缓存中获取当前屏幕的图片
//        temBitmap = view.getDrawingCache();
//
//        LayoutInflater factory = LayoutInflater.from(this);
//        View textEntryView = factory.inflate(R.layout.mypicture, null);
//        EditText edit=textEntryView.findViewById(R.id.info_pic);
//        TextView textname=textEntryView.findViewById(R.id.mypicture_text);
//        ImageView image=textEntryView.findViewById(R.id.image_info);
//        image.setImageBitmap(temBitmap);
//        final RadioGroup mname_radio=(RadioGroup)textEntryView.findViewById(R.id.radioGroup_picture);
//        TextView mname_text=(TextView)textEntryView.findViewById(R.id.mypicture_text);
//        new AlertDialog.Builder(this)
//                .setIcon(R.drawable.button_action_blue)
//                .setTitle("反馈图片")
//                .setView(textEntryView)
//                .setNegativeButton("cancel",
//                        new DialogInterface.OnClickListener() {
//                            @Override
//                            public void onClick(DialogInterface dialog,
//                                                int which) {
//                                // TODO Auto-generated method stub
//                                Toast.makeText(BugHunterActivity.this,"u click cancel",Toast.LENGTH_SHORT).show();
//
//                            }
//
//                        })
//                .setPositiveButton("submit",
//                        new DialogInterface.OnClickListener() {
//
//
//
//                            @Override
//                            public void onClick(DialogInterface dialog,
//                                                int which) {
//                                for (int i = 0; i < mname_radio.getChildCount(); i++) {
//                                    RadioButton rd = (RadioButton) mname_radio.getChildAt(i);
//                                    if (rd.isChecked()) {
//                                        Toast.makeText(getApplicationContext(), "点击提交按钮,获取你选择的是:" + rd.getText(), Toast.LENGTH_LONG).show();
//                                        break;
//                                    }
//                                }
//                            }
//
//                        })
//                .show();
//
//    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();

    }
}
