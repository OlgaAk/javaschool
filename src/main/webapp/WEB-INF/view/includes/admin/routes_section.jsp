<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="routes-section" class="hidden">
    <h3 id="routes_title">Routes for train ***</h3>
    <div class="addNew-section">
        <form method="post" action="/admin/add/route">
            <input id="routes_train_id" name="train" type="hidden">
            <div id="select_container">
                <div id="routes_stations_div">
                    <select name="timetableItems[0].station">
                        <c:forEach var="station" items="${stations}">
                            <option value="${station.id}">${station.name}</option>
                        </c:forEach>
                    </select>
                    <label><input type="date" name="timetableItems[0].departureDate">Date</label>
                    <label><input type="time" name="timetableItems[0].departureTime">Time</label>
                </div>
            </div>
            <button type="button" onclick="addNewSelect('select_container')">+</button>
            <button class="btn btn-primary" type="submit"> ADD</button>
        </form>
    </div>
    <div class="routes-table">
        <h3>Routes</h3>
        <div style="display: none" class="table-row table-header table-columns-3" id="routes-table-header">
            <div class="table-cell">Date</div>
            <div class="table-cell">Time</div>
            <div class="table-cell">Station</div>
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
            <button class="btn btn-primary" type="submit">EDIT</button>
            <button id="deleteRouteBtn" class="btn " type="button">DELETE</button>
            <button class="btn btn-close-popup" type="button" onclick="closeEditPopUp('route-edit-popup-container')">
                <span class="material-icons md-18">close</span>
            </button>
        </form>

    </div>
</div>


