package com.longdt.finalproject.model;

public class Book {
    private Integer id = 0;
    private String name;
    private String imgURL;
    private String description;
    private Float price;

    public Book() {
    }

    public Book(String name, String imgURL, String description, Float price) {
        this.id = ++this.id;
        this.name = name;
        this.imgURL = imgURL;
        this.description = description;
        this.price = price;
    }

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

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}

