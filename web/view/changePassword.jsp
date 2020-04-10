<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 4/9/2020
  Time: 8:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<jsp:include page="template/header.jsp"></jsp:include>
<p>
    <c:if test="${requestScope['message']!=null}">
        <span class="message">${requestScope['message']}</span>
    </c:if>
</p>
<a href="/profile">Go back to profile</a>
<form method="post">
    <table>
        <tr>
            <td>Old password:</td>
            <td><input type="password" name="oldPw"></td>
        </tr>
        <tr>
            <td>New password:</td>
            <td><input type="password" name="newPw"></td>
        </tr>
        <tr>
            <td>Re-enter new password</td>
            <td><input type="password" name="newPw2"></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Confirm"></td>
        </tr>
    </table>
</form>
</body>
</html>
