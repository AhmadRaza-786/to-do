package com.example.todo.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.model.Task;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.MyViewHolder> {

    private List<Task> listTasks;
    public TaskAdapter(List<Task> list) {
        this.listTasks = list;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View listItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_list_adapter, parent, false);
        return new MyViewHolder(listItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Task task = listTasks.get(position);
        holder.list.setText(task.getTaskName());
    }

    @Override
    public int getItemCount() {
        return listTasks.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView list;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            list = itemView.findViewById(R.id.textList);
        }
    }
}
