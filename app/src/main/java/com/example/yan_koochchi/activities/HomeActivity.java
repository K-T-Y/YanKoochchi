package com.example.yan_koochchi.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.yan_koochchi.R;

public class HomeActivity extends AppCompatActivity {
LinearLayout pnl_profile,pnl_booking,pnl_mybookings,pnl_contactus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        pnl_profile=findViewById(R.id.pnl_profile);
        pnl_booking=findViewById(R.id.pnl_booking);
        pnl_mybookings=findViewById(R.id.pnl_mybookings);
        pnl_contactus=findViewById(R.id.pnl_contactus);

        pnl_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(intent);
            }
        });
        pnl_mybookings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(getApplicationContext(), MyBookingActivity.class);
                startActivity(intent);
            }
        });
        pnl_booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),BookingActivity.class);
                startActivity(intent);
            }
        });
    }
}