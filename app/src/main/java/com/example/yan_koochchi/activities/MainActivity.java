package com.example.yan_koochchi.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.yan_koochchi.R;
import com.example.yan_koochchi.apiutil.ApiClient;
import com.example.yan_koochchi.apiutil.ApiInterface;
import com.example.yan_koochchi.models.responseModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView txt_register;
    EditText txt_email, txt_password;
    Button btn_login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_register = findViewById(R.id.txt_register);
        btn_login = findViewById(R.id.btn_login);
        txt_email = findViewById(R.id.txt_email);
        txt_password = findViewById(R.id.txt_password);
        // Storing data into SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        // Creating an Editor object to edit(write to the file)
        SharedPreferences.Editor myEdit = sharedPreferences.edit();
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = txt_email.getText().toString();
                String password = txt_password.getText().toString();
                Call<responseModel> call = ApiClient.getApiClient().create(ApiInterface.class).Login(email, password);
                call.enqueue(new Callback<responseModel>() {
                    @Override
                    public void onResponse(Call<responseModel> call, Response<responseModel> response) {
                        if (response.body().getCode() == 0) {
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                        } else if (response.body().getCode() == 1) {
                            myEdit.putString("fname", response.body().getData().getfName().toString());
                            myEdit.putString("lname", response.body().getData().getlName().toString());
                            myEdit.putString("address", response.body().getData().getAddress().toString());
                            myEdit.putString("email", response.body().getData().getEmail().toString());
                            myEdit.putString("mobile", response.body().getData().getMobile().toString());
                            myEdit.putString("nic", response.body().getData().getNic().toString());
                            myEdit.putString("id", response.body().getData().getNic().toString());
                            myEdit.commit();
                            Toast.makeText(getApplicationContext(), response.body().getMessage(), Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), HomeActivity.class);
                            startActivity(intent);
                        } else {

                        }
                    }

                    @Override
                    public void onFailure(Call<responseModel> call, Throwable t) {

                    }
                });
            }
        });

    }

    private void Login() {

    }
}