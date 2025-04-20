package com.example.second_retro_java;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.GET;
import retrofit2.http.Field;



public interface ApiService {
    @FormUrlEncoded
    @POST("insert.php")
    Call<ResponseModel> insetem(
            @Field("name")String name,
            @Field("email")String email,
            @Field("mob")String mobile
    );

    @GET("selectusers.php")
    Call<List<Users>> getAllUsers();

    @FormUrlEncoded
    @POST("updateusers.php")
    Call<ResponseModel> updateu(
            @Field("id") int id,
            @Field("name") String name,
            @Field("email") String email,
            @Field("mob") String mobile
    );

    @FormUrlEncoded
    @POST("deleteuser.php")
    Call<ResponseModel>deleteu(
      @Field("id") int id
    );
}
