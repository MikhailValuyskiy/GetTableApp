package ru.example.mvaluyskiy.gettableapp.api.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by m.valuyskiy on 15.04.17.
 */

public class ApiClient {

    public static final String BASE_URL = "https://s3-eu-west-1.amazonaws.com/";
    private static Retrofit retrofit = null;

    public static Retrofit getClient() {

        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(
                    ))
                    .build();
        }
        return retrofit;
    }

}