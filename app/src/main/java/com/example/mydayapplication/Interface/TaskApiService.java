package com.example.mydayapplication.Interface;

import com.example.mydayapplication.Pojo.TaskList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TaskApiService {
    @GET(".")
    Call<TaskList> getTaskLists();
}
