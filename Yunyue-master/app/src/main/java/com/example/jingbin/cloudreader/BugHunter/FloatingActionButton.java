package com.example.jingbin.cloudreader.BugHunter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jingbin.cloudreader.R;
import com.example.jingbin.cloudreader.app.CloudReaderApplication;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FloatingActionButton {
    //demo用 写死uid
    public static Integer uid = 2;
    String app_key = "yunyue";
    final int MSG_ERROR = 1;
    final int MSG_OK = 0;
    final int MSG_FAIL = 2;
    private Handler handler;
    //把这个设置成公共变量，让我能够在不同的页面进行调用
    public static EdgeInfo edgeInfo;
    //因为到不同的界面要走路，这个用来记录我走了几步，也就是说我到了哪个界面了
    public static int step = 0;
    //从数据库里读出来的数据带空格，这个用来对其去空格
    String next_window = "";


    CloudReaderApplication myApplication;
    String TAG = "FloatingActionButton";
    int CLICK_OR_NOT = 0;  //是否选择相应的测试用例
    public FloatingActionButton(final Activity context) {
        //设置悬浮按钮
        ImageView fabContent = new ImageView(context);
        fabContent.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_settings));


        com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton darkButton = new com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton.Builder(context)
                .setTheme(com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton.THEME_DARK)
                .setContentView(fabContent)
                .setPosition(com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton.POSITION_RIGHT_CENTER)
                .build();

        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(context)
                .setTheme(SubActionButton.THEME_DARK);
        ImageView chaticon = new ImageView(context);
        ImageView picicon = new ImageView(context);

        ImageView settingicon = new ImageView(context);
        ImageView hintIcon = new ImageView(context);
        ImageView tcRecommIcon = new ImageView(context);
        ImageView pathRecommIcon = new ImageView(context);
        ImageView sendicon = new ImageView(context);


        chaticon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_chat));
        picicon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_picture));
        sendicon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_send_now));
        settingicon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_settings));
        hintIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_hint));
        tcRecommIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_exception));
        pathRecommIcon.setImageDrawable(context.getResources().getDrawable(R.drawable.ic_action_path));


        SubActionButton chatbutton = rLSubBuilder.setContentView(chaticon).build();
        SubActionButton picbutton = rLSubBuilder.setContentView(picicon).build();
        SubActionButton sendbutton = rLSubBuilder.setContentView(sendicon).build();
        SubActionButton settingbutton = rLSubBuilder.setContentView(settingicon).build();
        SubActionButton hintbutton = rLSubBuilder.setContentView(hintIcon).build();
        SubActionButton recommTCbutton = rLSubBuilder.setContentView(tcRecommIcon).build();
        SubActionButton recommPathbutton = rLSubBuilder.setContentView(pathRecommIcon).build();


        chatbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                JumpEdit(context);

            }
        });

        picbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                camera(context);
            }
        });

        sendbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(context);
            }
        });

        settingbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settings(context);
            }
        });

        hintbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Hint(context);
            }
        });

        recommTCbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecommendBug(context);
            }
        });

        recommPathbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecommendPath(context);
            }
        });

        // Set 4 SubActionButtons
        FloatingActionMenu centerBottomMenu = new FloatingActionMenu.Builder(context)
//                .addSubActionView(chatbutton)
                .addSubActionView(picbutton)
                .addSubActionView(sendbutton)
