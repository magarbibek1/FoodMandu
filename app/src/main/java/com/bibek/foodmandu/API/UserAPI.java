package com.bibek.foodmandu.API;

import com.bibek.foodmandu.Model.User;
import com.bibek.foodmandu.serverresponse.ImageResponse;
import com.bibek.foodmandu.serverresponse.SignupResponse;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

public interface UserAPI {
    @POST("users/signup")
    Call<SignupResponse> registerUser(@Body User users);

    @FormUrlEncoded
    @POST("users/login")
    Call<SignupResponse> checkUser(@Field("email") String email, @Field("password") String password);

    @Multipart
    @POST("upload")
    Call<ImageResponse> uploadImage(@Part MultipartBody.Part img);

    @GET("users/me")
    Call<User> getUserDetails(@Header("Authorization")String token);

    @GET("users/images")
    Call<ImageResponse> getImage(@Part MultipartBody.Part img);
}
