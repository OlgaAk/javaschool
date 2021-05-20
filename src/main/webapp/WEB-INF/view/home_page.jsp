<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
</head>
<body>

<%@include file="includes/common/navbar.jsp" %>

<div class="header">
    <p>Home Area</p>
</div>

<div id="train-img-container">
    <img id="train-img" src="/resources/img/train-img.jpeg"/>
    <h1 class="train-search-title">Book your train ticket now</h1>
</div>

<div class="main-container-home-with-hero">

    <%@include file="includes/home/train_search_section.jsp" %>
    <%@include file="includes/home/train_search_result_section.jsp" %>
</div>


<script src="/resources/js/home.js"/>
></script>
</body>
</html>
