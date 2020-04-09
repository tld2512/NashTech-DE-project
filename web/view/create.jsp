<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 4/9/2020
  Time: 4:47 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Create New Book</title>
</head>
<body>
<h1>Create New Book</h1>
<p>
    <c:if test="${requestScope['message']!=null}">
        <span class="message">${requestScope['message']}</span>
    </c:if>
</p>
<p>
    <a href="/bookList">Back to book list</a>
</p>
<form method="post">
    <fieldset>
        <legend>Book Detail</legend>
        <table>
<%--            <tr>--%>
<%--                <td>ID:</td>--%>
<%--                <td><input type="number" name="id"></td>--%>
<%--            </tr>--%>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name"></td>
            </tr>
            <tr>
                <td>Image URL:</td>
                <td><input type="text" name="imgURL"></td>
            </tr>
            <tr>
                <td>Description:</td>
                <td><textarea name="description"></textarea></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type="number" name="price"></td>
            </tr>
        </table>
        <input type="submit" value="Confirm">
    </fieldset>
</form>
</body>
</html>
