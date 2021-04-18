<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin area</title>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
</head>
<body>
<p>Admin Area
</p>
<div>
    <div class="tabs">
        <div class="tab"></div>
        <div class="tab"></div>
    </div>
    <div class="addNew-section">
        <form method="post" action="/add-train">
            <label for="train-number">Train number</label>
            <input id="train-number" name="number" type="number">
            <label for="seat-count">Number of seats </label>
            <input id="seat-count" name="seat_count" type="number">
            <button class="btn" type="submit"> ADD</button>
        </form>
    </div>
    <div class="train-table">
        <h3>Train list</h3>
        <div class="table-row table-header">
            <div class="table-cell">Train number</div>
            <div class="table-cell">Number of seats</div>
            <div class="table-cell"></div>
            <div class="table-cell"></div>
        </div>
        <c:forEach var="train" items="${trains}">
            <div class="table-row">
                <span class="table-cell">${train.number}</span>
                <span class="table-cell">${train.seat_count}</span>
                <span class="table-cell"><span class="material-icons">edit</span></span>
                <span class="table-cell">
                    <a href="/delete/train/${train.id}">
                        <span class="material-icons">clear</span>
                    </a>
                </span>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
