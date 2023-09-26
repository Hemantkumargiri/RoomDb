package com.example.pip.activity;

import static com.example.pip.repository.EmployeeRepository.database;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import com.example.pip.R;
import com.example.pip.adapter.EmployeeAdapter;
import com.example.pip.adapter.ItemClickListener;
import com.example.pip.model.Data;
import com.example.pip.viewmodel.EmployeeViewModel;

import org.jetbrains.annotations.Async;

import java.util.ArrayList;
import java.util.List;

public class EmployeeListActivity extends AppCompatActivity {
    RecyclerView rvEmp;
    List<Data> EmpList = new ArrayList<>();
    public EmployeeAdapter adapter;
    public EmployeeViewModel employeeViewModel;
    public ConstraintLayout lr_checkbox;
    public CheckBox checkbox;
    public boolean flagSelectAll = false;
    public Button btnDelete;
    public int EmpId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_list);
        employeeViewModel = new ViewModelProvider(this).get(EmployeeViewModel.class);
        init();
        clickListeners();
        setupRecyclerview();
        fetchDataFromDb();
    }

    private void fetchDataFromDb() {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                database.employeeDao().getAllEmployeeData();
            }
        });
    }

    private void clickListeners() {
        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkbox.isChecked()) {
                    flagSelectAll = true;
                    adapter.selectAllItem(true);
                    adapter.notifyDataSetChanged();
                } else {
                    adapter.selectAllItem(false);
                    adapter.notifyDataSetChanged();
                }
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    List<Long> selectedIds = adapter.getSelectedIds();
                    for (Long id : selectedIds) {
                        /*new Thread(new Runnable() {
                            @Override
                            public void run()
                            {
                                employeeViewModel.deleteById(Math.toIntExact(id));
                            }
                        }).start();*/
                        AsyncTask.execute(new Runnable() {
                            @Override
                            public void run() {
                                employeeViewModel.deleteById(Math.toIntExact(id));
                            }
                        });

                    }
                }
            }
        });
    }


    private void setupRecyclerview() {
        adapter = new EmployeeAdapter(EmployeeListActivity.this, EmpList, new ItemClickListener() {
            @Override
            public void onClick(int position) {
                if (isItemSelected()) {
                    EmpId = EmpList.get(position).getId();
                    lr_checkbox.setVisibility(View.VISIBLE);

                } else {

                }
            }
        });

        rvEmp.setAdapter(adapter);

        employeeViewModel.getAllEmployeeData().observe(this, new Observer<List<Data>>() {
            @Override
            public void onChanged(List<Data> empList) {
                adapter.getAllEmployees(empList);
                rvEmp.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }
        });
    }


    @SuppressLint("WrongViewCast")
    private void init() {
        rvEmp = findViewById(R.id.rvEmpData);
        lr_checkbox = findViewById(R.id.lr_checkbox);
        checkbox = findViewById(R.id.checkbox);
        btnDelete = findViewById(R.id.btn_delete);
    }

    private boolean isItemSelected() {
        for (int i = 0; i < EmpList.size(); i++) {
            if (EmpList.get(i).isSelected()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}