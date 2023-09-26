package com.example.pip.model;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "employee", indices = @Index(value = {"id"},unique = true))
@TypeConverters(Converters.class)
public class Data implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("employee_name")
    @Expose
    private String employeeName;
    @SerializedName("employee_salary")
    @Expose
    private Integer employeeSalary;
    @SerializedName("employee_age")
    @Expose
    private Integer employeeAge;
    @SerializedName("profile_image")
    @Expose
    private String profileImage;
    private boolean isSelected= false;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public Integer getEmployeeSalary() {
        return employeeSalary;
    }

    public void setEmployeeSalary(Integer employeeSalary) {
        this.employeeSalary = employeeSalary;
    }

    public Integer getEmployeeAge() {
        return employeeAge;
    }

    public void setEmployeeAge(Integer employeeAge) {
        this.employeeAge = employeeAge;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
