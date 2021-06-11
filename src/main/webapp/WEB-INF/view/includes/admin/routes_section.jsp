<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="routes-section" >
    <h3 id="routes_title">Routes for train ***</h3>
    <div class="addNew-section">
        <form method="post" action="/admin/add/route">
            <input id="routes_train_id" name="trainId" type="hidden">
            <input id="routes_seat_count" name="seatCount" type="hidden">
            <div id="select_container">
                <button type="button" onclick="addNewSelect('select_container')">+</button>
                <div id="routes_stations_div">
                    <select name="timetableItems[0].station">
                        <c:forEach var="station" items="${stations}">
                            <option value="${station.id}">${station.name}</option>
                        </c:forEach>
                    </select>
                    <label><input type="date" name="timetableItems[0].departureDate" required>Date</label>
                    <label><input type="time" name="timetableItems[0].arrivalTime" required>Arrival Time</label>
                    <label><input type="time" name="timetableItems[0].departureTime" required>Departure Time</label>
                </div>
            </div>

            <button class="btn btn-primary" type="submit">SAVE</button>
        </form>
    </div>
    <div class="routes-table">
        <h3>Routes</h3>
        <div style="display: none" class="table-row table-header table-columns-3" id="routes-table-header">
            <div class="table-cell">Station</div>
            <div class="table-cell">Arrival Time</div>
            <div class="table-cell">Departure Time</div>
        </div>
        <div id="routes-table-content" >

        </div>

    </div>
</div>

<%--EDIT POP-UP--%>
<div id="route-edit-popup-container" class="edit-popup-container hidden">
    <div class="edit-popup">
        <h3>Edit route info</h3>
        <form method="post" action="/admin/edit/route">
            <input id="edit_route_id" name="route" type="hidden">
            <input id="edit_route_train_id" name="trainId" type="hidden">
            <div id="select-container-edit">
                <div id="route-table-edit-content">
                    <select name="timetableItems[0].station">
                        <c:forEach var="station" items="${stations}">
                            <option value="${station.id}">${station.name}</option>
                        </c:forEach>
                    </select>
                    <label><input type="date" name="timetableItems[0].departureDate">Date</label>
                    <label><input type="time" name="timetableItems[0].departureTime">Time</label>
                </div>
            </div>
            <button type="button" onclick="addNewSelect('select-container-edit')">+</button>
            <button class="btn btn-primary btn-edit" type="submit">EDIT</button>
            <button id="deleteRouteBtn" class="btn btn-delete" type="button">DELETE</button>
            <button class="btn btn-close-popup" type="button" onclick="closeEditPopUp('route-edit-popup-container')">
                <span class="material-icons md-18">close</span>
            </button>
        </form>

    </div>
</div>


