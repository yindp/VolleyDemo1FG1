package com.yinom.rdc.colin.volleydemo1fg1;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import java.util.Map;

/**
 * Created by Colin on 1/15/2016.
 */
public class VolleyRequest {
    public static StringRequest stringRequest;
    public static Context context;

    public static void RequestGet(Context mContext, String url, String tag, VolleyInterface vif) {
        //防止重复请求消耗内存
        MyApplication.getHttpQueues().cancelAll(tag);
        stringRequest = new StringRequest(Request.Method.GET, url, vif.loadingListener(), vif.errorListener());
        stringRequest.setTag(tag);
        MyApplication.getHttpQueues().add(stringRequest);
        MyApplication.getHttpQueues().start();

    }

    public static void RequestPost(Context nContext,String url,String tag,Map<String,String> params,VolleyInterface vif) {
        MyApplication.getHttpQueues().cancelAll(tag);

    }
}
