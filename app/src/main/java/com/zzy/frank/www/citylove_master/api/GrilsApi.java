package com.zzy.frank.www.citylove_master.api;


import com.zzy.frank.www.citylove_master.bean.Grils;

import java.util.List;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by JDD on 2016/4/8.
 */
public interface GrilsApi
{

    @GET("data.txt")
    Observable<List<Grils>> getJoke();
}
