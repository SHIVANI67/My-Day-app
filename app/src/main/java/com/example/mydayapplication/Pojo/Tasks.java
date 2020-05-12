package com.example.mydayapplication.Pojo;

import com.google.gson.annotations.SerializedName;

public class Tasks {
    @SerializedName("duration")
    private int duration;

    @SerializedName("taskCd")
    private String taskCd;

    @SerializedName("type")
    private String type;

    @SerializedName("frequency")
    private int frequency;

    @SerializedName("drug")
    private DrugPojo drugPojo;

    @SerializedName("video")
    private VideoPojo videoPojo;

    @SerializedName("scheduleList")
    private ScheduleListPojo[] scheduleListPojo;

    private boolean isComplete;

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public ScheduleListPojo[] getScheduleList() {
        return scheduleListPojo;
    }

    public void setScheduleList(ScheduleListPojo[] scheduleListPojo) {
        this.scheduleListPojo = scheduleListPojo;
    }

    public String getTaskCd() {
        return taskCd;
    }

    public void setTaskCd(String taskCd) {
        this.taskCd = taskCd;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
        this.frequency = frequency;
    }

    public DrugPojo getDrug() {
        return drugPojo;
    }

    public void setDrug(DrugPojo drugPojo) {
        this.drugPojo = drugPojo;
    }

    public VideoPojo getVideoPojo() {
        return videoPojo;
    }

    public void setVideoPojo(VideoPojo videoPojo) {
        this.videoPojo = videoPojo;
    }
}
