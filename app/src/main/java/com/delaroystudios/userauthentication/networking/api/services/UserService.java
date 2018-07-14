package com.delaroystudios.userauthentication.networking.api.services;

import com.delaroystudios.userauthentication.model.Message;
import com.delaroystudios.userauthentication.model.Task;
import com.delaroystudios.userauthentication.model.Tasks;
import com.delaroystudios.userauthentication.model.UserLogin;
import com.delaroystudios.userauthentication.networking.Routes;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

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

    @FormUrlEncoded
    @POST(Routes.CREATE_TASK)
    Call<Message> createTask(@Field("title") String title, @Field("username") String username, @Field("taskdate") String taskdate, @Field("tasktime") String tasktime, @Field("task") String task);

    @GET(Routes.FETCH_TASK + "/{username}")
    Call<Tasks> fetchTask(@Path("username") String username);

}
