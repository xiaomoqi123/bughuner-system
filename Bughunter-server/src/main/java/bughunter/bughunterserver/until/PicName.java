package bughunter.bughunterserver.until;

import java.text.SimpleDateFormat;

public class PicName {

    public static int Guid = 100;

    public static String getPicName() {
        Guid += 1;
        long now = System.currentTimeMillis();
        //获取4位年份数字
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
        //获取时间戳
        String time = dateFormat.format(now);
        String info = now + "";
        //获取三位随机数
        //要是一段时间内的数据连过大会有重复的情况，所以做以下修改
        if (Guid > 999) {
            Guid = 100;
        }
        //调用这个功能的bug，将把其bugid交出，让需要保存的图片做文件名，使根据bugID可以找到相对应的图片
        Constants.bug_id = time + info.substring(2, info.length()) + Guid;
        return time + info.substring(2, info.length()) + Guid;
    }


}
