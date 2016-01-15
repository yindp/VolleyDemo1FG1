package com.yinom.rdc.colin.volleydemo1fg1;

import android.content.Context;

import com.android.volley.Response;
import com.android.volley.VolleyError;

/**
 * Created by Colin on 1/15/2016.
 */
public abstract class VolleyInterface {
    public Context mContext;
    public Response.Listener<String> mListener;
    public Response.ErrorListener mErrorListener;

    public VolleyInterface(Context context,Response.Listener<String> listener,Response.ErrorListener errorListener){
        this.mContext=context;
        this.mListener=listener;
        this.mErrorListener=errorListener;
    }
    public abstract void onMySuccess(String result);
    public abstract void onMyError(VolleyError error);

    public Response.Listener<String> loadingListener(){
        mListener=new Response.Listener<String>() {
            @Override
            public void onResponse(String s) {
                onMySuccess(s);
            }
        };
        return mListener;
    }
    public Response.ErrorListener errorListener(){
        mErrorListener=new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                onMyError(volleyError);
            }
        };
        return errorListener();
    }
}

