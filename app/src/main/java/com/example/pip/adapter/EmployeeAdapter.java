package com.example.pip.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.pip.R;
import com.example.pip.activity.EmployeeDetailsActivity;
import com.example.pip.activity.EmployeeListActivity;
import com.example.pip.activity.HomeActivity;
import com.example.pip.model.Data;
import com.example.pip.model.EmployeeDataResponse;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeViewHolder> {
    Activity activity;
    private List<Data> EmpList = new ArrayList<>();

    private  ItemClickListener itemClickListener;

    public  Set<Long> selectedIds = new HashSet<>();


    public EmployeeAdapter(EmployeeListActivity employeeListActivity, List<Data> empList, ItemClickListener itemClickListener) {
        this.activity = employeeListActivity;
        this.EmpList = empList;
        this.itemClickListener = itemClickListener;
    }


    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(activity).inflate(R.layout.data_item, parent, false);
        return new EmployeeViewHolder(view,itemClickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, @SuppressLint("RecyclerView") int position) {
        Data data = EmpList.get(position);
        holder.tvEmpAge.setText("" + data.getEmployeeAge());
        holder.tvEmpName.setText("" + data.getEmployeeName());
        holder.tvEmpSalary.setText("" + data.getEmployeeSalary());


        Glide.with(activity)
                .load(data.getProfileImage())
                .into(holder.img_profile);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    Data item = EmpList.get(position);
                    if (!item.isSelected()) {
                        item.setSelected(true);
                        selectedIds.add(Long.valueOf(item.getId()));
                    } else {
                        item.setSelected(false);
                        selectedIds.remove(Long.valueOf(item.getId()));
                    }
                    notifyItemChanged(position);
                    itemClickListener.onClick(position);
                }
                return true;
            }
        });

        if (EmpList.get(position).isSelected()) {
            holder.items_constraint.setBackgroundColor(Color.RED);
        } else {
            holder.items_constraint.setBackgroundColor(Color.TRANSPARENT);
        }

// Handle regular item clicks here using itemClickListener if needed
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    // Handle regular item clicks here
                    itemClickListener.onClick(position);
                    Intent intent = new Intent(activity,EmployeeDetailsActivity.class);
                    intent.putExtra("name",""+data.getEmployeeName());
                    intent.putExtra("salary",""+data.getEmployeeSalary());
                    intent.putExtra("age",""+data.getEmployeeAge());
                    intent.putExtra( "img_profile",""+data.getProfileImage());
                    intent.putExtra("id",""+data.getId());
                    activity.startActivity(intent);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return EmpList.size();
    }

    public void getAllEmployees(List<Data> empList) {
        this.EmpList = empList;
    }

    public void selectAllItem(boolean isSelectedAll) {
        selectedIds.clear(); // Clear the previous selection

        for (Data item : EmpList) {
            item.setSelected(isSelectedAll);

            if (isSelectedAll) {
                selectedIds.add(Long.valueOf(item.getId())); // Add the ID to the selectedIds set
            }
        }

        notifyDataSetChanged(); // Notify the adapter that data has changed
    }


    public List<Long> getSelectedIds() {
        return new ArrayList<>(selectedIds);
    }
}