//                .addSubActionView(settingbutton)
                .addSubActionView(hintbutton)
                .addSubActionView(recommTCbutton)
                .addSubActionView(recommPathbutton)
                .attachTo(darkButton)
                .build();
    }

    @SuppressLint("HandlerLeak")
    public void RecommendPath(final Activity context) {
        String currWindow = getCurrentActivity().getClass().getName();
        String[] infos = currWindow.split("\\.");
        final String currentWindow = infos[infos.length - 1];

        handler = new Handler() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_OK:

                        Toast.makeText(context, "Get Success", Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject result = new JSONObject(msg.obj.toString());
                            Log.i(TAG, "handleMessage: 拿结果"+msg.obj.toString());
                            JSONArray dataarray = result.getJSONArray("data");
                            final List<EdgeInfo> edgeInfos = new ArrayList<>();
                            for (int i = 0; i < dataarray.length(); i++) {
                                JSONObject data = dataarray.getJSONObject(i);
                                String id = data.getString("id");
                                String sourceNode = data.getString("sourceNode");
                                String targetNode = data.getString("targetNode");
                                String eventHandlers = data.getString("eventHandlers");
                                String message = data.getString("message");
                                String path = data.getString("path");
                                String imageUrl = data.getString("imageUrl");
                                String dateType = data.getString("dataType");
                                EdgeInfo edgeInfo = new EdgeInfo(id, sourceNode, targetNode, eventHandlers, message, path, imageUrl);
                                edgeInfo.setDataType(new Integer(dateType));
                                edgeInfos.add(edgeInfo);
                            }
                            View bottemView = LayoutInflater.from(context).inflate(R.layout.dialoglistview, null);
                            TextView textView = (TextView) bottemView.findViewById(R.id.dialoglist_id);
                            textView.setText("Uncovered Window");
                            final ListView listView = (ListView) bottemView.findViewById(R.id.listView);

                            listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                            final EdgeInfoAdapter edgeInfoAdapter = new EdgeInfoAdapter(context, R.layout.edgeitem, edgeInfos);
                            listView.setAdapter(edgeInfoAdapter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    edgeInfo = edgeInfos.get(position);
                                    view.setAlpha(0.5f);
                                    CLICK_OR_NOT = 1;
                                }
                            });

                            AlertDialog alertDialog = new AlertDialog.Builder(context)
                                    .setView(bottemView)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if(CLICK_OR_NOT==1)
                                            {
                                                Toast.makeText(context, "You have chosen" + edgeInfo.getId(), Toast.LENGTH_SHORT).show();
//                                                OkHttpRequest.sendOkHttpRequest("http://118.178.18.181:58015/edge/" + edgeInfo.getId() + "/" + uid,
//                                                        new okhttp3.Callback() {
//                                                            @Override
//                                                            public void onFailure(Call call, IOException e) {
////
//                                                            }
//
//                                                            @Override
//                                                            public void onResponse(Call call, Response response) throws IOException {
////
//                                                            }
//                                                        });
                                                CLICK_OR_NOT = 0;
                                            }else
                                            {
                                                Toast.makeText(context, "no test case selected", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    })
                                    .create();
                            alertDialog.setCanceledOnTouchOutside(false);
                            alertDialog.show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case MSG_FAIL:
                        Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        };

        OkHttpRequest.sendOkHttpRequest("http://118.178.18.181:58015/app/bug/"+app_key+"/" + currentWindow + "/bugList/0/2",
                new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {
                        Message message = new Message();
                        message.what = MSG_FAIL;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {

                        Message message = new Message();
                        message.what = MSG_OK;
                        message.obj = response.body().string();
                        handler.sendMessage(message);
                    }
                });

    }

    //推荐异常
    @SuppressLint("HandlerLeak")
    public void RecommendBug(final Activity context) {

        String currWindow = getCurrentActivity().getClass().getName();
        String[] infos = currWindow.split("\\.");
        final String currentWindow = infos[infos.length - 1];
        handler = new Handler() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_OK:
                        Toast.makeText(context, "Get Success", Toast.LENGTH_SHORT).show();
                        try {
                            JSONObject result = new JSONObject(msg.obj.toString());
                            JSONArray dataarray = result.getJSONArray("data");
                            final List<EdgeInfo> edgeInfos = new ArrayList<>();
                            //避免重复的ID出现
                            Set<String> idSet = new HashSet<>();
                            for (int i = 0; i < dataarray.length(); i++) {
                                JSONObject data = dataarray.getJSONObject(i);
                                String id = data.getString("id");
                                if(!idSet.contains(id))
                                {
                                    idSet.add(id);
                                    String sourceNode = data.getString("sourceNode");
                                    String targetNode = data.getString("targetNode");
                                    String eventHandlers = data.getString("eventHandlers");
                                    String message = data.getString("message");
                                    String path = data.getString("path");
                                    String imageUrl = data.getString("imageUrl");
                                    String dateType = data.getString("dataType");
                                    EdgeInfo edgeInfo = new EdgeInfo(id, sourceNode, targetNode, eventHandlers, message, path, imageUrl);
                                    edgeInfo.setDataType(new Integer(dateType));
                                    edgeInfos.add(edgeInfo);
                                }else
                                {

                                }
                            }
                            View bottemView = LayoutInflater.from(context).inflate(R.layout.dialoglistview, null);
                            TextView textView = (TextView) bottemView.findViewById(R.id.dialoglist_id);
                            textView.setText("Recommended Task：");
                            final ListView listView = (ListView) bottemView.findViewById(R.id.listView);
                            listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                            final EdgeInfoAdapter edgeInfoAdapter = new EdgeInfoAdapter(context, R.layout.edgeitem, edgeInfos);
                            listView.setAdapter(edgeInfoAdapter);

                            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    edgeInfo = edgeInfos.get(position);
                                    view.setAlpha(0.5f);
                                    CLICK_OR_NOT = 1;
                                }
                            });

                            AlertDialog alertDialog = new AlertDialog.Builder(context)
                                    .setView(bottemView)
                                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            if (CLICK_OR_NOT==1) {
                                                Toast.makeText(context, "You have chosen" + edgeInfo.getId(), Toast.LENGTH_SHORT).show();
                                                //表明我要从头开始走路了
                                                int step = 0;
                                                CLICK_OR_NOT = 0;
                                            } else
                                            {
                                                Toast.makeText(context, "no test case selected", Toast.LENGTH_SHORT).show();
                                            }

                                        }
                                    })
                                    .create();

                            alertDialog.show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case MSG_FAIL:
                        Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        };

        OkHttpRequest.sendOkHttpRequest("http://118.178.18.181:58015/app/bug/"+app_key+"/" + currentWindow + "/bugList/2/" + uid,
                new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {
                        Message message = new Message();
                        message.what = MSG_FAIL;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Message message = new Message();
                        message.what = MSG_OK;
                        message.obj = response.body().string();
                        handler.sendMessage(message);
                    }
                });

    }
    @SuppressLint("HandlerLeak")
    public void Hint(final Activity context) {

        if(edgeInfo==null)
        {
            Toast.makeText(context, "No test case selected",Toast.LENGTH_SHORT).show();
        }else  //这里是选中用例
        {
            String currWindow = getCurrentActivity().getClass().getName();
            String[] infos = currWindow.split("\\.");
            String currentWindow = infos[infos.length - 1];
            String[]  nodeWindows = edgeInfo.getPath().split("->");
            //如果选择的是未覆盖的路径，那么就这样显示
            if (edgeInfo.getDataType().equals(0)) {
            View bottemView = LayoutInflater.from(context).inflate(R.layout.dialoglistview, null);
            TextView textView = (TextView) bottemView.findViewById(R.id.dialoglist_id);
            textView.setText("Uncovered Event:");
            final ListView listView = (ListView) bottemView.findViewById(R.id.listView);
            List<EdgeInfo> edgeInfos = new ArrayList<>();
            edgeInfos.add(edgeInfo);
            listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
            final EdgeInfoAdapter edgeInfoAdapter = new EdgeInfoAdapter(context, R.layout.edgeitem, edgeInfos);
            listView.setAdapter(edgeInfoAdapter);
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(bottemView)
                    .setPositiveButton("OK", null)
                    .create();
            builder.show();
            }else {                  //这种是遇到异常
                handler = new Handler() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    public void handleMessage(Message msg) {
                        switch (msg.what) {
                            case MSG_OK:
                                if (step < nodeWindows.length) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(msg.obj.toString());
                                        JSONObject data = jsonObject.getJSONObject("data");
                                        String id = data.getString("id");
                                        String sourceNode = data.getString("sourceNode");
                                        String targetNode = data.getString("targetNode");
                                        String eventHandlers = data.getString("eventHandlers");
                                        String message = data.getString("message");
                                        String path = data.getString("path");
                                        String imageUrl = data.getString("imageUrl");
                                        String dateType = data.getString("dataType");
                                        EdgeInfo new_edgeInfo = new EdgeInfo(id, sourceNode, targetNode, eventHandlers, message, path, imageUrl);
                                        View bottemView = LayoutInflater.from(context).inflate(R.layout.dialoglistview, null);
                                        TextView textView = (TextView) bottemView.findViewById(R.id.dialoglist_id);
                                        textView.setText("Transition Path:");
                                        final ListView listView = (ListView) bottemView.findViewById(R.id.listView);
                                        List<EdgeInfo> edgeInfos = new ArrayList<>();
                                        edgeInfos.add(new_edgeInfo);
                                        listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
                                        final EdgeInfoAdapter edgeInfoAdapter = new EdgeInfoAdapter(context, R.layout.edgeitem, edgeInfos);
                                        listView.setAdapter(edgeInfoAdapter);
                                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                                        builder.setView(bottemView)
                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        step++;
                                                    }
                                                })
                                                .setNegativeButton("cancel", null)
                                                .create();
                                        builder.show();
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                } else  //已经走到目标路径了
                                {
                                    step = 0;
                                    TextView textView = new TextView(context);
                                    StringBuilder builder = new StringBuilder();
                                    builder.append("        You have completed this task");
                                    builder.append("\n");
                                    builder.append("        please submit bug report");
                                    builder.append("\n");
                                    builder.append("        or choose another task");
                                    textView.setText(builder.toString());
                                    AlertDialog alertDialog = new AlertDialog.Builder(context)
                                            .setTitle("Hint")
                                            .setView(textView)
                                            .setNeutralButton("Report Exection", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    JumpEdit(context);
                                                }
                                            })
                                            .setPositiveButton("Test Next", null)
                                            .create();
                                    alertDialog.show();
                                    //然后把它去掉
