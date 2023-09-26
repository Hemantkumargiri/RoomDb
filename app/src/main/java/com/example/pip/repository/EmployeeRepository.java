package com.example.pip.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.pip.dao.EmployeeDao;
import com.example.pip.database.EmployeeDatabase;
import com.example.pip.model.Data;
import com.example.pip.model.EmployeeDataResponse;
import com.example.pip.network.API;

import java.util.List;

public class EmployeeRepository {
    private API apiManager;

    public static EmployeeDatabase database;

    private LiveData<List<Data>> getAllEmployeeData;



    public EmployeeRepository(Application application)
    {
        database= EmployeeDatabase.getInstance(application);
        getAllEmployeeData= (LiveData<List<Data>>) database.employeeDao().getAllEmployeeData();
    }


    public void insert(List<Data> data) {
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                database.employeeDao().insert(data);
            }
        });

    }


    public void delete(int id){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                database.employeeDao().deleteById(id);
            }
        });
    }
    public LiveData<List<Data>> getAllEmployeeData() {
    return getAllEmployeeData;
    }


    /*static class InsertAsynTask extends AsyncTask<List<EmployeeDataResponse>,Void,Void> {

        private EmployeeDao employeeDao;

        InsertAsynTask(EmployeeDatabase employeeDatabase)
        {
            employeeDao=employeeDatabase.employeeDao();
        }

        protected Void doInBackground(List<EmployeeDataResponse> lists) {
            database.employeeDao().insert(lists);
            return null;
        }

        @Override
        protected Void doInBackground(List<EmployeeDataResponse>... lists) {

            return null;
        }
    }*/
}
