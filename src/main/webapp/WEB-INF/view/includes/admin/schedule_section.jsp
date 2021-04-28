
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%--Schedule Section--%>
<div id="main-container-right-side">
    <div id="schedule-section" class="hidden">
        <h3 id="schedule_title">Schedules for station ***</h3>
        <div class="addNew-section">
            <form method="post" action="/add/timeTableItem" >
                <input id="schedule_station_id" name="station" type="hidden">
                <select id="schedule_station_trains" name="train">
                    <c:forEach var="train" items="${trains}">
                        <option value="${train.id}">${train.number}</option>
                    </c:forEach>
                </select>
                <label for="schedule_departure_time">Time</label>
                <input id="schedule_departure_time" name="departureTime" type="datetime-local" required>
                <button class="btn btn-primary" type="submit"> ADD</button>
            </form>
        </div>
        <div class="schedule-table">
            <h3>Schedule</h3>
            <div class="table-row table-header table-columns-4">
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
</div>
<%--Schedule section End--%>