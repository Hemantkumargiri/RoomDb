package com.example.pip.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.pip.R;
import com.example.pip.viewmodel.EmployeeViewModel;

public class EmployeeDetailsActivity extends AppCompatActivity {

    TextView tvEmpName,tvEmpSalary,tvEmpAge;
    int EmpId;
    Button btn_delete;
    EmployeeViewModel employeeViewModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_details);
        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
        init();
        Intent intent = getIntent();
        tvEmpName.setText(intent.getStringExtra("name"));
        tvEmpSalary.setText(intent.getStringExtra("salary"));
        tvEmpAge.setText(intent.getStringExtra("age"));
        EmpId = Integer.parseInt(intent.getStringExtra("id"));
        Log.d("empid", "onCreate: "+EmpId);
        setClickListener();
    }
    public void init(){
        tvEmpSalary = findViewById(R.id.tv_emp_salary);
        tvEmpName = findViewById(R.id.tv_Emp_name);
        tvEmpAge = findViewById(R.id.tv_Emp_age);
        btn_delete = findViewById(R.id.btn_delete);
    }
    public void setClickListener()
    {
        btn_delete.setOnClickListener(v -> {
            deleteEmployeeById(EmpId);
        });
    }

    private void deleteEmployeeById(int empId) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                employeeViewModel.deleteById(empId);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(EmployeeDetailsActivity.this, "Employee deleted", Toast.LENGTH_SHORT).show();
                        // Optionally, navigate back to the previous screen or perform other actions
                        finish();
                    }
                });
            }
        }).start();
    }


}