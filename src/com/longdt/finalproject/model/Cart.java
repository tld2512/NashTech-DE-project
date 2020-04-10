package com.longdt.finalproject.model;

public class Cart {
    private int id = 0;
    private int bill_id;
    private int book_id;
    private int quantity = 0;

    public Cart() {
    }

    public Cart(int bill_id, int book_id, int quantity) {
        this.id = ++this.id;
        this.bill_id = bill_id;
        this.book_id = book_id;
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBill_id() {
        return bill_id;
    }

    public void setBill_id(int bill_id) {
        this.bill_id = bill_id;
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
