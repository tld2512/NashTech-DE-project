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
    <style>
        .btn {
            height: auto;
            font-size: 14px;
        }

        .btn-edit {
            width: 52px;
        }

        .btn-delete {
            width: 70px;
        }

        .btn-add {
            width: 100px;
        }
    </style>
</head>
<body>
<jsp:include page="template/header.jsp"></jsp:include>

<div class="d-flex" id="wrapper">
    <div class="bg-light border-right" id="sidebar-wrapper">
        <div class="list-group list-group-flush">
            <a href="#" class="list-group-item list-group-item-action bg-light">Home</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Categories</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Shopping Cart</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Profile</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">Overview</a>
            <a href="#" class="list-group-item list-group-item-action bg-light">About</a>
        </div>
    </div>
    <div class="page-content-wrapper">
        <div class="container" style="margin-top: 10px">
            <div class="row">
                <div class="col-4">
                    <p>
                    <h4><a href="${pageContext.request.contextPath}/bookList?action=create">Create new book</a></h4>
                    </p>
                </div>
                <div class="col-4"></div>
                <div class="col-4">
                    <form class="form-inline md-form mr-auto mb-4" method="post"
                          action="${pageContext.request.contextPath}/bookList?action=search">
                        <input class="form-control mr-sm-2" type="text" name="keyWord" id="keyWord"
                               placeholder="Search">
                        <button class="btn btn-outline-warning btn-rounded btn-sm my-0" type="submit">Search</button>
                    </form>

                </div>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <c:forEach items='${requestScope["books"]}' var="book">
                    <div class="col-lg-4 col-md-6 col-sm-10" style="padding: 5px">
                        <div class="card" style="width: 95%;height: 320px; padding: 5px">
                            <img src="${book.getImgURL()}" class="card-img-top" alt="..."
                                 style="height: 43%; width: 43%; margin-left: 12%">
                            <div class="card-body">
                                <table>
                                    <tr style="height: 50%">
                                        <h5 class="card-title" style="color: blue; text-shadow: magenta 2px 2px 6px">
                                            <a href="${pageContext.request.contextPath}/bookList?action=view&id=${book.getId()}">${book.getName()}</a>
                                        </h5>
                                    </tr>
                                    <p class="card-text"><b>Price: ${book.getPrice()}</b></p>
                                    </tr>

                                    <tr>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/bookList?action=update&id=${book.getId()}"
                                               class="btn btn-primary btn-edit"
                                               style=" bottom: 2%">Edit</a>

                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/bookList?action=delete&id=${book.getId()}"
                                               onclick="confirm('Are you sure you want to delete this book?')"
                                               class="btn btn-danger btn-delete"
                                               style="bottom: 2%;">Delete</a>
                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/bookList?activity=addToCart&id=${book.getId()}"
                                               style=" bottom: 2%" class="btn btn-light btn-add">Add to cart</a>
                                        </td>
                                    </tr>
                                </table>
                            </div>

                        </div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
</div>
</body>
</html>
