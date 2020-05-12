package com.example.mydayapplication.Interface;

import com.example.mydayapplication.Pojo.TaskList;

import retrofit2.Call;
import retrofit2.http.GET;

// interface to call api
public interface TaskApiService {
    @GET(".")
    Call<TaskList> getTaskLists();
}
