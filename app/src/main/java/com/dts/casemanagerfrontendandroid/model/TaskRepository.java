package com.dts.casemanagerfrontendandroid.model;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.dts.casemanagerfrontendandroid.service.RetrofitInstance;
import com.dts.casemanagerfrontendandroid.service.TaskApiService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TaskRepository {
    private MutableLiveData<List<Task>> mutableLiveData = new MutableLiveData<>();
    private Application application = new Application();
    public TaskRepository(Application application) {
        this.application = application;
    }
    private final String TAG = "TaskRepository";

    public void postTask(TaskDTO taskDTO) {
        TaskApiService taskApiService = RetrofitInstance.getService();

        Call<Task> call = taskApiService.postTask(taskDTO);

        call.enqueue(new Callback<Task>() {

            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if (response.code() == 201) {
                    Toast.makeText(application.getApplicationContext(),
                            "Task created successfully",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(application.getApplicationContext(),
                            "Task not created",
                            Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "POST request (postTask) received response other than 201: HTTP code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                Toast.makeText(
                        application.getApplicationContext(),
                        "Task not created",
                        Toast.LENGTH_SHORT).show();
                Log.i(TAG, "POST request (postTask) failed: " + t.getMessage());
            }
        });
    }

    //currently no need for getTaskById - individual task data will be accessed through selection from getAllTasks List

    public MutableLiveData<List<Task>> getMutableLiveData() {
        TaskApiService taskApiService = RetrofitInstance.getService();

        Call<List<Task>> call = taskApiService.getAllTasks();

        call.enqueue(new Callback<>() {
            @Override
            public void onResponse(Call<List<Task>> call, Response<List<Task>> response) {
                if (response.code() == 200) {
                    List<Task> tasks = response.body();
                    mutableLiveData.setValue(tasks);
                    Log.i(TAG, "GET request (getAllTasks) success. HTTP code: " + response.code());
                } else {
                    Toast.makeText(application.getApplicationContext(),
                            "Tasks not retrieved",
                            Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "GET request (getAllTasks) received response other than 200: HTTP code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Task>> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(),
                        "Tasks not retrieved",
                        Toast.LENGTH_SHORT).show();
                Log.i(TAG, "GET request (getAllTasks) failed:" + t.getMessage());
            }
        });

        return mutableLiveData;
    }

    public void patchTaskStatus(long id, String status) {
        TaskApiService taskApiService = RetrofitInstance.getService();

        Call<Task> call = taskApiService.patchTaskStatus(id, status);

        call.enqueue(new Callback<Task>() {
            @Override
            public void onResponse(Call<Task> call, Response<Task> response) {
                if (response.code() == 200) {
                    Toast.makeText(application.getApplicationContext(),
                            "Task status updated successfully",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(application.getApplicationContext(),
                            "Task status not updated",
                            Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "PATCH request (patchTaskStatus) received response other than 200: HTTP code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Task> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), "Task not updated",
                        Toast.LENGTH_SHORT).show();
                Log.i(TAG, "PATCH request (patchTaskStatus) failed: " + t.getMessage());
            }
        });
    }

    public void deleteStockItem(long id) {
        TaskApiService taskApiService = RetrofitInstance.getService();

        Call<Void> call = taskApiService.deleteTask(id);

        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.code() == 204) {
                    Toast.makeText(application.getApplicationContext(),
                            "Task deleted successfully",
                            Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(application.getApplicationContext(),
                            "Task not deleted",
                            Toast.LENGTH_SHORT).show();
                    Log.i(TAG, "patchTaskStatus received response other than 204: HTTP code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), "Task not deleted",
                        Toast.LENGTH_SHORT).show();
                Log.i(TAG, "DELETE request (deleteTask) failed: " + t.getMessage());
            }
        });
    }
}
