<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin area</title>
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/admin.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/map.css"/>" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
    <script src="https://www.gstatic.com/external_hosted/handlebars/v4.7.6/handlebars.min.js"></script>
    <script src="/resources/js/map.js"></script>

    <script src="/resources/js/map_config.js"></script>
    <script id="locator-result-items-tmpl" type="text/x-handlebars-template" defer>
        {{#each locations}}
        <li class="location-result" data-location-index="{{index}}">
            <button class="select-location" type="button">
                <p class="name no-margin" data-lat="{{coords.lat}}" data-lng="{{coords.lng}}" onclick="setStationInputName(this.innerText, this.dataset.lat, this.dataset.lng)">{{title}}</p>
            </button>
        </li>
        {{/each}}
    </script>
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
                        <button class="btn btn-primary" onclick="showPopUpById('station-create-popup-container')">ADD
                            NEW STATION
                        </button>

                </div>
                <div class="table">
                    <h3>Station list</h3>
                    <div class="table-row table-header table-columns-3-left-big">
                        <div class="table-cell">Station name</div>
                        <div class="table-cell"></div>
                        <div class="table-cell"></div>
                    </div>
                    <c:forEach var="station" items="${stations}">
                        <div class="table-row table-columns-3-left-big">
                            <span class="table-cell" onclick="openScheduleSection(${station.id}, '${station.name}')">
                                    ${station.name}
                            </span>
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

    </div>
    <%@include file="includes/admin/schedule_section.jsp" %>

</div>


<div id="station-create-popup-container" class="edit-popup-container hidden">
    <div class="edit-popup map-popup">
        <form method="post" action="/admin/add/station">
        <div id="map-container">

                <h4>Select from list of cities:</h4>
                <label>Selected location</label>   <span id="station-name-p"></span>
                <input id="station-name-input" name="name" type="text" hidden required>
                <input id="station-latitude" name="latitude" type="text" hidden>
                <input id="station-longitude" name="longitude" type="text" hidden>

            <div id="locations-panel">
                <div id="locations-panel-list">

                    <div class="section-name" id="location-results-section-name">
                        All locations
                    </div>
                    <div class="results">
                        <ul id="location-results-list"></ul>
                    </div>
                </div>
                <div id="locations-panel-details"></div>
            </div>
            <div id="map"></div>
        </div>

            <button class="btn btn-primary float-right" type="submit">ADD STATION</button>
            <button class="btn btn-close-popup" type="button"
                    onclick="closeEditPopUp('station-create-popup-container')">
                <span class="material-icons md-18">close</span>
            </button>
        </form>
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

<script src="/resources/js/admin.js" defer></script>

<script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDpRycTSimqO5xTUhAQWf7rBWUB5s1pd1o&callback=initMap&libraries=places,geometry&channel=GMPSB_locatorplus_v2_cABCDE"
        async defer></script>
</body>
</html>
