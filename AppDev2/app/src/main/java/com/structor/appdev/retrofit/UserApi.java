package com.structor.appdev.retrofit;

import com.structor.appdev.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.DELETE;
import retrofit2.http.Path;

public interface UserApi {

    @GET("/")
    Call<List<User>> getAllUsers();

    @POST("/add-user") // Correct the endpoint URL
    Call<User> save(@Body User user);

    @PUT("/update-user")
    Call<User> updateUser(@Body User user);

    @DELETE("/users/{id}")
    Call<Void> deleteUser(@Path("id") int id);

}
