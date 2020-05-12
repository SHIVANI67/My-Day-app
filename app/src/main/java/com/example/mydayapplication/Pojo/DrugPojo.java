package com.example.mydayapplication.Pojo;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class DrugPojo implements Serializable {
    @SerializedName("dosage")
    private Dosage dosage;

    @SerializedName("brandNm")
    private String brandNm;

    @SerializedName("qty")
    private String qty;

    @SerializedName("genericNm")
    private String genericNm;

    public Dosage getDosage() {
        return dosage;
    }

    public void setDosage(Dosage dosage) {
        this.dosage = dosage;
    }

    public String getBrandNm() {
        return brandNm;
    }

    public void setBrandNm(String brandNm) {
        this.brandNm = brandNm;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getGenericNm() {
        return genericNm;
    }

    public void setGenericNm(String genericNm) {
        this.genericNm = genericNm;
    }

}
