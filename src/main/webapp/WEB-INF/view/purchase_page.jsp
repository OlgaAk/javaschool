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

<div class="main-container-home">

<div class="train-search-result-item">
    <div class="train-search-result-item-left">
        <div class="train-search-result-item-row">
            <div class="train-search-result-item-time">${route.startTripTime}</div>
            <div class="train-search-result-item-station">${route.startTripStation}</div>
        </div>
        <div class="train-search-result-item-row">
            <div class="train-search-result-item-time">${route.endTripTime}</div>
            <div class="train-search-result-item-station">${route.endTripStation}</div>
        </div>
        <div class="train-search-result-item-row">
            <div class="train-search-result-item-duration">${route.tripDuration}</div>
            <div class="train-search-result-item-change">${route.changeType}</div>
        </div>
    </div>

</div>

</div>

<script src="/resources/js/script.js"/>
></script>
</body>
</html>
