package com.example.pip.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.pip.model.Data;
import com.example.pip.model.EmployeeDataResponse;

import java.util.List;

@Dao
public interface EmployeeDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Data> employeeList);

    @Query("SELECT * FROM employee")
    LiveData<List<Data>> getAllEmployeeData();

//    @Query("DELETE FROM employee")
//    void deleteAll();

    @Query("DELETE FROM employee WHERE id = :employeeId")
    void deleteById(int employeeId);

}
