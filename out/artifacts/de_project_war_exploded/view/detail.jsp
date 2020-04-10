<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 4/9/2020
  Time: 3:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Book Detail</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="template/header.jsp"></jsp:include>

<h1>Book details</h1>
<p>
    <a href="/bookList">Back to book list</a>
</p>
<table>
    <tr>
        <td>Name:</td>
        <td>${requestScope["book"].getName()}</td>
    </tr>
    <tr>
        <td></td>
        <td><img src="${requestScope["book"].getImgURL()}" alt="" style="height: 250px; width: auto"></td>
    </tr>
    <tr>
        <td>Description:</td>
        <td>${requestScope["book"].getDescription()}</td>
    </tr>
    <tr>
        <td>Price:</td>
        <td>${requestScope["book"].getPrice()}</td>
    </tr>
</table>
</body>
</html>
