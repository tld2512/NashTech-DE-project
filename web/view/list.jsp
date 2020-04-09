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
</head>
<body>
<h1 onclick="">Book Store</h1>
<p>
    <a href="/bookList?action=create">Create new book</a>
</p>
<table border="1">
    <tr>
        <td>Name</td>
        <td>Image</td>
<%--        <td>Description</td>--%>
        <td>Price</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
    <c:forEach items='${requestScope["books"]}' var="book">
        <tr>
            <td style="width: 25%"><a href="/bookList?action=view&id=${book.getId()}">${book.getName()}</a></td>
            <td style="width: 40%"><img src="${book.getImgURL()}" alt="" style="height: 100px; width: auto"></td>
<%--            <td >${book.getDescription()}</td>--%>
            <td style="width: 15%">${book.getPrice()}</td>
            <td style="width: 10%"><a href="/bookList?action=update&id=${book.getId()}">Edit</a></td>
            <td style="width: 10%"><a href="/bookList?action=delete&id=${book.getId()}" onclick="confirm('Are you sure you want to delete this book?')">Delete</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
