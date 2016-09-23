package com.zzy.frank.www.citylove_master.util;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.zzy.frank.www.citylove_master.PushApplication;

import java.util.Map;

/**
 * Created by Administrator on 2015/11/25.
 */
public class VolleyRequest
{

    public static StringRequest mRequest;
    public static Context context;

    public static void RequestGet(Context context, String url, String tag, VolleyInterface volleyInterface)
    {

        PushApplication.getHttpQueues().cancelAll(tag);
        mRequest = new StringRequest(Request.Method.GET, url, volleyInterface.loadingListener(),
                volleyInterface.errorListener());
        PushApplication.getHttpQueues().add(mRequest);
        PushApplication.getHttpQueues().start();
    }

    public static void RequestPost(Context context, String url, String tag, final Map<String, String> params,
                                   VolleyInterface volleyInterface)
    {
        PushApplication.getHttpQueues().cancelAll(tag);
        mRequest = new StringRequest(Request.Method.POST, url, volleyInterface.loadingListener(),
                volleyInterface.errorListener())
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError
            {
                return params;
            }
        };
        PushApplication.getHttpQueues().add(mRequest);
        PushApplication.getHttpQueues().start();
    }

}
