package com.example.king.dsday003.utils;

import android.os.Handler;

import com.example.king.dsday003.contact.Contact;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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

    public void toGet(final Contact.Imodule imodule){
        final Request request = new Request.Builder()
                .url(Api.API)
                .get()
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

    private void res(final String request, final Contact.Imodule imodule) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                imodule.success(request);
            }
        });

    }


}
