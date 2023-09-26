package com.example.pip.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class EmployeeDataResponse  {

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("data")
    @Expose
    private List<Data> data;
    @SerializedName("message")
    @Expose
    private String message;



    public EmployeeDataResponse(String status, List<Data> data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public EmployeeDataResponse() {

    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
