package com.dts.casemanagerfrontendandroid.ui.mainactivity;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.dts.casemanagerfrontendandroid.model.Task;
import com.dts.casemanagerfrontendandroid.model.TaskDTO;
import com.dts.casemanagerfrontendandroid.model.TaskRepository;

import java.util.List;

public class MainActivityViewModel extends AndroidViewModel {
    TaskRepository taskRepository;
    String TAG = "MainActivityViewModel";

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        this.taskRepository = new TaskRepository(application);
    }

    public LiveData<List<Task>> getAllTasks() {
        Log.i(TAG, "getAllTasks executed");
        return taskRepository.getMutableLiveData();
    }

    public void postTask(TaskDTO taskDTO) {
        Log.i(TAG, "postTask executed");
        taskRepository.postTask(taskDTO);
    }

    public void patchTaskStatus(long id, String status) {
        Log.i(TAG, "patchTaskStatus executed");
        taskRepository.patchTaskStatus(id, status);
    }

    public void deleteTask(long id) {
        Log.i(TAG, "deleteTask executed");
        taskRepository.deleteStockItem(id);
    }
}
