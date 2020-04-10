package com.longdt.finalproject.model;

public class Order {
    private int id = 0;
    private String user_name;
    private float total_money;

    public Order() {
    }

    public Order(String user_name, float total_money) {
        this.id = ++this.id;
        this.user_name = user_name;
        this.total_money = total_money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public float getTotal_money() {
        return total_money;
    }

    public void setTotal_money(float total_money) {
        this.total_money = total_money;
    }
}
