package com.longdt.finalproject.service;

import com.longdt.finalproject.model.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface IBookService {
    List<Book> listAllBook(Connection connection) throws SQLException;

    void saveBook(Book book, Connection connection) throws SQLException;

    void updateBook(int id, Book book, Connection connection) throws SQLException;

    void deleteBook(int id, Connection connection) throws SQLException;

    Book findBook(String id, Connection connection) throws SQLException;
}
