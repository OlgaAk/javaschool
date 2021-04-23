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
<div class="header">
    <p>Admin Area</p>
</div>

<div class="main-container">

    <div id="main-container-left-side">
        <%@include file="admin/trains_section.jsp" %>
        <%@include file="admin/stations_section.jsp" %>
    </div>

    <div id="main-container-right-side">
        <%@include file="admin/schedule_section.jsp" %>
        <%@include file="admin/routes_section.jsp" %>
    </div>

</div>

<%--EDIT POP-UP--%>
<div id="train-edit-popup-container" class="edit-popup-container hidden">
    <div id="train-edit-popup" class="edit-popup">
        <h3>Edit train info</h3>
        <form method="post" action="/edit/train">
            <input id="edit_train_id" name="id" type="hidden">
            <label><input id="edit_train_number" name="number" required type="number">Train number</label>
            <label><input id="edit_train_seat_count" name="seat_count" required type="number">Number of seats </label>
            <select multiple name="stations" id="edit_train_select_stations">
                <c:forEach var="station" items="${stations}">
                    <option value="${station.id}">${station.name}</option>
                </c:forEach>
            </select>
            <button class="btn btn-primary" type="submit">EDIT</button>
            <button class="btn btn-secondary" type="button" onclick="closeEditPopUp('train-edit-popup-container')">
                CANCEL
            </button>
        </form>
    </div>
</div>

<%--EDIT POP-UP--%>
<div id="station-edit-popup-container" class="edit-popup-container hidden">
    <div id="station-edit-popup" class="edit-popup">
        <h3>Edit station info</h3>
        <form method="post" action="/edit/station">
            <input id="edit_station_id" name="id" type="hidden">
            <label><input id="edit_station_name" name="name" required type="text">Station name</label>
            <button class="btn btn-primary" type="submit">EDIT</button>
            <button class="btn btn-secondary" type="button" onclick="closeEditPopUp('station-edit-popup-container')">
                CANCEL
            </button>
        </form>
    </div>
</div>

<script src="/resources/js/script.js"/>
></script>
</body>
</html>
