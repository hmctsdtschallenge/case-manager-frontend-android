package com.dts.casemanagerfrontendandroid.ui.updatetaskactivity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dts.casemanagerfrontendandroid.model.StatusDTO;
import com.dts.casemanagerfrontendandroid.model.Task;
import com.dts.casemanagerfrontendandroid.ui.mainactivity.MainActivity;
import com.dts.casemanagerfrontendandroid.ui.mainactivity.MainActivityViewModel;

import java.util.Objects;

public class UpdateTaskClickHandler {

    private Task task;
    private Context context;
    private MainActivityViewModel viewModel;
    private long id;
    private static String TAG = "UpdateTaskClickHandler";

    public UpdateTaskClickHandler(Task task, Context context, MainActivityViewModel viewModel) {
        this.task = task;
        this.context = context;
        this.viewModel = viewModel;
    }

    public void onBackBtnClick(View view) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public void onSubmitBtnClick(View view) {

        Task updatedTask = new Task(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                task.getStatus(),
                task.getCreatedDate(),
                task.getDueDate());

        //0 is acceptable release year, stock and price in my API logic so have not checked int values here
        if(Objects.equals(updatedTask.getStatus(), "")) {
            Toast.makeText(context, "Task status cannot be empty", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(context, MainActivity.class);

            long taskId = task.getId();

            StatusDTO statusDTO = new StatusDTO(task.getStatus());

            viewModel.patchTaskStatus(taskId, statusDTO);

            context.startActivity(intent);
        }
    }

    public void onDeleteBtnClick(View view) {
        Intent intent = new Intent(context, MainActivity.class);
        viewModel.deleteTask(task.getId());
        context.startActivity(intent);
    }
}
