package com.example.myapplication;

public class ReturnItem {

    // ReturnItem 对象在从数据库返回查询结果，以便分析时 使用。

    public ReturnItem() {

    }

    public ReturnItem(double quantity, double carb, double protein, double fat, double df, double kcal) {
        this.quantity = quantity;
        this.carb = carb;
        this.protein = protein;
        this.fat = fat;
        this.df = df;
        this.kcal = kcal;
    }

    public ReturnItem(String name, double quantity, double carb, double protein, double fat, double df, double kcal) {
        this.name = name;
        this.quantity=quantity;
        this.carb = carb;
        this.protein = protein;
        this.fat = fat;
        this.df = df;
        this.kcal = kcal;
    }

    private String name="";
    private double quantity=0.0;
    private double carb = 0.0; // 碳水化合物
    private double protein = 0.0; // 蛋白质
    private double fat = 0.0; // 脂肪
    private double df = 0.0; // 膳食纤维
    private Double kcal=0.0;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quality) {
        this.quantity = quality;
    }

    public Double getKcal() {
        return kcal;
    }

    public void setKcal(Double kcal) {
        this.kcal = kcal;
    }


    public void setCarb(double carb) {
        this.carb = carb;
    }

    public double getCarb() {
        return carb;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getProtein() {
        return protein;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getFat() {
        return fat;
    }

    public void setDF(double df) {
        this.df = df;
    }

    public double getDF() {
        return df;
    }
}
