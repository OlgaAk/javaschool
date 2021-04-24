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
    <p>Home Area</p>
</div>

<div class="main-container-home">
    <div>
        <h2 class="train-search-title">Book a train ticket</h2>
    </div>
    <div class="train-search-container">
        <form method="get" action="/train-query">
            <div class="train-search-input-groups-container">
                <div class="train-search-input-groups-box">
                    <div class="train-search-input-group">
                        <label>From</label>
                        <input name="departure_station" type="search" required>
                    </div>
                    <div class="train-search-input-group">
                        <label>To</label>
                        <input name="arrival_station" type="search" required>
                    </div>
                    <div class="train-search-input-group">
                        <label>Date</label>
                        <input name="departure_date" type="date" required>
                    </div>
                </div>
            </div>
            <div class="train-search-btn-container ">
                <button class="train-search-btn" type="submit">Search</button>
            </div>

        </form>
    </div>
</div>


<script src="/resources/js/script.js"/>
></script>
</body>
</html>
