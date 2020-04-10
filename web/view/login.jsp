<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 4/9/2020
  Time: 6:33 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>

<%--<h3>Login Page</h3>--%>
<%--<p style="color: red;">${errorMessage}</p>--%>


<%--<form method="POST" action="${pageContext.request.contextPath}/login">--%>
<%--    <table border="0">--%>
<%--        <tr>--%>
<%--            <td>User Name</td>--%>
<%--            <td><input type="text" name="userName" value="${user.userName}"/></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Password</td>--%>
<%--            <td><input type="password" name="password" value="${user.password}"/></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td>Remember me</td>--%>
<%--            <td><input type="checkbox" name="rememberMe" value="Y"/></td>--%>
<%--        </tr>--%>
<%--        <tr>--%>
<%--            <td colspan="2">--%>
<%--                <input type="submit" value="Submit"/>--%>
<%--                <a href="${pageContext.request.contextPath}/">Cancel</a>--%>
<%--            </td>--%>
<%--        </tr>--%>
<%--    </table>--%>
<%--</form>--%>

<div class="container">
    <div class="row">
        <div class="col-md-5 mx-auto">
            <div id="first">
                <div class="myform form ">
                    <div class="logo mb-3">
                        <div class="col-md-12 text-center">
                            <h1>Login</h1>
                        </div>
                        <p style="color: red;">${errorMessage}</p>
                    </div>
                    <form method="POST" action="${pageContext.request.contextPath}/login" name="login">
                        <div class="form-group">
                            <label for="userName">User name</label>
                            <input type="text" name="userName" class="form-control" id="userName"
                                   aria-describedby="emailHelp" placeholder="Enter User name">
                        </div>
                        <div class="form-group">
                            <label for="password">Password</label>
                            <input type="password" name="password" id="password" class="form-control"
                                   aria-describedby="emailHelp" placeholder="Enter Password">
                        </div>
                        <div class="col-md-12 text-center ">
                            <button type="submit" class=" btn btn-block mybtn btn-primary tx-tfm">Login</button>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
