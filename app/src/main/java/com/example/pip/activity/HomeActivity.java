package com.example.pip.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.pip.model.Data;
import com.example.pip.network.Retrofit;
import com.example.pip.repository.EmployeeRepository;
import com.example.pip.viewmodel.EmployeeViewModel;
import com.example.pip.R;
import com.example.pip.model.EmployeeDataResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {
    public Button btnShowData, btnLoadData;
    public ProgressBar pgBar;
    public EmployeeViewModel employeeViewModel;
    public EmployeeRepository employeeRepository;
    public List<Data> EmpList = new ArrayList<>();


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
        btnShowData = findViewById(R.id.btn_ShowData);
        btnLoadData = findViewById(R.id.btn_LoadData);
        pgBar = findViewById(R.id.pgBar);

        employeeRepository = new EmployeeRepository(getApplication());
        setClickListeners();
    }

    private void setClickListeners() {
        btnShowData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HomeActivity.this, EmployeeListActivity.class);
                startActivity(intent);
            }
        });

        btnLoadData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pgBar.setVisibility(View.VISIBLE);
               /* Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        fetchEmployeeData();
                    }
                })*/
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                      fetchEmployeeData();
                        } catch (Exception e) {
                            Log.e("HomeActivity", "Error fetching data: " + e.getMessage());
                            e.printStackTrace();
                        }
                    }
                }).start();

            }
        });
    }

    private void fetchEmployeeData() {
        try {
            // Your API call code here
            Retrofit retrofit = new Retrofit();
            Call<EmployeeDataResponse> call = retrofit.api.getEmployeeData();

            call.enqueue(new Callback<EmployeeDataResponse>() {
                @Override
                public void onResponse(Call<EmployeeDataResponse> call, Response<EmployeeDataResponse> response) {
                    if (response.isSuccessful()) {
                        pgBar.setVisibility(View.GONE);
                        List<Data> data = response.body().getData();
                        employeeViewModel.insert(data);
                        EmpList.clear();
                        EmpList.addAll(data);
                        Log.d("main", "onResponse: " + response.body().getData().size());
                    } else {
                        pgBar.setVisibility(View.GONE);
                        Toast.makeText(HomeActivity.this, "Api Failed", Toast.LENGTH_SHORT).show();
                        // Handle unsuccessful response (e.g., non-200 HTTP status code)
                        Log.e("main", "Unsuccessful response: " + response.code());
                        // You can also add error handling code here if needed
                    }
                }

                @Override
                public void onFailure(Call<EmployeeDataResponse> call, Throwable t) {
                    // Handle network or other exceptions here
                    Log.e("main", "API call failed: " + t.getMessage(), t);
                    Toast.makeText(HomeActivity.this, "Something went wrong...", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            // Handle general exceptions here
            Log.e("main", "Exception occurred during API call: " + e.getMessage(), e);
            Toast.makeText(HomeActivity.this, "An error occurred while fetching data.", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
    }
}