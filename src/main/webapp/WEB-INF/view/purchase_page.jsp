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

<div class="main-container-home">

    <h2>Selected Trip</h2>
    <div class="train-search-result-item">
        <div class="train-search-result-item-left">
            <div class="train-search-result-item-row">
                <div class="train-search-result-item-time">${route.startTripTime}</div>
                <div id="startTripStation" data-id="${route.startTripStation.id}" data-routeid="${route.id}"
                     class="train-search-result-item-station">${route.startTripStation.name}</div>
            </div>
            <div class="train-search-result-item-row">
                <div class="train-search-result-item-time">${route.endTripTime}</div>
                <div id="endTripStation" data-id="${route.endTripStation.id}"
                     class="train-search-result-item-station">${route.endTripStation.name}</div>
            </div>
            <div class="train-search-result-item-row">
                <div class="train-search-result-item-duration">${route.tripDuration}</div>
                <div class="train-search-result-item-change">${route.changeType}</div>
            </div>
        </div>

    </div>

    <div class="purchase-tabs">
        <div id="purchase-tab-seat-select" class="purchase-tab active">Seat selection</div>
        <div id="purchase-tab-passenger" class="purchase-tab">Passenger Information</div>
        <div id="purchase-tab-confirm" class="purchase-tab">Confirmation</div>
    </div>

    <div class="purchase-sections">
        <div id="purchase-section-seat-select" class="purchase-section active">
            <h3>Select a seat</h3>
            <div class="seats-container">
                <c:forEach var="seat" items="${route.seats}">
                    <button data-id="${seat.id}" onclick="selectSeat(this, ${seat.number})" id="${seat.number}"
                            class="seat-btn ${seat.getVacant() ? '' : 'seat-occupied'}">${seat.number}</button>
                </c:forEach>
            </div>

            <div id="selected-seat-info"></div>

            <button disabled class="purchase-section-next-btn purchase-section-btn"
                    onclick="goToSection('purchase-section-passenger')">Next
            </button>
        </div>


        <div id="purchase-section-passenger" class="purchase-section">
            <h3>Enter passenger information</h3>
            <div class="">
                <div class="">
                    <div class="">
                        <div class="login-form-input-group">
                            <label>First Name</label>
                            <input id="passengerFirstname" name="firstName" type="text" required>
                        </div>
                        <div class="login-form-input-group">
                            <label>Last Name</label>
                            <input id="passengerLastname" name="lastName" type="text" required>
                        </div>
                        <div class="login-form-input-group">
                            <label>Date of birth</label>
                            <input id="passengerDateOfBirth" name="dateOfBirth" type="date" required>
                        </div>
                        <div class="login-form-input-group">
                            <label>Passport</label>
                            <input id="passengerPassportNumber" name="passport" type="number" required>
                        </div>
                    </div>
                </div>
            </div>

            <button class="purchase-section-back-btn purchase-section-btn"
                    onclick="goToSection('purchase-section-seat-select')">Back
            </button>
            <button class="purchase-section-next-btn purchase-section-btn"
                    onclick="goToSection('purchase-section-confirm')">Next
            </button>
        </div>


        <div id="purchase-section-confirm" class="purchase-section">
            <div id="passanger-data-error-message-box" class="error"></div>
            <h3>Confirm your booking</h3>
            <p>Trip details</p>
            <p>Passenger details</p>
            <p>Price</p>
            <button class="purchase-section-back-btn purchase-section-btn"
                    onclick="goToSection('purchase-section-passenger')">Back
            </button>
            <button class="purchase-section-next-btn purchase-section-btn" onclick="purchaseTicket()">Purchase</button>
        </div>
    </div>


</div>


<script src="/resources/js/script.js"/>
></script>
</body>
</html>
