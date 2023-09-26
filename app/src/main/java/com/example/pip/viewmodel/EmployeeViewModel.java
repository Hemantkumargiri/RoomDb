package com.example.pip.viewmodel;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.example.pip.model.Data;
import com.example.pip.repository.EmployeeRepository;
import java.util.List;

public class EmployeeViewModel extends AndroidViewModel {
    public EmployeeRepository employeeRepository;

    private LiveData<List<Data>> getAllEmployeeData;

    private long lastFetchTime = 0; // Initialize with 0


    public EmployeeViewModel(Application application) {
        super(application);
        employeeRepository = new EmployeeRepository(application);
        getAllEmployeeData = employeeRepository.getAllEmployeeData();
    }

    public void insert(List<Data> data) {
        employeeRepository.insert(data);
    }
    public void deleteById(int id){
        employeeRepository.delete(id);
    }
    public LiveData<List<Data>> getAllEmployeeData() {
        return getAllEmployeeData;
    }

    public boolean isDataStale() {
        // Define a threshold for data staleness, e.g., data is considered stale if it's older than 1 minute
        long currentTime = System.currentTimeMillis();
        long timeThreshold = 1 * 60 * 1000; // 1 minute in milliseconds

        // Compare the last fetch time with the current time
        return (currentTime - lastFetchTime) > timeThreshold;
    }
}
