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
    <p>Profile Area</p>
</div>

<div class="main-container-profile">

    <div id="main-container-profile-left-side">
        <div class="profile-menu-container">
            <div class="profile-menu-item">
                <p class="active">Profile</p>
            </div>
            <div class="profile-menu-item">
                <p>Tickets</p>
            </div>
        </div>
    </div>

    <div id="main-container-profile-right-side">
        <div class="profile-content-container">
            <div class="profile-personal-info">
                <h3>Ivan Ivanov</h3>
                <p>ivanov@email.com</p>
                <p>05.01.2000</p>
            </div>
        </div>
    </div>

</div>


<script src="/resources/js/script.js"/>
></script>
</body>
</html>
