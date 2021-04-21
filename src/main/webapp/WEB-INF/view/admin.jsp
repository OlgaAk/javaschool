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

    <%--Trains    --%>
    <div id="main-container-left-side">
        <div class="train-section main-subsection">
            <div class="addNew-section">
                <form method="post" action="/add/train">
                    <label for="train-number">Train number</label>
                    <input id="train-number" name="number" type="number" required>
                    <label for="seat-count">Number of seats </label>
                    <input id="seat-count" name="seat_count" type="number" required>
                    <select multiple name="stations">
                        <c:forEach var="station" items="${stations}">
                            <option value="${station.id}">${station.name}</option>
                        </c:forEach>
                    </select>
                    <button class="btn btn-primary" type="submit"> ADD</button>
                </form>
            </div>
            <div class="train-table">
                <h3>Train list</h3>
                <div class="table-row table-header table-row-5">
                    <div class="table-cell">Train number</div>
                    <div class="table-cell">Number of seats</div>
                    <div class="table-cell">Stations</div>
                    <div class="table-cell"></div>
                    <div class="table-cell"></div>
                </div>
                <c:forEach var="train" items="${trains}">
                    <div class="table-row table-row-5">
                        <span class="table-cell">${train.number}</span>
                        <span class="table-cell">${train.seat_count}</span>
                        <span class="table-cell" onclick="openRoutesSection(${train.number}, ${train.id},
                                '<c:forEach var="station" items="${train.stations}">${station.name},</c:forEach>')">
                        <c:forEach var="station" items="${train.stations}" varStatus="loop">
                            ${station.name}<c:if test="${!loop.last}">,&nbsp;</c:if>
                        </c:forEach>
                    </span>
                        <span class="table-cell">

                    <span class="material-icons md-18"
                          onclick="openTrainEditPopUp(${train.id}, ${train.number}, ${train.seat_count},
                                  '<c:forEach var="station" items="${train.stations}">${station.name},</c:forEach>')">
                        edit
                    </span>

                </span>
                        <span class="table-cell">
                    <a href="/delete/train/${train.id}" class="icon">
                        <span class="material-icons md-18">clear</span>
                    </a>
                </span>
                    </div>
                </c:forEach>
            </div>
        </div>


        <%--Stations--%>

        <div class="station-section main-subsection">
            <div class="addNew-section">
                <form method="post" action="/add/station">
                    <label for="train-number">Station name</label>
                    <input id="station-name" name="name" type="text" required>
                    <button class="btn btn-primary" type="submit">ADD</button>
                </form>
            </div>
            <div class="table">
                <h3>Station list</h3>
                <div class="table-row table-header table-row-4">
                    <div class="table-cell">Station name</div>
                    <div class="table-cell"></div>
                    <div class="table-cell"></div>
                    <div class="table-cell"></div>
                </div>
                <c:forEach var="station" items="${stations}">
                    <div class="table-row table-row-4">
                        <span class="table-cell">${station.name}</span>
                        <span class="table-cell" onclick="openScheduleSection(${station.id}, '${station.name}',
                                '<c:forEach var="train" items="${station.trains}">${train.number},</c:forEach>') ">View Schedule</span>
                        <span class="table-cell">
                    <span class="material-icons"
                          onclick="openStationEditPopUp(${station.id}, '${station.name}')">
                        edit
                    </span>
                     </span>
                        <span class="table-cell">
                    <a href="/delete/station/${station.id}" class="icon">
                        <span class="material-icons">clear</span>
                    </a>
                </span>
                    </div>
                </c:forEach>
            </div>
        </div>

    </div>
    <%--main section end--%>
    <div id="main-container-right-side">
        <%@include file="includes/schedule.jsp" %>
        <%@include file="includes/routes.jsp" %>
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

<script src="/resources/js/script.js"/>></script>
</body>
</html>
