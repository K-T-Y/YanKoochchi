package com.example.yan_koochchi.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import com.example.yan_koochchi.R;

public class ProfileActivity extends AppCompatActivity {
    EditText txt_fname,txt_lname,txt_address,txt_mobile,txt_email,txt_nic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        txt_fname=findViewById(R.id.txt_fname);
        txt_lname=findViewById(R.id.txt_lname);
        txt_address=findViewById(R.id.txt_address);
        txt_mobile=findViewById(R.id.txt_mobile);
        txt_email=findViewById(R.id.txt_email);
        txt_nic=findViewById(R.id.txt_nic);

        txt_fname.setText(sh.getString("fname",""));
        txt_lname.setText(sh.getString("lname",""));
        txt_address.setText(sh.getString("address",""));
        txt_mobile.setText(sh.getString("mobile",""));
        txt_email.setText(sh.getString("email",""));
        txt_nic.setText(sh.getString("nic",""));


    }
}