//                                    OkHttpRequest.sendOkHttpRequest("http://118.178.18.181:58015/edge/" + edgeInfo.getId() + "/" + uid,
//                                            new okhttp3.Callback() {
//                                                @Override
//                                                public void onFailure(Call call, IOException e) {
////
//                                                }
//
//                                                @Override
//                                                public void onResponse(Call call, Response response) throws IOException {
////
//                                                }
//                                            });

                                }
                                break;
                            case MSG_FAIL:
                                Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show();
                                break;
                            default:
                                break;
                        }
                    }
                };


                if(step<nodeWindows.length)
                {
                     next_window = nodeWindows[step];
                    if(next_window.startsWith(" "))
                    {
                        next_window = next_window.substring(1,next_window.length());
                    }
                }
                OkHttpRequest.sendOkHttpRequest("http://118.178.18.181:58015/path/nextHint/"
                                + currentWindow + "/" +next_window+ "/" + new Long(edgeInfo.getId()),
                        new Callback() {
                            @Override
                            public void onFailure(Call call, IOException e) {
                                Message message = new Message();
                                message.what = MSG_FAIL;
                                handler.sendMessage(message);
                            }

                            public void onResponse(Call call, Response response) throws IOException {
                                Message message = new Message();
                                message.what = MSG_OK;
                                message.obj = response.body().string();
                                handler.sendMessage(message);
                                System.out.println("拿到数据："+message.obj.toString()+"-----------------------------------------");
                            }
                        });
             }

         }

    }
