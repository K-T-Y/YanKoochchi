package com.example.yan_koochchi.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yan_koochchi.R;
import com.example.yan_koochchi.apiutil.ApiClient;
import com.example.yan_koochchi.apiutil.ApiInterface;
import com.example.yan_koochchi.models.responseModel;
import com.example.yan_koochchi.models.userModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
EditText txt_fname,txt_lname,txt_address,txt_mobile,txt_email,txt_nic,txt_password;
Button btn_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        txt_fname=findViewById(R.id.txt_fname);
        txt_lname=findViewById(R.id.txt_lname);
        txt_address=findViewById(R.id.txt_address);
        txt_mobile=findViewById(R.id.txt_mobile);
        txt_email=findViewById(R.id.txt_email);
        txt_nic=findViewById(R.id.txt_nic);
        txt_password=findViewById(R.id.txt_password);
        btn_register=findViewById(R.id.btn_register);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txt_fname.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter First Name",Toast.LENGTH_LONG).show();
                }
                else if(txt_lname.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Last Name",Toast.LENGTH_LONG).show();
                }
                else if(txt_address.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Address",Toast.LENGTH_LONG).show();
                }
                else if(txt_mobile.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Mobile",Toast.LENGTH_LONG).show();
                }
                else if(txt_email.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Email",Toast.LENGTH_LONG).show();
                }
                else if(txt_nic.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter NIC",Toast.LENGTH_LONG).show();
                }
                else if(txt_password.getText().toString().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Please Enter Password",Toast.LENGTH_LONG).show();
                }
                else
                {
                    register();
                }
            }
        });





    }

    private void register()
    {
            userModel user=new userModel();
            user.setfName(txt_fname.getText().toString());
            user.setlName(txt_lname.getText().toString());
            user.setAddress(txt_address.getText().toString());
            user.setMobile(txt_mobile.getText().toString());
            user.setNic(txt_nic.getText().toString());
            user.setEmail(txt_email.getText().toString());
            user.setPassword(txt_password.getText().toString());
            user.setActive(false);
            user.setCategory("User");
        Call<responseModel> call = ApiClient.getApiClient().create(ApiInterface.class).Register(user);
        call.enqueue(new Callback<responseModel>() {
            @Override
            public void onResponse(Call<responseModel> call, Response<responseModel> response) {
                if(response.body().getCode()==0)
                {
                    Toast.makeText(getApplicationContext(),"Error While Register !",Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Successfully Registered !",Toast.LENGTH_LONG).show();
                    Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<responseModel> call, Throwable t) {

            }
        });
    }
}