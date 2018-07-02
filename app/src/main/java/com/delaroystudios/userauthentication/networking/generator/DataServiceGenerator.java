package com.delaroystudios.userauthentication.networking.generator;

import android.content.Context;

import com.delaroystudios.userauthentication.BuildConfig;
import com.delaroystudios.userauthentication.networking.api.interceptors.AuthenticationInterceptor;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.text.DateFormat;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by delaroy on 6/26/18.
 */

public class DataServiceGenerator {
    public static <S> S createService(Class<S> serviceClass, Context context, String baseUrl) {
        baseUrl = baseUrl.endsWith("/") ? baseUrl : baseUrl + "/";
        String token = "";
        Gson gson = new GsonBuilder()
                .enableComplexMapKeySerialization()
                .serializeNulls()
                .setDateFormat(DateFormat.LONG)
                .setPrettyPrinting()
                .setVersion(1.0)
                .create();

        Retrofit.Builder builder = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder()
                .readTimeout(90, TimeUnit.SECONDS)
                .connectTimeout(90, TimeUnit.SECONDS)
                .writeTimeout(90, TimeUnit.SECONDS)
                .cache(null);

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor()
                    .setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }
       /* if(context != null) {
            token = PreferenceUtils.getToken(context);
        }
        if (token != null) {
            AuthenticationInterceptor authenticationInterceptor =
                    new AuthenticationInterceptor(token);
            httpClient.addInterceptor(authenticationInterceptor);
        }else {

        }*/
        builder.client(httpClient.build());
        Retrofit retrofit = builder.build();
        return  retrofit.create(serviceClass);
    }
}