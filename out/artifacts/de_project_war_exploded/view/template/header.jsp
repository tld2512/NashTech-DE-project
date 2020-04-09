<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 4/9/2020
  Time: 6:45 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div style="background: #E0E0E0; height: 70px; padding: 5px;">
    <div style="float: left">
        <h1><a href="/bookList">Book Store</a></h1>
    </div>

    <div style="float: right; padding: 10px; text-align: right;">
        <!-- User store in session with attribute: loggedInUser -->
        <b><a href="/profile">${loggedInUser.userName}</a></b>
        <br/>
        <a href="/LogoutServlet">Log out</a>
        <br>
        <br>
        <br>
    </div>

</div>
