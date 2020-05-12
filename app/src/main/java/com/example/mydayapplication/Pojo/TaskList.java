package com.example.mydayapplication.Pojo;

import com.google.gson.annotations.SerializedName;

public class TaskList {
    @SerializedName("tasks")
    private Tasks[] tasks;

    public Tasks[] getTasks() {
        return tasks;
    }

    public void setTasks(Tasks[] tasks) {
        this.tasks = tasks;
    }

}
