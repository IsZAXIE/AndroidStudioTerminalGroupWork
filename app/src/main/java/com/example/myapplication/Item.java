package com.example.myapplication;

public class Item {

    public Item() {

    }

    public Item(Integer id, String name, Double kcal) {
        this.id = id;
        this.name = name;
        this.kcal = kcal;
    }


    private Integer id;
    private String name;
    private Double kcal;
/*

    private double carb = 0.0; // 碳水化合物
    private double protein = 0.0; // 蛋白质
    private double fat = 0.0; // 脂肪
    private double df = 0.0; // 膳食纤维
*/

    private final double carb = 0.0; // 碳水化合物
    private final double protein = 0.0; // 蛋白质
    private final double fat = 0.0; // 脂肪
    private final double df = 0.0; // 膳食纤维
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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



/*
    public void setCarb(double carb){
        this.carb = carb;
    }
    public double getCarb(){
        return carb;
    }
    public void setProtein(double protein){
        this.protein = protein;
    }
    public double getProtein(){
        return protein;
    }
    public void setFat(double Fat){
        this.fat = fat;
    }
    public double getFat(){
        return fat;
    }
    public void setDF(double df){
        this.df = df;
    }
    public double getDF(){
        return df;
    }
    */
}
