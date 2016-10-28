package com.zzy.frank.www.citylove_master.api;

import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by SkyEyesStion on 2016/2/26.
 */
public class RxService
{
    private static final String BASETESTURL = "https://oetlj49uy.qnssl.com/";
   /* private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Retrofit retrofit = new Retrofit.Builder().baseUrl(BASETESTURL).client(okHttpClient)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//RxJava适配器
            .addConverterFactory(GsonConverterFactory.create())//Gson转换器
            .build();

    private static Retrofit retrofit_imgs = new Retrofit.Builder().baseUrl(BASETESTURL_IMGS).client(okHttpClient)
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())//RxJava适配器
            .addConverterFactory(GsonConverterFactory.create())//Gson转换器
            .build();

    public static <T> T createApi(Class<T> clazz)
    {
        return retrofit.create(clazz);
    }

    public static <T> T createApi_img(Class<T> clazz)
    {
        return retrofit_imgs.create(clazz);
    }*/

    private static GrilsApi mGrilsApi;
    private static OkHttpClient okHttpClient = new OkHttpClient();
    private static Converter.Factory gsonConverterFactory = GsonConverterFactory.create();
    private static CallAdapter.Factory rxJavaCallAdapterFactory = RxJavaCallAdapterFactory.create();


    public static GrilsApi getJokeApi()
    {

        if (mGrilsApi == null)
        {
            Retrofit retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASETESTURL)
                    .addConverterFactory(gsonConverterFactory)
                    .addCallAdapterFactory(rxJavaCallAdapterFactory)
                    .build();
            mGrilsApi = retrofit.create(GrilsApi.class);
        }
        return mGrilsApi;
    }
}
