<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="routes-section" class="hidden">
    <h3 id="routes_title">Routes for train ***</h3>
    <div class="addNew-section">
        <form method="post" action="/add/route">
            <input id="routes_train_id" name="train" type="hidden">
            <div id="select_container">
                <div id="routes_stations_div">
                    <select name="timetableItems[0].station">
                        <c:forEach var="station" items="${stations}">
                            <option value="${station.id}">${station.name}</option>
                        </c:forEach>
                    </select>
                    <label><input type="datetime-local" name="timetableItems[0].departureTime">Time</label>
                </div>
            </div>
            <button type="button" onclick="addNewSelect()">+</button>
            <button class="btn btn-primary" type="submit"> ADD</button>
        </form>
    </div>
    <div class="routes-table">
        <h3>Routes</h3>
        <div style="display: none" class="table-row table-header table-columns-2" id="routes-table-header">
            <div class="table-cell">Time</div>
            <div class="table-cell">Station</div>
        </div>
        <div id="routes-table-content" >

        </div>

    </div>
</div>

