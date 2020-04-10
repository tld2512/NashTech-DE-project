package com.longdt.finalproject.service;

import com.longdt.finalproject.model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookService implements IBookService {

    @Override
    public List<Book> listAllBook(Connection connection) throws SQLException {
        String sql = "select b.id, b.book_name, b.imgURL, b.book_description, b.price from book b ";

        PreparedStatement pstm = connection.prepareStatement(sql);

        ResultSet rs = pstm.executeQuery();
        List<Book> list = new ArrayList<>();
        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("book_name");
            String imgURL = rs.getString("imgURL");
            String description = rs.getString("book_description");
            float price = rs.getFloat("price");
            Book book = new Book();
            book.setId(Integer.parseInt(id));
            book.setName(name);
            book.setImgURL(imgURL);
            book.setDescription(description);
            book.setPrice(price);
            list.add(book);
        }
        rs.close();
        pstm.close();
        return list;
    }

    @Override
    public void saveBook(Book book, Connection connection) throws SQLException {
        String sql = "insert into book(book_name, imgURL, book_description, price) values (?, ?, ?, ?)";
        PreparedStatement ps = connection.prepareStatement(sql);
//        ps.setInt(1, book.getId());
        ps.setString(1, book.getName());
        ps.setString(2, book.getImgURL());
        ps.setString(3, book.getDescription());
        ps.setFloat(4, book.getPrice());
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void updateBook(int id, Book book, Connection connection) throws SQLException {
        String sql = "update book set book_name = ?, imgURL = ?, book_description = ?, price = ? where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, book.getName());
        ps.setString(2, book.getImgURL());
        ps.setString(3, book.getDescription());
        ps.setString(4, String.valueOf(book.getPrice()));
        ps.setString(5, String.valueOf(id));
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public void deleteBook(int id, Connection connection) throws SQLException {
        String sql = "delete from book where id = ?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, String.valueOf(id));
        ps.executeUpdate();
        ps.close();
    }

    @Override
    public Book findBook(String id, Connection connection) throws SQLException {
        String sql = "Select b.id, b.book_name, b.imgURL, b.book_description, b.price from book b where b.id=?";
        PreparedStatement ps = connection.prepareStatement(sql);
        ps.setString(1, id);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String name = rs.getString("book_name");
            String imgURL = rs.getString("imgURL");
            String description = rs.getString("book_description");
            float price = rs.getFloat("price");
            return new Book(name, imgURL, description, price);
        }
        rs.close();
        ps.close();
        return null;
    }

    @Override
    public List<Book> findByName(String keyword, Connection connection) throws SQLException {
        String sql = "Select * from book where book_name like '%" + keyword + "%'";
        PreparedStatement ps = connection.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();

        List<Book> resultList = new ArrayList<>();
        while (rs.next()) {
            String id = rs.getString("id");
            String name = rs.getString("book_name");
            String imgURL = rs.getString("imgURL");
            String description = rs.getString("book_description");
            float price = rs.getFloat("price");
            Book book = new Book();
            book.setId(Integer.parseInt(id));
            book.setName(name);
            book.setImgURL(imgURL);
            book.setDescription(description);
            book.setPrice(price);
            resultList.add(book);
        }
        rs.close();
        ps.close();
        return resultList;
    }

}
