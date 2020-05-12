package com.example.mydayapplication.Pojo;

import com.google.gson.annotations.SerializedName;

public class Dosage {

    @SerializedName("dose")
    private String dose;

    @SerializedName("unit")
    private String unit;

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

}
