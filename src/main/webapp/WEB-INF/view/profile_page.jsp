<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
    <link href="<c:url value="/resources/css/main.css"/>" rel="stylesheet">
    <link href="<c:url value="/resources/css/profile.css"/>" rel="stylesheet">
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons"
          rel="stylesheet">
</head>
<body>

<%@include file="includes/common/navbar.jsp" %>

<div class="header">
    <p>Profile Area</p>
</div>

<div class="main-container-profile">

    <div id="main-container-profile-left-side">
        <div class="profile-menu-container">
            <div class="profile-menu-item">
                <p>Profile</p>
            </div>
            <div class="profile-menu-item">
                <p class="active">Tickets</p>
            </div>
        </div>
    </div>

    <div id="main-container-profile-right-side">
        <div class="profile-content-container">
            <div class="profile-personal-info profile-content-item hidden" id="profile-content-item-profile">
                <h3>${user.firstName} ${user.lastName}</h3>
                <p>${user.email}</p>
            </div>

            <div class="profile-content-item " id="profile-content-item-tickets">
                <c:choose>
                    <c:when test="${tickets.size()==0}">
                        <p>No tickets yet</p>
                    </c:when>
                    <c:otherwise>
                        <c:forEach items="${tickets}" var="ticket">
                            <div class="ticket-container ${ticket.isArchived? "ticket-archived" : ""}">
                                <div class="ticket-container-content">
                                    <h5>Ticket ${ticket.isArchived? "(archived)" : ""}</h5>
                                    <p>Train number: ${ticket.route.train.number}</p>
                                    <p>${ticket.departureTime} - ${ticket.arrivalTime}</p>
                                    <p>${ticket.startStation.name} - ${ticket.endStation.name}</p>
                                    <p>Passenger: ${ticket.passenger.firstName} ${ticket.passenger.lastName}</p>
                                    <p>Seat ${ticket.seat.number}</p>
                                </div>
                                <c:if test="${!ticket.isArchived}">
                                    <button class="btn-delete btn btn-primary ticket-container-btn"
                                            onclick="openCancelTicketPopUp(${ticket.id})">Cancel ticket
                                    </button>
                                </c:if>

                            </div>
                        </c:forEach>
                    </c:otherwise>
                </c:choose>


            </div>
        </div>
    </div>

</div>

<%--DELETE POP-UP--%>
<div id="ticket-edit-popup-container" class="edit-popup-container hidden">
    <div id="ticket-edit-popup" class="edit-popup">
        <h3>Delete ticket?</h3>
        <button class="btn btn-close-popup" type="button" onclick="closeEditPopUp('ticket-edit-popup-container')">
            <span class="material-icons md-18">close</span>
        </button>
        <a class="btn btn-primary btn-delete ticket-container-btn">
            Confirm
        </a>
    </div>
</div>

<script src="/resources/js/profile.js"/>
></script>
</body>
</html>
