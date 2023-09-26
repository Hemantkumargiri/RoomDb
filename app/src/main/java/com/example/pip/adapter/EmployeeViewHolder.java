package com.example.pip.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pip.R;

public class EmployeeViewHolder extends RecyclerView.ViewHolder  {

    TextView tvEmpName,tvEmpSalary,tvEmpAge;
    ImageView img_profile;
    ConstraintLayout items_constraint;
    public EmployeeViewHolder(@NonNull View itemView, ItemClickListener itemClickListener) {
        super(itemView);

        tvEmpSalary = itemView.findViewById(R.id.tv_emp_salary);
        tvEmpName = itemView.findViewById(R.id.tv_Emp_name);
        tvEmpAge = itemView.findViewById(R.id.tv_Emp_age);
        img_profile = itemView.findViewById(R.id.img_profile);
        items_constraint = itemView.findViewById(R.id.items_constraint);
    }

}
