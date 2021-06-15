<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Train info</title>
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/admin.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/calender.css"/>" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
</head>
<body>
<%@include file="includes/common/navbar.jsp" %>

<div class="main-container">

    <div id="trains-subpage" class="admin-subpage">
        <div>
            <div id="train-section" class="train-section main-subsection">
                <div id="train-info-header">
                    <div id="train-info-header-left">
                        <div id="train-info-header-top">
                            <button id="backToTrainListBtn" class="btn btn-primary" type="button">
                                <a href="/admin">BACK</a>
                            </button>
                            <h2>Train number ${train.number}</h2>
                            <span>Total seat count ${train.seatCount}</span>
                        </div>
                        <div id="train-info-header-bottom">
                            <p class="small-title">Schedule</p>
                            <p class="small-title-text-below">Days:
                                <c:forEach var="dayIndex" items="${train.routePlan.weekdays}">
                                    ${train.routePlan.daysOfWeekNames[dayIndex].substring(0,2)}
                                </c:forEach>
                            </p>
                        </div>
                    </div>
                    <div>
                        <%@include file="includes/common/calender.jsp" %>
                    </div>
                </div>
                <div class="train-table ">

                    <div class="table-row table-header table-columns-3">
                        <div class="table-cell">Station</div>
                        <div class="table-cell">Arrival time</div>
                        <div class="table-cell">Departure time</div>
                    </div>
                    <c:forEach var="timetableItem" items="${train.routePlan.timetableItems}">
                        <div class="table-row table-columns-3">
                            <span class="table-cell">${timetableItem.stationName}</span>
                            <span class="table-cell">${timetableItem.formattedArrivalTime}</span>
                            <span class="table-cell">${timetableItem.formattedDepartureTime}</span>
                        </div>
                    </c:forEach>

                </div>
            </div>
            <div class="train-table ">
                <h2>Trains</h2>
                <div class="table-row table-header table-columns-3">
                    <div class="table-cell">Date</div>
                    <div class="table-cell">Tickets sold</div>
                    <div class="table-cell"></div>
                </div>
                <c:forEach var="route" items="${train.routePlan.routes}">
                    <div class="table-row table-columns-3">
                        <span class="table-cell">${route.formattedDepartureDate}</span>
                        <span class="table-cell">${route.tickets.size()}</span>
                        <span class="table-cell"><a href="/admin/delete/route/${route.id}" class="icon">
                            <span class="material-icons md-18">delete</span>
                        </a></span>
                    </div>
                </c:forEach>
            </div>

        </div>
    </div>
</div>
</div>


<script src="/resources/js/admin.js"/>
></script>
</body>
</html>
