package com.example.yan_koochchi.activities;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.yan_koochchi.R;
import com.example.yan_koochchi.adapters.ticketAdapter;
import com.example.yan_koochchi.apiutil.ApiClient;
import com.example.yan_koochchi.apiutil.ApiInterface;
import com.example.yan_koochchi.models.bookingModel;
import com.example.yan_koochchi.models.responseModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyBookingActivity extends AppCompatActivity {
    SharedPreferences sh ;
    ArrayList<bookingModel> upcoming,completed,cancel;
RecyclerView recycle;
TextView txt_upcoming,txt_completed,txt_cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_booking);
        sh= getSharedPreferences("MySharedPref", MODE_PRIVATE);
        recycle=findViewById(R.id.recycle);
        txt_cancel=findViewById(R.id.txt_cancel);
        txt_completed=findViewById(R.id.txt_completed);
        txt_upcoming=findViewById(R.id.txt_upcoming);
        getBooking();
        txt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recycle.setAdapter(null);
                ticketAdapter adapter = new ticketAdapter(cancel, getApplicationContext());
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, true);
                recycle.setLayoutManager(layoutManager);
                //storyRV.setNestedScrollingEnabled(false);
                recycle.setAdapter(adapter);
            }
        });
        txt_upcoming.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recycle.setAdapter(null);
                ticketAdapter adapter = new ticketAdapter(upcoming, getApplicationContext());
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, true);
                recycle.setLayoutManager(layoutManager);
                //storyRV.setNestedScrollingEnabled(false);
                recycle.setAdapter(adapter);
            }
        });
        txt_completed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recycle.setAdapter(null);
                ticketAdapter adapter = new ticketAdapter(completed, getApplicationContext());
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, true);
                recycle.setLayoutManager(layoutManager);
                //storyRV.setNestedScrollingEnabled(false);
                recycle.setAdapter(adapter);
            }
        });
    }

    private void getBooking()
    {
        Call<List<bookingModel>> call = ApiClient.getApiClient().create(ApiInterface.class).GetUserBookings(sh.getString("id",""));
        call.enqueue(new Callback<List<bookingModel>>() {
            @Override
            public void onResponse(Call<List<bookingModel>> call, Response<List<bookingModel>> response) {
                upcoming=new ArrayList<>() ;
                completed=new ArrayList<>() ;
                cancel=new ArrayList<>() ;
                    for (int i=0;i<response.body().size();i++)
                    {
                        System.out.println("xxx"+response.body().get(i).getStatus());

                        if(response.body().get(i).getStatus()==0)
                        {
                            upcoming.add(response.body().get(i));
                        }
                        else if(response.body().get(i).getStatus()==1)
                        {
                            completed.add(response.body().get(i));
                        }
                        else if(response.body().get(i).getStatus()==2)
                        {
                            cancel.add(response.body().get(i));
                        }

                    }
                ticketAdapter adapter = new ticketAdapter(upcoming, getApplicationContext());
                LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, true);
                recycle.setLayoutManager(layoutManager);
                //storyRV.setNestedScrollingEnabled(false);
                recycle.setAdapter(adapter);


            }

            @Override
            public void onFailure(Call<List<bookingModel>> call, Throwable t) {

            }
        });

    }
}