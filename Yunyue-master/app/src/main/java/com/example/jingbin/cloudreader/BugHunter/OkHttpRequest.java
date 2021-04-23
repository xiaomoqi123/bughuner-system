package com.example.jingbin.cloudreader.BugHunter;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;

public class OkHttpRequest {
    public static void sendLoginPost(String address, String param, okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        MediaType JSON=MediaType.parse("application/json; charset=utf-8");
        RequestBody requestBody=RequestBody.create(JSON,param);
        Request request=new Request.Builder()
                .url(address)
                .addHeader("Accept","application/json")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);

    }
    public static void sendOkHttpRequest(String address,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .addHeader("Accept","application/json")
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void sendOkPostFormData(String json,String address,okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("bug", json)
                .build();
        Request request=new Request.Builder()
                .url(address)
                .addHeader("Accept","application/json")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }


    public static void sendOkPostPicData(String json, String address, File file, okhttp3.Callback callback){
        OkHttpClient client=new OkHttpClient();
        RequestBody fileBody = RequestBody.create(MediaType.parse("image/png"), file);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("screenshot", "head_image", fileBody)
                .addFormDataPart("bug", json)
                .build();
        Request request=new Request.Builder()
                .url(address)
                .addHeader("Accept","application/json")
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }


}

