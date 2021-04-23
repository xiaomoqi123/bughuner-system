package com.example.jingbin.cloudreader.BugHunter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.jingbin.cloudreader.R;

import java.util.List;

public class EdgeInfoAdapter extends ArrayAdapter<EdgeInfo> {

    public EdgeInfoAdapter(Context context, int resource, List<EdgeInfo> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view;
        if (convertView == null) {
            //通过一个打气筒 inflate 可以把一个布局转换成一个view对象
            view = LayoutInflater.from(getContext()).inflate(R.layout.edgeitem, null);
        } else {
            view = convertView;//复用历史缓存对象
        }
        final EdgeInfo edgeInfo = getItem(position);
        //单选按钮的文字
        StringBuilder message = new StringBuilder();
        message.append("Event：");
        message.append("\n");
        message.append(edgeInfo.getEventHandlers());
        message.append("\n");
        message.append("\n");
        message.append("Exception Message：");
        message.append("\n");
        message.append(edgeInfo.getMessage());
        message.append("\n");
        message.append("\n");
        message.append("Source Window：");
        message.append("\n");
        String sourceNode = edgeInfo.getSourceNode();
        message.append(sourceNode);
        message.append("\n");
        message.append("\n");
        message.append("Target Window：");
        message.append("\n");
        String targetNode = edgeInfo.getTargetNode();
        message.append(targetNode);
        TextView textView = (TextView) view.findViewById(R.id.edge_textView);
        textView.setText(message.toString());
        //把imageURL写死，进行测试http://172.19.240.240:8080/yunyue/pic/8df430a8_1547460287119.png////edgeInfo.getImageUrl()
        String URLs = "http://172.19.240.240:8080/test/image/8df430a8_1547460287119.png";
        ImageView imageView = (ImageView) view.findViewById(R.id.edge_imageView);
        Glide.with(getContext()).load(edgeInfo.getImageUrl()).into(imageView);

        return view;

    }

    class ViewHolder {
        ImageView imageView;
        TextView textView;
    }


}