//    @SuppressLint("HandlerLeak")
//    public void Hint(final Activity context) {
//        String currWindow = getCurrentActivity().getClass().getName();
//        String[] infos = currWindow.split("\\.");
//        String currentWindow = infos[infos.length - 1];
//        String[] nodeWindows = edgeInfo.getPath().split("->");
//        if (edgeInfo.getDataType().equals(0)) {
//
//            View bottemView = LayoutInflater.from(context).inflate(R.layout.dialoglistview, null);
//            TextView textView = (TextView) bottemView.findViewById(R.id.dialoglist_id);
//            textView.setText("Recommended Event:");
//            final ListView listView = (ListView) bottemView.findViewById(R.id.listView);
//            List<EdgeInfo> edgeInfos = new ArrayList<>();
//            edgeInfos.add(edgeInfo);
//            listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
//            final EdgeInfoAdapter edgeInfoAdapter = new EdgeInfoAdapter(context, R.layout.edgeitem, edgeInfos);
//            listView.setAdapter(edgeInfoAdapter);
//
//            AlertDialog.Builder builder = new AlertDialog.Builder(context);
//            builder.setView(bottemView)
//                    .setPositiveButton("OK", null)
//                    .create();
//            builder.show();
//
//        } else if (edgeInfo.getDataType().equals(2)) {
//            for (int i = 0; i < nodeWindows.length - 1; i++) {
//                //第一次到达最后一条跳转A-B-C-D:C-D
//                if (nodeWindows[i].equals(currentWindow) && nodeWindows[i + 1].equals(currentWindow)
//                        && i + 1 == nodeWindows.length - 1 && edgeInfo.getCount() == 0) {
//                    //推荐bug
//
//                    View bottemView = LayoutInflater.from(context).inflate(R.layout.dialoglistview, null);
//                    TextView textView = (TextView) bottemView.findViewById(R.id.dialoglist_id);
//                    textView.setText("Trigger Exception:");
//                    final ListView listView = (ListView) bottemView.findViewById(R.id.listView);
//                    List<EdgeInfo> edgeInfos = new ArrayList<>();
//                    edgeInfos.add(edgeInfo);
//                    listView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
//                    final EdgeInfoAdapter edgeInfoAdapter = new EdgeInfoAdapter(context, R.layout.edgeitem, edgeInfos);
//                    listView.setAdapter(edgeInfoAdapter);
//
//                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                    builder.setView(bottemView)
//                            .setPositiveButton("OK", null)
//                            .create();
//                    builder.show();
//
//                    edgeInfo.setCount(1);
//                    Log.i(TAG, "Hint: 1111111111111111111111111111");
//                } else if (nodeWindows[i].equals(currentWindow) && nodeWindows[i + 1].equals(currentWindow)
//                        && i + 1 == nodeWindows.length - 1 && edgeInfo.getCount() != 0) {
//                    //第二次在最后跳转中请求提示
//                    TextView textView = new TextView(context);
//                    StringBuilder builder = new StringBuilder();
//                    builder.append("        You have completed this task");
//                    builder.append("\n");
//                    builder.append("        please submit bug report");
//                    builder.append("\n");
//                    builder.append("        or choose another task");
//                    textView.setText(builder.toString());
//                    AlertDialog alertDialog = new AlertDialog.Builder(context)
//                            .setTitle("Hint")
//                            .setView(textView)
//                            .setNeutralButton("report", new DialogInterface.OnClickListener() {
//                                @Override
//                                public void onClick(DialogInterface dialog, int which) {
//                                    JumpEdit(context);
//                                }
//                            })
//                            .setPositiveButton("continue", null)
//                            .create();
//                    alertDialog.show();
//
//                } else if (nodeWindows[i].equals(currentWindow)) {
//                    //A-B-C-D:B-C
//                    handler = new Handler() {
//                        @RequiresApi(api = Build.VERSION_CODES.KITKAT)
//                        public void handleMessage(Message msg) {
//                            switch (msg.what) {
//                                case MSG_OK:
//                                    Toast.makeText(context, "Get Success", Toast.LENGTH_SHORT).show();
//                                    Log.e("response", msg.obj.toString());
//                                    try {
//                                        JSONObject result = new JSONObject(msg.obj.toString());
//                                        JSONArray dataarray = result.getJSONArray("data");
//                                        JSONObject data = dataarray.getJSONObject(0);
//
//                                        String eventHandlers = data.getString("eventHandlers");
//                                        String imageUrl = data.getString("imageUrl");
//
//                                        ImageView imageView = new ImageView(context);
//                                        Glide.with(context).load(edgeInfo.getImageUrl()).into(imageView);
//
//                                        StringBuilder message = new StringBuilder();
//                                        message.append("Hint: ");
//                                        message.append(eventHandlers);
//
//                                        new AlertDialog.Builder(context)
//                                                .setTitle(message.toString())
//                                                .setView(imageView)
//                                                .setPositiveButton("OK", null);
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                            }
//                        }
//                    };
//                    OkHttpRequest.sendOkHttpRequest("http://118.178.18.181:58015/path/nextHint/"
//                                    + currentWindow + "/" + nodeWindows[i + 1] + "/" + new Long(edgeInfo.getId()),
//                            new Callback() {
//
//                                @Override
//                                public void onFailure(Call call, IOException e) {
//                                    Message message = new Message();
//                                    message.what = MSG_FAIL;
//                                    handler.sendMessage(message);
//                                }
//
//                                @Override
//                                public void onResponse(Call call, Response response) throws IOException {
//                                    Message message = new Message();
//                                    message.what = MSG_OK;
//                                    message.obj = response.body().string();
//                                    handler.sendMessage(message);
//                                }
//                            });
//                }
//            }
//        }
//
//    }

    @SuppressLint("HandlerLeak")
    public void settings(final Activity context) {

        //当前appkey和当前活动
        String appkey = Appkey.getApplicationMetaData(context.getApplication());
        String currentapp = getCurrentActivity().getClass().getName();

        handler = new Handler() {

            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_OK:
                        Toast.makeText(context, "Get Success", Toast.LENGTH_SHORT).show();
                        Log.e("response", msg.obj.toString());
                        try {
                            JSONObject result = new JSONObject(msg.obj.toString());
                            JSONArray dataarray = result.getJSONArray("data");
                            JSONObject data1 = dataarray.getJSONObject(0);
                            JSONObject bugbaseinfo = data1.getJSONObject("bugBaseInfo");
                            String bugId = bugbaseinfo.getString("bugId");
                            String type = bugbaseinfo.getString("type");
                            String describe = bugbaseinfo.getString("describe");
                            String priority = bugbaseinfo.getString("priority");
                            Log.e("priority", priority);
                            new AlertDialog.Builder(context)
                                    .setTitle("Recommended bug").setMessage("bugid:" + bugId + "\ntype:" + type + "\ndescription:" + describe
                                    + "\npriority:" + priority)
                                    .setPositiveButton("Modify",
                                            new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog,
                                                                    int which) {
                                                    JumpEdit(context);


                                                }

                                            }).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        break;
                    case MSG_FAIL:
                        Toast.makeText(context, "Network Error", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        };
        OkHttpRequest.sendOkHttpRequest("http://118.178.18.181:58015/app/bug/" + appkey + "/" + currentapp + "/getCurrentActivityBug",
                new Callback() {

                    @Override
                    public void onFailure(Call call, IOException e) {
                        Message message = new Message();
                        message.what = MSG_FAIL;
                        handler.sendMessage(message);
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        Message message = new Message();
                        message.what = MSG_OK;
                        message.obj = response.body().string();
                        handler.sendMessage(message);

                    }
                });

    }


    @SuppressLint("HandlerLeak")
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


