package com.dts.casemanagerfrontendandroid.ui.createtask;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.dts.casemanagerfrontendandroid.model.Task;
import com.dts.casemanagerfrontendandroid.model.TaskDTO;
import com.dts.casemanagerfrontendandroid.ui.mainactivity.MainActivity;
import com.dts.casemanagerfrontendandroid.ui.mainactivity.MainActivityViewModel;

public class CreateTaskClickHandler {
    Task task;
    Context context;
    MainActivityViewModel mainActivityViewModel;
    String TAG = "CreateTaskClickHandler";

    public CreateTaskClickHandler(Task task, Context context, MainActivityViewModel mainActivityViewModel) {
        this.task = task;
        this.context = context;
        this.mainActivityViewModel = mainActivityViewModel;
    }

    public void onCreateBtnClick(View view) {
        if(
                task.getTitle() == null || task.getTitle().isBlank() ||
                task.getStatus() == null || task.getStatus().isEmpty() ||
                task.getCreatedDate() == null || task.getCreatedDate().isEmpty() ||
                task.getDueDate() == null || task.getDueDate().isEmpty()
        ) {
            Toast.makeText(context,"Mandatory task fields cannot be empty",Toast.LENGTH_SHORT).show();
            Log.i(TAG, "onSubmitBtnClick: invalid task submitted");
        } else {
            Intent intent = new Intent(context, MainActivity.class);
            TaskDTO taskDTO = new TaskDTO(
                    task.getTitle(),
                    task.getDescription(),
                    task.getStatus(),
                    task.getCreatedDate(),
                    task.getDueDate());
            mainActivityViewModel.postTask(taskDTO);

            //TODO add something here to make sure that post request has finished before reloading the MainActivity
            context.startActivity(intent);
            Log.i(TAG, "onSubmitBtnClick: valid task submitted");
        }
    }

    public void onBackBtnClick(View view) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

}


