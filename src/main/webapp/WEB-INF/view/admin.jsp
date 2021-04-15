<%--
  Created by IntelliJ IDEA.
  User: olgaaktas
  Date: 13.04.2021
  Time: 13:44
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin area</title>
    <link href="<c:url value="/resources/css/style.css"/>" rel="stylesheet">
</head>
<body>
<p>Admin Area</p>
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
</div>
</body>
</html>
