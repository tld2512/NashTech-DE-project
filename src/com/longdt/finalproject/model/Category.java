package com.longdt.finalproject.model;

public class Category {
    private int id = 0;
    private String name;

    public Category() {
    }

    public Category(String name) {
        this.id = ++this.id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
