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
<%--                    <select name="timetableItems[1].station">--%>
<%--                         <c:forEach var="station" items="${stations}">--%>
<%--                            <option value="${station.id}">${station.name}</option>--%>
<%--                        </c:forEach>--%>
<%--                    </select>--%>
<%--                    <label><input type="datetime-local" name="timetableItems[1].departureTime">Time</label>--%>
                </div>
            </div>
            <button type="button" onclick="addNewSelect()">+</button>

            <%--                <label for="schedule_departure_time">Time</label>--%>
            <%--                <input id="schedule_departure_time" name="departureTime"--%>
            <%--                       type="datetime-local" required>--%>
            <button class="btn btn-primary" type="submit"> ADD</button>
        </form>
    </div>
    <div class="routes-table">
        <h3>Routes</h3>
        <div class="table-row table-header table-row-4">
            <div class="table-cell">Train number</div>
            <div class="table-cell">Time</div>
            <div class="table-cell"></div>
            <div class="table-cell"></div>
        </div>
        <%--                <c:forEach var="train" items="${trains}">--%>
        <%--                    <div class="table-row table-row-train">--%>
        <%--                        <span class="table-cell">${train.number}</span>--%>
        <%--                        <span class="table-cell"></span>--%>
        <%--                        <span class="table-cell">--%>
        <%--                        <c:forEach var="station" items="${train.stations}" varStatus="loop">--%>
        <%--                            ${station.name}<c:if test="${!loop.last}">,&nbsp;</c:if>--%>
        <%--                        </c:forEach>--%>
        <%--                    </span>--%>
        <%--                        <span class="table-cell">--%>

        <%--                    <span class="material-icons md-18"--%>
        <%--                          onclick="openTrainEditPopUp(${train.id}, ${train.number}, ${train.seat_count},--%>
        <%--                                  '<c:forEach var="station" items="${train.stations}">${station.name},</c:forEach>')">--%>
        <%--                        edit--%>
        <%--                    </span>--%>

        <%--                </span>--%>
        <%--                        <span class="table-cell">--%>
        <%--                    <a href="/delete/train/${train.id}" class="icon">--%>
        <%--                        <span class="material-icons md-18">clear</span>--%>
        <%--                    </a>--%>
        <%--                </span>--%>
        <%--                    </div>--%>
        <%--                </c:forEach>--%>
    </div>
</div>

