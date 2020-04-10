package com.longdt.finalproject.controller;

import com.longdt.finalproject.log.MyLogger;
import com.longdt.finalproject.model.Book;
import com.longdt.finalproject.service.BookService;
import com.longdt.finalproject.service.ConnectionService;
import com.longdt.finalproject.service.IBookService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

@WebServlet(name = "BookServlet", urlPatterns = "/bookList")
public class BookServlet extends HttpServlet {
    private static final String CREATE = "create";
    private static final String UPDATE = "update";
    private static final String DELETE = "delete";
    private static final String VIEW = "view";
    private static final String SEARCH = "search";
    private static final Logger logger = MyLogger.getLogger();
    private IBookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case CREATE: {
                getCreateForm(req, resp);
                break;
            }
            case UPDATE: {
                try {
                    getUpdateForm(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                    logger.severe(e.getMessage());
                }
                break;
            }
            case VIEW: {
                try {
                    getDetailBook(req, resp);
                } catch (SQLException e) {
                    e.printStackTrace();
                    logger.severe(e.getMessage());
                }
                break;
            }
            case DELETE: {
                try {
                    deleteBook(req, resp);
                } catch (SQLException | IOException e) {
                    e.printStackTrace();
                    logger.severe(e.getMessage());
                }
                break;
            }
            case SEARCH: {
                searchByName(req, resp);
                break;
            }
            default: {
                listBooks(req, resp);
                logger.info("Book list page is accessed");
                break;
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case CREATE: {
                try {
                    createNewBook(req, resp);
                } catch (ServletException | IOException e) {
                    e.printStackTrace();
                    logger.severe(e.getMessage());
                }
                break;
            }
            case UPDATE: {
                try {
                    updateBook(req, resp);
                } catch (SQLException | ServletException | IOException e) {
                    e.printStackTrace();
                    logger.severe(e.getMessage());
                }
                break;
            }
            case SEARCH: {
                searchByName(req, resp);
                break;
            }
            default: {
                break;
            }
        }
    }

    private void listBooks(HttpServletRequest req, HttpServletResponse resp) {
        Connection conn = ConnectionService.getStoredConnection(req);
        try {
            List<Book> bookList = this.bookService.listAllBook(conn);
            req.setAttribute("books", bookList);
            RequestDispatcher dispatcher = req.getRequestDispatcher("view/list.jsp");
            dispatcher.forward(req, resp);
        } catch (SQLException | ServletException | IOException e) {
            e.printStackTrace();
            logger.severe(e.getMessage());
        }
    }

    private void getCreateForm(HttpServletRequest req, HttpServletResponse resp) {
        RequestDispatcher dispatcher = req.getRequestDispatcher("view/create.jsp");
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            logger.severe(e.getMessage());
        }
    }

    private void createNewBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection conn = ConnectionService.getStoredConnection(req);
        String name = req.getParameter("name");
        String imgURL = req.getParameter("imgURL");
        String description = req.getParameter("description");
        float price = Float.parseFloat(req.getParameter("price"));
        Book newBook = new Book(name, imgURL, description, price);

        if (name.equals("") || price == 0) {
            req.setAttribute("message", "Name and price is required");
            logger.warning("New book failed to create, some fields is required");
        } else {
            try {
                this.bookService.saveBook(newBook, conn);
                req.setAttribute("message", "New book was created successfully");
                logger.info("New book with id: " + newBook.getId() + " was created successfully");
            } catch (SQLException e) {
                e.printStackTrace();
                logger.severe(e.getMessage());
            }
        }

        RequestDispatcher dispatcher = req.getRequestDispatcher("view/create.jsp");
        dispatcher.forward(req, resp);
    }

    private void getUpdateForm(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        Connection conn = ConnectionService.getStoredConnection(req);
        String id = req.getParameter("id");
        Book book = this.bookService.findBook(id, conn);
        RequestDispatcher dispatcher;
        if (book == null) {
            dispatcher = req.getRequestDispatcher("error-404.jsp");
        } else {
            req.setAttribute("book", book);
            dispatcher = req.getRequestDispatcher("view/update.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            logger.severe(e.getMessage());
        }
    }

    private void updateBook(HttpServletRequest req, HttpServletResponse resp) throws SQLException, ServletException, IOException {
        Connection conn = ConnectionService.getStoredConnection(req);
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String imgURL = req.getParameter("imgURL");
        String description = req.getParameter("description");
        Float price = Float.parseFloat(req.getParameter("price"));
        Book book = this.bookService.findBook(id, conn);
        RequestDispatcher dispatcher;
        if (book == null) {
            dispatcher = req.getRequestDispatcher("error-404.jsp");
            logger.warning("Update failed");
        } else {
            book.setName(name);
            book.setImgURL(imgURL);
            book.setDescription(description);
            book.setPrice(price);
            this.bookService.updateBook(Integer.parseInt(id), book, conn);
            req.setAttribute("book", book);
            req.setAttribute("message", "Book information was updated");
            dispatcher = req.getRequestDispatcher("view/update.jsp");
            logger.info("Book with id: " + book.getId() + " updated successfully");
        }
        dispatcher.forward(req, resp);
    }

    private void deleteBook(HttpServletRequest req, HttpServletResponse resp) throws SQLException, IOException {
        Connection conn = ConnectionService.getStoredConnection(req);
        int id = Integer.parseInt(req.getParameter("id"));
        this.bookService.deleteBook(id, conn);
        logger.info("Book with id: " + id + " is deleted");
        resp.sendRedirect("/bookList");
    }

    private void getDetailBook(HttpServletRequest req, HttpServletResponse resp) throws SQLException {
        Connection conn = ConnectionService.getStoredConnection(req);
        String id = req.getParameter("id");
        Book book = this.bookService.findBook(id, conn);
        RequestDispatcher dispatcher;
        if (book == null) {
            dispatcher = req.getRequestDispatcher("error-404.jsp");
        } else {
            req.setAttribute("book", book);
            dispatcher = req.getRequestDispatcher("/view/detail.jsp");
            logger.info("Book with id: " + id + " was viewed");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
            logger.severe(e.getMessage());
        }
    }

    private void searchByName(HttpServletRequest req, HttpServletResponse resp) {
        Connection connection = ConnectionService.getStoredConnection(req);
        String keyWord = req.getParameter("keyWord");
        if (keyWord == null) {
            keyWord = "";
        }
        try {
            List<Book> bookList = this.bookService.findByName(keyWord, connection);
            req.setAttribute("books", bookList);
            req.setAttribute("keyword", keyWord);
            RequestDispatcher dispatcher = req.getRequestDispatcher("view/list.jsp");
            dispatcher.forward(req, resp);
        } catch (SQLException | ServletException | IOException e) {
            e.printStackTrace();
            logger.severe(e.getMessage());
        }
    }
}
