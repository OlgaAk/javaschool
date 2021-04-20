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
                <button class="btn" type="submit"> ADD</button>
            </form>
        </div>
        <div class="train-table">
            <h3>Train list</h3>
            <div class="table-row table-header table-row-train">
                <div class="table-cell">Train number</div>
                <div class="table-cell">Number of seats</div>
                <div class="table-cell">Stations</div>
                <div class="table-cell"></div>
                <div class="table-cell"></div>
            </div>
            <c:forEach var="train" items="${trains}">
                <div class="table-row table-row-train">
                    <span class="table-cell">${train.number}</span>
                    <span class="table-cell">${train.seat_count}</span>
                    <span class="table-cell">
                        <c:forEach var="station" items="${train.stations}" varStatus="loop">
                            ${station.name}
                            <c:if test="${!loop.last}">,&nbsp;</c:if>
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
                <button class="btn" type="submit">ADD</button>
            </form>
        </div>
        <div class="table">
            <h3>Station list</h3>
            <div class="table-row table-header table-row-station">
                <div class="table-cell">Station name</div>
                <div class="table-cell"></div>
                <div class="table-cell"></div>
                <div class="table-cell"></div>
            </div>
            <c:forEach var="station" items="${stations}">
                <div class="table-row table-row-station">
                    <span class="table-cell">${station.name}</span>
                    <span class="table-cell"></span>
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

</div>
<%--main section end--%>

<%--EDIT POP-UP--%>
<div id="train-edit-popup-container" class="edit-popup-container">
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
<div id="station-edit-popup-container" class="edit-popup-container">
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

<script>
    function openTrainEditPopUp(id, number, seats, stations) {
        document.getElementById("train-edit-popup-container").style.visibility = 'visible';
        // insert saved values into fields
        document.getElementById("edit_train_number").value = number;
        document.getElementById("edit_train_seat_count").value = seats;
        document.getElementById("edit_train_id").value = id;
        let select = document.getElementById("edit_train_select_stations");
        let values = stations.split(',');
        for (let i = 0; i < select.options.length; i++) {
            select.options[i].selected = values.indexOf(select.options[i].text) >= 0;
        }
    }


    function openStationEditPopUp(id, name) {
        document.getElementById("station-edit-popup-container").style.visibility = 'visible';
        // insert saved values into fields
        document.getElementById("edit_station_name").value = name;
        document.getElementById("edit_station_id").value = id;
    }

    function closeEditPopUp(container) {
        document.getElementById(container).style.visibility = 'hidden';
    }
</script>
</body>
</html>
