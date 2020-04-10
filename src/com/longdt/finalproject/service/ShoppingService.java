package com.longdt.finalproject.service;

import com.longdt.finalproject.model.Book;
import com.longdt.finalproject.model.Cart;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShoppingService {
//    private BookService bookService = new BookService();

    public void addBookToCart(int book_id, int cart_id, Connection connection) throws SQLException {
        Cart cart = findCart(cart_id, connection);
        if (cart != null) {
            cart.setBook_id(book_id);
        }
    }

    private Cart findCart(int id, Connection connection) throws SQLException {
        String sql = "SELECT * FROM cart where id =?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, String.valueOf(id));
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            Cart cart = new Cart();
            cart.setId(Integer.parseInt(rs.getString("id")));
            cart.setBill_id(Integer.parseInt(rs.getString("bill_id")));
            cart.setBook_id(Integer.parseInt(rs.getString("book_id")));
            cart.setQuantity(Integer.parseInt(rs.getString("quantity")));
            return cart;
        }
        rs.close();
        ps.close();
        return null;
    }
}
