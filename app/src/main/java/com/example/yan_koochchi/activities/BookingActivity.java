package com.example.yan_koochchi.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.yan_koochchi.R;
import com.example.yan_koochchi.apiutil.ApiClient;
import com.example.yan_koochchi.apiutil.ApiInterface;
import com.example.yan_koochchi.models.dataResponseModel;
import com.example.yan_koochchi.models.responseModel;
import com.example.yan_koochchi.models.scheduleModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingActivity extends AppCompatActivity {
    private Spinner spn_from,spn_to,spn_schedule;
    private CalendarView cln_date;
    private EditText txt_count;
    ArrayList<String> options=new ArrayList<String>();
    ArrayList<scheduleModel> allSchedules;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        spn_from=findViewById(R.id.spn_from);
        spn_to=findViewById(R.id.spn_to);
        spn_schedule=findViewById(R.id.spn_schedule);
        txt_count=findViewById(R.id.txt_count);
        cln_date=findViewById(R.id.cln_date);
        Call<dataResponseModel> call = ApiClient.getApiClient().create(ApiInterface.class).GetAllSchedules();
        call.enqueue(new Callback<dataResponseModel>() {
            @Override
            public void onResponse(Call<dataResponseModel> call, Response<dataResponseModel> response) {

                allSchedules=new ArrayList<>();
                allSchedules=  response.body().getData();
                for(int i =0;i<allSchedules.size();i++){
                    options.add(allSchedules.get(i).getDeparture()+" - "+allSchedules.get(i).getDesignation());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item,options);
                spn_schedule.setAdapter(adapter); // this will set list of values to spinner

               // spn_schedule.setSelection(options.indexOf(<value you want to show selected>)));
            }

            @Override
            public void onFailure(Call<dataResponseModel> call, Throwable t) {

            }
        });



    }
}