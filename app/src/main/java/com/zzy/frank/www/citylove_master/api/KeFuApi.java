package com.zzy.frank.www.citylove_master.api;

import com.zzy.frank.www.citylove_master.bean.KeFuCode;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by pc on 2016/11/25.
 */
public interface KeFuApi
{
    @FormUrlEncoded
    @POST("openapi/api")
    Observable<KeFuCode> getKefu(@Field("key") String key, @Field("info") String info);
}
