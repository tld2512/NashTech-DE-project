<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 4/9/2020
  Time: 7:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="template/header.jsp"></jsp:include>
<div>
    <table>
        <tr>
            <td>Username:</td>
            <td>${user.getUserName()}</td>
        </tr>
        <tr>
            <td></td>
            <td><a href="/profile?action=changePassword&username=${user.getUserName()}">Change Password</a></td>
        </tr>
    </table>
</div>
</body>
</html>
