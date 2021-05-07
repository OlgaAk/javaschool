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
            <div class="profile-personal-info profile-content-item" id="profile-content-item-profile">
                <h3>${user.firstName} ${user.lastName}</h3>
                <p>${user.email}</p>
                <p>${user.dateOfBirth}</p>
            </div>

            <div class="profile-content-item hidden" id="profile-content-item-tickets">
                <p>No tickets yet</p>
            </div>
        </div>
    </div>

</div>


<script src="/resources/js/script.js"/>
></script>
</body>
</html>
