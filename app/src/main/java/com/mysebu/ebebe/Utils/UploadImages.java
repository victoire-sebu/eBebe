package com.mysebu.ebebe.Utils;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class UploadImages {

    private static UploadImages mInstance;
    private RequestQueue requestQueue;
    private static Context mContext;

    private UploadImages(Context context){
        mContext=context;
        requestQueue=getRequestQueue();
    }

    private RequestQueue getRequestQueue(){
        if (requestQueue==null)
            requestQueue= Volley.newRequestQueue(mContext.getApplicationContext());
        return requestQueue;
    }

    public static synchronized UploadImages getInstance(Context context){
        if(mInstance==null){
            mInstance=new UploadImages(context);
        }
        return mInstance;
    }

    public <T> void addToResquestQue(Request<T> request){
        getRequestQueue().add(request);
    }

}
