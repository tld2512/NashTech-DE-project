package com.longdt.finalproject.service;

import com.longdt.finalproject.model.Book;

import java.sql.SQLException;
import java.util.List;

public interface IBookService {
    List<Book> listAllBook() throws SQLException;

    void saveBook(Book book) throws SQLException;

    void updateBook(int id, Book book) throws SQLException;

    void deleteBook(int id) throws SQLException;

    Book findBook(String id) throws SQLException;
}
