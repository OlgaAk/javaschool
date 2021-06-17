<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin area</title>
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/admin.css"/>" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
</head>
<body>
<%@include file="includes/common/navbar.jsp" %>
<%@include file="includes/common/spinner.jsp" %>

<div class="main-container">

    <div id="stations-subpage" class="admin-subpage">
        <div class="main-container-left-side">
            <%--Stations--%>

            <div id="station-section" class="station-section main-subsection">
                <div class="addNew-section">
                    <form method="post" action="/admin/add/station">
                        <label>Station name</label>
                        <input id="station-name" name="name" type="text" required>
                        <button class="btn btn-primary" type="submit">ADD NEW STATION</button>
                    </form>
                </div>
                <div class="table">
                    <h3>Station list</h3>
                    <div class="table-row table-header table-columns-4">
                        <div class="table-cell">Station name</div>
                        <div class="table-cell">Schedule</div>
                        <div class="table-cell"></div>
                        <div class="table-cell"></div>
                    </div>
                    <c:forEach var="station" items="${stations}">
                        <div class="table-row table-columns-4">
                            <span class="table-cell">${station.name}</span>
                            <span class="table-cell" onclick="openScheduleSection(${station.id}, '${station.name}')
                                <%--'<c:forEach var="train" items="${station.trains}">${train.number},</c:forEach>') --%>
                                    ">View Schedule</span>
                            <span class="table-cell">
                    <span class="material-icons icon"
                          onclick="openStationEditPopUp(${station.id}, '${station.name}')">
                        edit
                    </span>
                     </span>
                            <span class="table-cell">
                    <a href="/admin/delete/station/${station.id}" class="icon">
                        <span class="material-icons icon">delete</span>
                    </a>
                </span>
                        </div>
                    </c:forEach>
                </div>
            </div>
        </div>
        <div class="main-container-right-side">
            <%@include file="includes/admin/schedule_section.jsp" %>
        </div>
    </div>

</div>


<%--ERROR POP-UP--%>
<div id="error-popup" class="edit-popup-container ${adminPageError && adminPageError != "" ? "" : "hidden"}">
    <div class="edit-popup">
        <h3>Error</h3>
        ${adminPageError}
        <button class="btn btn-close-popup" type="button"
                onclick="closeEditPopUp('error-popup')">
        <span class="material-icons md-18">close</span>
        </button>

    </div>
</div>


<%--EDIT POP-UP--%>
<div id="station-edit-popup-container" class="edit-popup-container hidden">
    <div id="station-edit-popup" class="edit-popup">
        <h3>Edit station info</h3>
        <form method="post" action="/admin/edit/station">
            <input id="edit_station_id" name="id" type="hidden">
            <label><input id="edit_station_name" name="name" required type="text">Station name</label>
            <button class="btn btn-primary" type="submit">EDIT</button>
            <button class="btn btn-close-popup" type="button" onclick="closeEditPopUp('station-edit-popup-container')">
                <span class="material-icons md-18">close</span>
            </button>
        </form>
    </div>
</div>

<script src="/resources/js/admin.js"/>
></script>
</body>
</html>
