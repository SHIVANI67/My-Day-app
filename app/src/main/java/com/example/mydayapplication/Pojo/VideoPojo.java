package com.example.mydayapplication.Pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class VideoPojo implements Serializable {
    @SerializedName("thumbnail")
    private String thumbnail;

    @SerializedName("title")
    private String title;

    @SerializedName("hlsUrl")
    private String hlsUrl;

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getHlsUrl() {
        return hlsUrl;
    }

    public void setHlsUrl(String hlsUrl) {
        this.hlsUrl = hlsUrl;
    }

}
