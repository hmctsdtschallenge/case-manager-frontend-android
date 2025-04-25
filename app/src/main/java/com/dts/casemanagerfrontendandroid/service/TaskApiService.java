package com.dts.casemanagerfrontendandroid.service;

import com.dts.casemanagerfrontendandroid.model.StatusDTO;
import com.dts.casemanagerfrontendandroid.model.Task;
import com.dts.casemanagerfrontendandroid.model.TaskDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface TaskApiService {

    @POST("tasks")
    public Call<Task> postTask(@Body TaskDTO taskDTO);

    @GET("tasks/{id}")
    public Call<Task> getTaskById(@Path("id") long id);

    @GET("tasks")
    public Call<List<Task>> getAllTasks();

    @PATCH("tasks/{id}")
    public Call<Task> patchTaskStatus(@Path("id") long id, @Body StatusDTO statusDTO);

    @DELETE("tasks/{id}")
    public Call<Void> deleteTask(@Path("id") long id);

}
