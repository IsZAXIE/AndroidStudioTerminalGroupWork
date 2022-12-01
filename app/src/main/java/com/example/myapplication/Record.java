package com.example.myapplication;

public class Record {
    public Record(){}
    public Record(String name,Double quality,Double kcal,String date){
        this.name = name;
        this.quality = quality;
        this.kcal = kcal;
        this.date = date;
    }
    private Integer id;
    private String name;
    private Double kcal;
    private Double quality;
    private String date;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Double getQuality() {
        return quality;
    }

    public void setQuality(Double quality) {
        this.quality = quality;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getKcal() {
        return kcal;
    }

    public void setKcal(Double kcal) {
        this.kcal = kcal;
    }
}
