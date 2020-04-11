<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 4/10/2020
  Time: 11:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Sign-up</title>
    <link href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
    <script src="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</head>
<body>
<div class="container">
    <div class="row">
        <div class="col-md-5 mx-auto">
            <div id="first">
                <div class="myform form ">
                    <div class="logo mb-3">
                        <div class="col-md-12 text-center">
                            <h1>Create new Account</h1>
                        </div>
                        <p style="color: red;">${errorSignUpMessage}</p>
                    </div>
                    <form method="POST" name="sign-up">
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
                        <div class="form-group">
                            <label for="password">Re-enter Password</label>
                            <input type="password" name="password2" id="password2" class="form-control"
                                   aria-describedby="emailHelp" placeholder="Re-enter Password">
                        </div>
                        <div class="col-md-12 text-center ">
                            <button type="submit" class=" btn btn-block mybtn btn-primary tx-tfm">Create account</button>
                        </div>
                        <div class="form-group">
                            <p class="text-center">Already have an account? <a href="/login" id="signup">Login</a></p>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
