package com.bibek.foodmandu.bll;

import com.bibek.foodmandu.API.UserAPI;
import com.bibek.foodmandu.URL.url;
import com.bibek.foodmandu.serverresponse.SignupResponse;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;

public class Loginbll {
    boolean isSuccess = false;

    public boolean checkUser(String email, String password) {

        UserAPI userAPI= url.getInstance().create(UserAPI.class);
        Call<SignupResponse> usersCall = userAPI.checkUser(email, password);

        try {
            Response<SignupResponse> loginResponse = usersCall.execute();
            if (loginResponse.isSuccessful() &&
                    loginResponse.body().getStatus().equals("Login success!")) {

                url.token += loginResponse.body().getToken();
                // Url.Cookie = imageResponseResponse.headers().get("Set-Cookie");
                isSuccess = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isSuccess;
    }
}
