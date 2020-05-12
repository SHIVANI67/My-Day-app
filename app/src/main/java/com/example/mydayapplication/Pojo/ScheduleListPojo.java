package com.example.mydayapplication.Pojo;

import com.google.gson.annotations.SerializedName;

public class ScheduleListPojo {
    @SerializedName("session")
    private String session;
    @SerializedName("foodContext")
    private String foodContext;

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }

    public String getFoodContext() {
        return foodContext;
    }

    public void setFoodContext(String foodContext) {
        this.foodContext = foodContext;
    }

}
