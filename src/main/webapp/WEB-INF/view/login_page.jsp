<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
</head>
<body>

<%@include file="includes/common/navbar.jsp" %>

<div class="header">
    <p>Login</p>
</div>

<div class="main-container-home">
    <div>
        <h2 class="train-search-title">Login</h2>
    </div>
    <div class="login-form-container">
        <form method="post" action="/login">
            <div class="login-form-input-groups-container">
                <div class="login-form-input-groups-box">
                    <div class="login-form-input-group">
                        <label>Email</label>
                        <input name="username" type="text" required>
                    </div>
                    <div class="login-form-input-group">
                        <label>Password</label>
                        <input name="password" type="password" required>
                    </div>
                </div>
            </div>
            <div class="login-form-btn-container ">
                <button class="login-form-btn" type="submit">Login</button>
            </div>

        </form>
    </div>
</div>


<script src="/resources/js/script.js"/>
></script>
</body>
</html>
