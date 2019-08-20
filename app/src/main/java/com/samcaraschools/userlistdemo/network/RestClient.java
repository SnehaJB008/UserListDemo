package com.samcaraschools.userlistdemo.network;

import com.samcaraschools.userlistdemo.module.UserData;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RestClient {
    @GET("users")
    Call<UserData> getUsers(@Query("page") int page);

}
