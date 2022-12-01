package com.example.myapplication;

public class User {
    private int id;
    private String account ;
    private String psw;
    private Double height;
    private Double weight;

    public User() {
    }

    public User(String account, String psw, Double height, Double weight) {
        this.account = account;
        this.psw = psw;
        this.height = height;
        this.weight = weight;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public void print(){
        System.out.println("account : "+ account);
        System.out.print("password : "+ psw);
        System.out.println("Height : "+height);
        System.out.println("Weight : "+weight);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }



}
