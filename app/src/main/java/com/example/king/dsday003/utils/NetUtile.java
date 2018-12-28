package com.example.king.dsday003.utils;

import android.os.Handler;

import com.example.king.dsday003.contact.Contact;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NetUtile {

    /**
     * 单例模式
     */
    private static NetUtile intence;
    private final OkHttpClient okHttpClient;
    private final Handler handler;

    private NetUtile() {
        handler = new Handler();
        okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();
    }

    public static NetUtile getIntence(){
        if (intence == null){
            synchronized (NetUtile.class){
                if (intence == null){
                    intence = new NetUtile();
                }
            }
        }
        return intence;
    }

    /**
     * post 方式
     * @param params
     * @param imodule
     */
    public void toPost(Map<String, String> params, String api, final Contact.Imodule imodule){

        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, String> p : params.entrySet()) {
            builder.add(p.getKey(),p.getValue());
        }

        final Request request = new Request.Builder()
                .url(api)
                //.get()
                .post(builder.build())
                .build();

        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        imodule.error("网络错误");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                if (request != null) {
                    res(result, imodule);
                }
            }
        });
    }

    /**
     *  上传图片
     * @param url
     * @param params
     */
    public void upLoadFile (String url, Map<String,Object> params, final NetUtilsCallBack callBack){

        MultipartBody.Builder builder = new MultipartBody.Builder();
        builder.setType(MultipartBody.FORM);
        for (Map.Entry<String, Object> map : params.entrySet()) {
            //得到键和值
            String key = map.getKey();
            Object value = map.getValue();

            //判断是否是文件
            if (value instanceof File){
                File file = (File) value;
                builder.addFormDataPart(key,file.getName(),
                        RequestBody.create(MediaType.parse("image/*"),file));
            } else {
                builder.addFormDataPart(key,value.toString());
            }
        }

        final Request request = new Request.Builder()
                .url(url)
                .post(builder.build())
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                if (callBack != null){
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.failure("网络异常");
                        }
                    });
                }
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (callBack != null){
                    //获取数据
                    final String getRes = response.body().string();

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.success(getRes);
                        }
                    });
                }
            }
        });
    }



    private void res(final String request, final Contact.Imodule imodule) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                imodule.success(request);
            }
        });

    }


}
