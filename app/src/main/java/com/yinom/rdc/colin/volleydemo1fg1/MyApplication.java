package com.yinom.rdc.colin.volleydemo1fg1;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by Colin on 1/15/2016.
 */
public class MyApplication extends Application{
    public static RequestQueue queues;
    @Override
    public void onCreate() {
        super.onCreate();
        queues= Volley.newRequestQueue(getApplicationContext());
    }

    public static RequestQueue getHttpQueues(){
        return queues;
    }
}
