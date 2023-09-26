package com.example.pip.network;

import com.example.pip.model.EmployeeDataResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    //get Employee data
    @GET("employees")
    Call<EmployeeDataResponse> getEmployeeData();
}
