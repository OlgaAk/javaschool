<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Signup</title>
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/login.css"/>" rel="stylesheet">
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
        <h2 class="train-search-title">Sign up</h2>
    </div>
    <div class="login-form-container signup-form">
        <sf:form method="post" action="/user/login/processsignup" modelAttribute="user">
            <div class="login-form-input-groups-container signup-form">
                <div class="login-form-input-groups-box">
                    <div class="login-form-input-group">
                        <sf:label path="firstName">First Name</sf:label>
                        <sf:input path="firstName" type="text" ></sf:input>
                        <sf:errors path="firstName" class="login-form-error"/>
                    </div>
                    <div class="login-form-input-group">
                        <sf:label path="lastName">Last Name</sf:label>
                        <sf:input path="lastName" type="text" />
                        <sf:errors path="lastName" class="login-form-error"/>
                    </div>
                    <div class="login-form-input-group">
                        <sf:label path="email">Email</sf:label>
                        <sf:input path="email" type="text" />
                        <sf:errors path="email" class="login-form-error"/>
                    </div>
                    <div class="login-form-input-group">
                        <sf:label path="password">Password</sf:label>
                        <sf:input path="password" type="password" />
                        <sf:errors path="password" class="login-form-error"/>
                    </div>
                </div>
            </div>
            <div class="login-form-btn-container ">
                <button class="login-form-btn" type="submit">Sign up</button>
            </div>
        </sf:form>
    </div>
</div>


<script src="/resources/js/admin.js"/>
></script>
</body>
</html>
