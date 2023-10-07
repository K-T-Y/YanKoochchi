package com.example.yan_koochchi.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.yan_koochchi.R;
import com.example.yan_koochchi.apiutil.ApiClient;
import com.example.yan_koochchi.apiutil.ApiInterface;
import com.example.yan_koochchi.models.SpinnerModel;
import com.example.yan_koochchi.models.bookingModel;
import com.example.yan_koochchi.models.dataResponseModel;
import com.example.yan_koochchi.models.responseModel;
import com.example.yan_koochchi.models.scheduleModel;

import java.util.ArrayList;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookingActivity extends AppCompatActivity {
    private Spinner spn_from, spn_to, spn_schedule;
    private CalendarView cln_date;
    private EditText txt_count;
    Button btn_register;
    ArrayList<SpinnerModel> options = new ArrayList<SpinnerModel>();
    ArrayList<String> list = new ArrayList<String>();
    ArrayList<scheduleModel> allSchedules;
    String date;
    String from, to;
    SharedPreferences sh;
    String selected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking);

        spn_from = findViewById(R.id.spn_from);
        spn_to = findViewById(R.id.spn_to);
        spn_schedule = findViewById(R.id.spn_schedule);
        txt_count = findViewById(R.id.txt_count);
        cln_date = findViewById(R.id.cln_date);
        btn_register = findViewById(R.id.btn_register);
        sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        Call<dataResponseModel> call = ApiClient.getApiClient().create(ApiInterface.class).GetAllSchedules();
        call.enqueue(new Callback<dataResponseModel>() {
            @Override
            public void onResponse(Call<dataResponseModel> call, Response<dataResponseModel> response) {

                allSchedules = new ArrayList<>();
                allSchedules = response.body().getData();
                for (int i = 0; i < allSchedules.size(); i++) {

                    SpinnerModel sp = new SpinnerModel(allSchedules.get(i).getId(), allSchedules.get(i).getDeparture() + " - " + allSchedules.get(i).getDesignation());
                    options.add(sp);
                    list.add(allSchedules.get(i).getDeparture() + " - " + allSchedules.get(i).getDesignation());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, list);
                spn_schedule.setAdapter(adapter);

            }

            @Override
            public void onFailure(Call<dataResponseModel> call, Throwable t) {

            }
        });
        cln_date.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                date = year + "-" + month + "-" + dayOfMonth;
            }
        });
        spn_schedule.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selected = spn_schedule.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spn_from.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                from = spn_from.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spn_to.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                to = spn_to.getSelectedItem().toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (Integer.parseInt(txt_count.getText().toString()) > 4) {
                        Toast.makeText(getApplicationContext(), "Please Enter Less Than 4 For Count", Toast.LENGTH_LONG).show();
                    } else {

                        Optional<SpinnerModel> shc = options.stream().filter(e -> e.getText().equals(selected)).findFirst();
                        String selectedSch = shc.get().getValue();
                        bookingModel model = new bookingModel();
                        model.setCount(Integer.parseInt(txt_count.getText().toString()));
                        model.setTo(to);
                        model.setFrom(from);
                        model.setStatus(0);
                        model.setUser(sh.getString("id", ""));
                        model.setDate(date);
                        model.setTrain_Schedule(selectedSch);

                        Call<responseModel> call = ApiClient.getApiClient().create(ApiInterface.class).SaveBooking(model);
                        call.enqueue(new Callback<responseModel>() {
                            @Override
                            public void onResponse(Call<responseModel> call, Response<responseModel> response) {
                                if(response.body().getCode()==0)
                                {
                                    Toast.makeText(getApplicationContext(),"Error While Saving",Toast.LENGTH_LONG).show();
                                }
                                else
                                {
                                    Toast.makeText(getApplicationContext(),"Saved Successfully",Toast.LENGTH_LONG).show();
                                    Intent intent=new Intent(getApplicationContext(),MyBookingActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            }

                            @Override
                            public void onFailure(Call<responseModel> call, Throwable t) {
                                Toast.makeText(getApplicationContext(),"Error While Saving",Toast.LENGTH_LONG).show();
                            }
                        });


                    }
                } catch (Exception ex) {

                }

            }
        });


    }
}