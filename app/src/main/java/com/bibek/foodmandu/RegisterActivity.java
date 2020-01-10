package com.bibek.foodmandu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bibek.foodmandu.serverresponse.SignupResponse;
import com.bibek.foodmandu.API.UserAPI;
import com.bibek.foodmandu.Model.User;
import com.bibek.foodmandu.URL.url;
import com.bibek.foodmandu.serverresponse.ImageResponse;
import com.bibek.foodmandu.serverresponse.SignupResponse;
import com.bibek.foodmandu.strictmode.StrictModeClass;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    TextView tvBackToLogin;
    EditText etFname,etLname,etContact,etEmail,etPassword,etRePassword;
    ImageView imgProfile;
    Button btnRegister;
    String imagePath;
    private  String imageName="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_register);

        tvBackToLogin=findViewById(R.id.tvback);
        etFname=findViewById(R.id.etfirstname);
        etLname=findViewById(R.id.etlastname);
        etContact=findViewById(R.id.etnumber);
        etEmail=findViewById(R.id.etemail);
        etPassword=findViewById(R.id.etpassword);
        etRePassword=findViewById(R.id.etrepassword);
        imgProfile=findViewById(R.id.imgProfile);
        btnRegister=findViewById(R.id.btnregister);

        tvBackToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });


        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (etPassword.getText().toString().equals(etRePassword.getText().toString())){
                    saveImageOnly();
                    signUp();
                }else {
                    Toast.makeText(RegisterActivity.this, "Password does not match", Toast.LENGTH_LONG).show();
                    etPassword.requestFocus();
                    return;
                }
            }
        });


    }

    private void BrowseImage(){
        Intent intent=new Intent(Intent.ACTION_PICK);
        intent.setType("image/");
        startActivityForResult(intent,0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode==RESULT_OK){
            if (data==null){
                Toast.makeText(this, "Please select an image", Toast.LENGTH_LONG).show();
            }
        }
        Uri uri=data.getData();
        imgProfile.setImageURI(uri);
        imagePath=getRealPathFromUri(uri);
    }

    private String getRealPathFromUri(Uri uri){
        String[] projection={MediaStore.Images.Media.DATA};
        CursorLoader loader=new CursorLoader(getApplicationContext(),
                uri,projection,null,null,null);
        Cursor cursor=loader.loadInBackground();
        int colIndex=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result=cursor.getString(colIndex);
        cursor.close();
        return result;
    }

    private void saveImageOnly(){
        File file=new File(imagePath);
        RequestBody requestBody=RequestBody.create(MediaType.parse("multipart/form-data"),file);
        MultipartBody.Part body=MultipartBody.Part.createFormData("imageFile",
                file.getName(),requestBody);

        UserAPI userAPI= url.getInstance().create(UserAPI.class);
        Call<ImageResponse> responseBodyCall=userAPI.uploadImage(body);

        StrictModeClass.strictmode();
        try {
            Response<ImageResponse> imageResponseResponse=responseBodyCall.execute();
            imageName=imageResponseResponse.body().getFilename();
            Toast.makeText(this, "Image inserted" + imageName, Toast.LENGTH_SHORT).show();

        }catch (IOException e){
            Toast.makeText(this, "Error" + e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }

    private void signUp(){
        String fname=etFname.getText().toString();
        String lname=etLname.getText().toString();
        String contact=etContact.getText().toString();
        String email=etEmail.getText().toString();
        String password=etPassword.getText().toString();

        User users=new User(fname,lname,contact,email,password,imageName);

        UserAPI userAPI=url.getInstance().create(UserAPI.class);
        Call<SignupResponse>signUpCall=userAPI.registerUser(users);

        signUpCall.enqueue(new Callback<SignupResponse>() {
            @Override
            public void onResponse(Call<SignupResponse> call, Response<SignupResponse> response) {
                if (!response.isSuccessful()){
                    Toast.makeText(RegisterActivity.this, "Code " + response.code(), Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(RegisterActivity.this, "Registered", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<SignupResponse> call, Throwable t) {

                Toast.makeText(RegisterActivity.this, "Error", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
