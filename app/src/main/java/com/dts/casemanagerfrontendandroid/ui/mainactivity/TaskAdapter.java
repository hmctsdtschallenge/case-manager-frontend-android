package com.dts.casemanagerfrontendandroid.ui.mainactivity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.dts.casemanagerfrontendandroid.R;
import com.dts.casemanagerfrontendandroid.databinding.TaskItemBinding;
import com.dts.casemanagerfrontendandroid.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {
    private List<Task> tasks;
    private final RecyclerViewInterface recyclerViewInterface;
    public TaskAdapter(List<Task> tasks, RecyclerViewInterface recyclerViewInterface) {
        this.tasks = tasks;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        TaskItemBinding binding = DataBindingUtil.inflate(
                LayoutInflater.from(parent.getContext()),
                R.layout.task_item,
                parent,
                false
        );
        return new TaskViewHolder(binding, recyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = tasks.get(position);
        holder.taskItemBinding.setTask(task);
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        private TaskItemBinding taskItemBinding;

        public TaskViewHolder(TaskItemBinding taskItemBinding, RecyclerViewInterface recyclerViewInterface) {
            super(taskItemBinding.getRoot());
            this.taskItemBinding = taskItemBinding;

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(recyclerViewInterface != null) {
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION) {
                            recyclerViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