//        private  String SAVE_PIC_PATH = Environment.getExternalStorageState().equalsIgnoreCase(Environment.MEDIA_MOUNTED)
//                ? Environment.getExternalStorageDirectory().getAbsolutePath() : "/mnt/sdcard";//
        String file_str = Environment.getExternalStorageDirectory().getPath();
        File mars_file = new File(file_str + "/my_camera");
        final File file = new File(file_str + "/my_camera/file.png");
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())) {
            // 先创建父目录，如果新创建一个文件的时候，父目录没有存在，那么必须先创建父目录，再新建文件。
            if (!mars_file.exists()) {
                mars_file.mkdirs();
            }
        }

  //      final File file = new File("/sdcard/test.png");//将要保存图片的路径
        Log.e(TAG, "camera: +++++++++++++++++"+file.length() );
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(file));
            temBitmap.compress(Bitmap.CompressFormat.PNG, 100, bos);
            bos.flush();
            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e(TAG, "camera: -------------------------------"+file.length() );
        //Handler处理返回的信息
        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_OK:
                        Toast.makeText(activity, "Submit Success", Toast.LENGTH_SHORT).show();
                        break;
                    case MSG_FAIL:
                        Toast.makeText(activity, "Submit Failed", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        };

        LayoutInflater factory = LayoutInflater.from(activity);
        View textEntryView = factory.inflate(R.layout.mypicture, null);
        final EditText edit = (EditText) textEntryView.findViewById(R.id.info_pic);
        TextView textname = (TextView) textEntryView.findViewById(R.id.mypicture_text);
        ImageView image = (ImageView) textEntryView.findViewById(R.id.image_info);
        image.setImageBitmap(temBitmap);
        final RadioGroup mname_radio = (RadioGroup) textEntryView.findViewById(R.id.radioGroup_picture);
        TextView mname_text = (TextView) textEntryView.findViewById(R.id.mypicture_text2);
        final RadioGroup bugitem = (RadioGroup) textEntryView.findViewById(R.id.radioGroup_pictureitem);
        new AlertDialog.Builder(activity)
                .setIcon(R.drawable.button_action_blue)
                .setTitle("Bug Report")
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
                                JSONObject submitjson = new JSONObject();
                                JSONObject bugbaseinfo = new JSONObject();
                                JSONObject bugconsolelog = new JSONObject();
                                JSONObject bugdeviceinfo = new JSONObject();
                                JSONObject bugoperatestep = new JSONObject();
                                try {
                                    bugconsolelog.put("logString", "  ");
                                    submitjson.put("BugConsoleLog", bugconsolelog);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    bugbaseinfo.put("appKey", app_key);
                                    bugbaseinfo.put("appVersion", Appkey.getVersionName(activity.getApplicationContext()));
                                    bugbaseinfo.put("describe", edit.getText());
                                    bugbaseinfo.put("uId", uid);

                                    bugbaseinfo.put("current", getCurrentActivity().getClass().getName());
                                    bugbaseinfo.put("status", "New");
                                    bugbaseinfo.put("edge_id", 0L);
                                    bugbaseinfo.put("info_flag", 0);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                //获取单选框信息
                                for (int i = 0; i < mname_radio.getChildCount(); i++) {
                                    RadioButton rd = (RadioButton) mname_radio.getChildAt(i);
                                    if (rd.isChecked()) {
                                        try {
                                            bugbaseinfo.put("priority", rd.getText());
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    }
                                }
                                for (int i = 0; i < bugitem.getChildCount(); i++) {
                                    RadioButton rd = (RadioButton) bugitem.getChildAt(i);
                                    if (rd.isChecked()) {
                                        try {
                                            bugbaseinfo.put("type", rd.getText());
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    }
                                }
                                try {
                                    submitjson.put("BugBaseInfo", bugbaseinfo);
                                    bugdeviceinfo.put("systemLanguage", SystemUtil.getSystemLanguage());
                                    bugdeviceinfo.put("systemVersion", SystemUtil.getSystemVersion());
                                    bugdeviceinfo.put("systemModel", SystemUtil.getSystemModel());
                                    bugdeviceinfo.put("deviceBrand", SystemUtil.getDeviceBrand());
                                    bugdeviceinfo.put("providersName", "china mobile");
                                    bugdeviceinfo.put("resolution", SystemUtil.getResolution(activity.getApplicationContext()));
                                    bugdeviceinfo.put("availMemory", SystemUtil.getAvailMemory(activity.getApplicationContext()));
                                    bugdeviceinfo.put("totalMemory", SystemUtil.getTotalMemory(activity.getApplicationContext()));
                                    bugdeviceinfo.put("networkType", SystemUtil.GetNetworkType(activity.getApplicationContext()));
                                    submitjson.put("BugDeviceInfo", bugdeviceinfo);

                                    //操作步骤
                                    bugoperatestep.put("operateStep", MyAccessibilityService.eventTime + " " +
                                            MyAccessibilityService.eventText + " " + MyAccessibilityService.className + " " + MyAccessibilityService.textArray);
                                    submitjson.put("BugOperateStep", bugoperatestep);
                                    Log.e("json", submitjson.toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }


                                OkHttpRequest.sendOkPostPicData(submitjson.toString(),
                                        "http://118.178.18.181:58015/app/bug/submit",
                                        file,
                                        new Callback() {

                                            @Override
                                            public void onFailure(Call call, IOException e) {
                                                Message message = new Message();
                                                message.what = MSG_FAIL;
                                                handler.sendMessage(message);
                                            }

                                            @Override
                                            public void onResponse(Call call, Response response) throws IOException {
                                                Message message = new Message();
                                                message.what = MSG_OK;
                                                handler.sendMessage(message);

                                            }
                                        });
                            }

                        })
                .show();

    }


    @SuppressLint("HandlerLeak")
    public void JumpEdit(final Activity activity) {

        // layout = (LinearLayout) getLayoutInflater().inflate(R.layout.myedit,
        // null);
        LayoutInflater factory = LayoutInflater.from(activity);
        View textEntryView = factory.inflate(R.layout.myedit, null);


// removeView();
//内部局部类，只能访问方法的final类型的变量
        final EditText mname_edit = (EditText) textEntryView
                .findViewById(R.id.info_edit);
        final RadioGroup mname_radio = (RadioGroup) textEntryView.findViewById(R.id.radioGroup_edit);
        TextView mname_text = (TextView) textEntryView.findViewById(R.id.myedit_text);
        final RadioGroup editRadio = (RadioGroup) textEntryView.findViewById(R.id.radioGroup_edititem);
        TextView edittext = (TextView) textEntryView.findViewById(R.id.myedit_text2);
        // 不是用这个方法获取EditText的内容的
        // mname_edit.addTextChangedListener(mTextWatcher);

        //Handler处理返回的信息
        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_OK:
                        Toast.makeText(activity, "Submit Success", Toast.LENGTH_SHORT).show();
                        break;
                    case MSG_FAIL:
                        Toast.makeText(activity, "Network Error", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        };

        // create a dialog
        new AlertDialog.Builder(activity)
                .setIcon(R.drawable.button_action_blue)
                .setTitle("Bug Report")
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
                                JSONObject submitjson = new JSONObject();
                                JSONObject bugbaseinfo = new JSONObject();
                                JSONObject bugconsolelog = new JSONObject();
                                JSONObject bugdeviceinfo = new JSONObject();
                                JSONObject bugoperatestep = new JSONObject();
                                try {
                                    bugconsolelog.put("logString", "  ");
                                    submitjson.put("BugConsoleLog", bugconsolelog);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                try {

                                   // bugbaseinfo.put("appKey", Appkey.getApplicationMetaData(activity.getApplicationContext()));
                                    bugbaseinfo.put("appKey", app_key);
                                    bugbaseinfo.put("appVersion", Appkey.getVersionName(activity.getApplicationContext()));
                                    bugbaseinfo.put("describe", mname_edit.getText());
                                    bugbaseinfo.put("uId", uid);
                                    bugbaseinfo.put("current", getCurrentActivity().getClass().getName());
                                    bugbaseinfo.put("status", "New");
                                    bugbaseinfo.put("edge_id", edgeInfo.getId());
                                    bugbaseinfo.put("info_flag", 1);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                //获取单选框信息
                                for (int i = 0; i < mname_radio.getChildCount(); i++) {
                                    RadioButton rd = (RadioButton) mname_radio.getChildAt(i);
                                    if (rd.isChecked()) {
                                        try {
                                            bugbaseinfo.put("priority", rd.getText());
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    }
                                }
                                for (int i = 0; i < editRadio.getChildCount(); i++) {
                                    RadioButton rd = (RadioButton) editRadio.getChildAt(i);
                                    if (rd.isChecked()) {
                                        try {
                                            bugbaseinfo.put("type", rd.getText());
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                        }
                                        break;
                                    }
                                }
                                try {
                                    submitjson.put("BugBaseInfo", bugbaseinfo);
                                    bugdeviceinfo.put("systemLanguage", SystemUtil.getSystemLanguage());
                                    bugdeviceinfo.put("systemVersion", SystemUtil.getSystemVersion());
                                    bugdeviceinfo.put("systemModel", SystemUtil.getSystemModel());
                                    bugdeviceinfo.put("deviceBrand", SystemUtil.getDeviceBrand());
                                    bugdeviceinfo.put("providersName", "china mobile");
                                    bugdeviceinfo.put("resolution", SystemUtil.getResolution(activity.getApplicationContext()));
                                    bugdeviceinfo.put("availMemory", SystemUtil.getAvailMemory(activity.getApplicationContext()));
                                    bugdeviceinfo.put("totalMemory", SystemUtil.getTotalMemory(activity.getApplicationContext()));
                                    bugdeviceinfo.put("networkType", SystemUtil.GetNetworkType(activity.getApplicationContext()));
                                    submitjson.put("BugDeviceInfo", bugdeviceinfo);

                                    //操作步骤
                                    bugoperatestep.put("operateStep", " " + MyAccessibilityService.operations);
                                    submitjson.put("BugOperateStep", bugoperatestep);
                                    Log.e("json", submitjson.toString());
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                OkHttpRequest.sendOkPostFormData(submitjson.toString(),
                                        "http://118.178.18.181:58015/app/bug/submit",
                                        new Callback() {

                                            @Override
                                            public void onFailure(Call call, IOException e) {
                                                Message message = new Message();
                                                message.what = MSG_FAIL;
                                                handler.sendMessage(message);
                                            }

                                            @Override
                                            public void onResponse(Call call, Response response) throws IOException {
                                                Message message = new Message();
                                                message.what = MSG_OK;
                                                handler.sendMessage(message);

                                            }
                                        });

                            }

                        }).show();
    }

    @SuppressLint("HandlerLeak")
    public void login(final Activity activity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        final AlertDialog dialog = builder.create();
        View dialogView = View.inflate(activity, R.layout.mylogin, null);
        //设置对话框布局
        dialog.setView(dialogView);
        dialog.show();
        final EditText etName = (EditText) dialogView.findViewById(R.id.et_name);
        final EditText etPwd = (EditText) dialogView.findViewById(R.id.et_pwd);
        final Button btnLogin = (Button) dialogView.findViewById(R.id.btn_login);
        final Button btnCancel = (Button) dialogView.findViewById(R.id.btn_cancel);

        //Handler处理返回的信息
        handler = new Handler() {
            public void handleMessage(Message msg) {
                switch (msg.what) {
                    case MSG_OK:
                        Toast.makeText(activity, "Login Success", Toast.LENGTH_SHORT).show();
                        uid = (Integer) msg.obj;
                        break;
                    case MSG_ERROR:
                        Toast.makeText(activity, "Invalid Email or Passowrd", Toast.LENGTH_SHORT).show();
                        break;
                    case MSG_FAIL:
                        Toast.makeText(activity, "Network Error", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
            }
        };
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = etName.getText().toString();
                String pwd = etPwd.getText().toString();
                if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)) {
                    Toast.makeText(activity, "Email or Password can't be empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                final Message message = new Message();

                try {
                    JSONObject longinJSON = new JSONObject();
                    longinJSON.put("email", name);
                    longinJSON.put("pwd", pwd);
                    OkHttpRequest.sendLoginPost("http://118.178.18.181:58015/bughunter/login",
                            longinJSON.toString(),
                            new Callback() {
                                @Override
                                public void onFailure(Call call, IOException e) {
                                    Message message = new Message();
                                    message.what = MSG_FAIL;
                                    handler.sendMessage(message);
                                }

                                @Override
                                public void onResponse(Call call, Response response) throws IOException {
                                    String data = response.body().string();
                                    try {
                                        JSONObject judge = new JSONObject(data);
                                        int error = judge.getInt("error");
                                        Log.e("info", "" + error);
                                        if (error == 0) {
                                            message.what = MSG_OK;
                                            message.obj = judge.getJSONObject("data").getInt("id");
                                        }
                                        else message.what = MSG_ERROR;
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                    handler.sendMessage(message);

                                }
                            });
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                dialog.dismiss();

            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
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
                    Log.e("return-correct", activities.size() + "");
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
