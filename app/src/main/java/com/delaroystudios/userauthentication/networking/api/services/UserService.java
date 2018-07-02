package com.delaroystudios.userauthentication.networking.api.services;

import com.delaroystudios.userauthentication.model.Message;
import com.delaroystudios.userauthentication.model.UserLogin;
import com.delaroystudios.userauthentication.networking.Routes;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by delaroy on 6/26/18.
 */

public interface UserService {
    @FormUrlEncoded
    @POST(Routes.CREATE_USER)
    Call<Message> createUser(@Field("name") String name, @Field("username") String username, @Field("password") String password);

    @FormUrlEncoded
    @POST(Routes.USER_LOGIN)
    Call<UserLogin> userLogin(@Field("username") String username, @Field("password") String password);
}
