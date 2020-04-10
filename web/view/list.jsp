<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 4/8/2020
  Time: 11:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Book Store</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <link href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"
          integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN"
          crossorigin="anonymous">
    <style>
        .btn {
            width: 75px;
            height: auto;
            font-size: 14px;
        }
    </style>
</head>
<body>
<jsp:include page="template/header.jsp"></jsp:include>

<div class="container" style="margin-top: 10px">
    <div class="row">
        <div class="col-4">
            <p>
            <h4><a href="/bookList?action=create">Create new book</a></h4>
            </p>
        </div>
        <div class="col-4"></div>
        <div class="col-4">
            <form class="form-inline md-form mr-auto mb-4" method="post" action="/bookList?action=search">
                <input class="form-control mr-sm-2" type="text" name="keyWord" id="keyWord" placeholder="Search">
                <button class="btn btn-outline-warning btn-rounded btn-sm my-0" type="submit">Search</button>
            </form>

        </div>
    </div>
</div>

<div class="container">
    <div class="row">
        <c:forEach items='${requestScope["books"]}' var="book">
            <div class="col-lg-4 col-md-6 col-sm-10" style="padding: 5px">
                <div class="card" style="width: 95%;height: 280px">
                    <img src="${book.getImgURL()}" class="card-img-top" alt="..."
                         style="height: 50%; width: 45%; margin-left: 12%">
                    <div class="card-body">
                        <table>
                            <tr>
                                <h5 class="card-title" style="color: blue; text-shadow: magenta 2px 2px 6px">
                                    <a href="/bookList?action=view&id=${book.getId()}">${book.getName()}</a>
                                </h5>
                            </tr>
                            <p class="card-text"><b>Price: ${book.getPrice()}</b></p>
                            </tr>

                            <tr>
<%--                                <td><a href="/bookList?action=view&id=${book.getId()}"--%>
<%--                                       style=" bottom: 2%"--%>
<%--                                       class="btn btn-info">View</a>--%>
<%--                                </td>--%>
                                <td><a href="/bookList?action=update&id=${book.getId()}" class="btn btn-primary"
                                       style=" bottom: 2%">Edit</a>
                                </td>
                                <td><a href="/bookList?action=delete&id=${book.getId()}"
                                       onclick="confirm('Are you sure you want to delete this book?')"
                                       class="btn btn-danger"
                                       style="bottom: 2%;">Delete</a>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
