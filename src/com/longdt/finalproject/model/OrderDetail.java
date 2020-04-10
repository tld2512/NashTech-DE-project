package com.longdt.finalproject.model;

public class OrderDetail {
    private int id = 0;
    private int order_id;
    private int book_id;
    private int quantity;

    public OrderDetail() {
    }

    public OrderDetail(int order_id, int book_id, int quantity) {
        this.id = ++this.id;
        this.order_id = order_id;
        this.book_id = book_id;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
