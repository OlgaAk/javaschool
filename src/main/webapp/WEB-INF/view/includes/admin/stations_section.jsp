<%--Stations--%>

<div class="station-section main-subsection">
    <div class="addNew-section">
        <form method="post" action="/admin/add/station">
            <label>Station name</label>
            <input id="station-name" name="name" type="text" required>
            <button class="btn btn-primary" type="submit">ADD</button>
        </form>
    </div>
    <div class="table scrollable">
        <h3>Station list</h3>
        <div class="table-row table-header table-columns-4">
            <div class="table-cell">Station name</div>
            <div class="table-cell"></div>
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
                    <span class="material-icons"
                          onclick="openStationEditPopUp(${station.id}, '${station.name}')">
                        edit
                    </span>
                     </span>
                <span class="table-cell">
                    <a href="/admin/delete/station/${station.id}" class="icon">
                        <span class="material-icons">clear</span>
                    </a>
                </span>
            </div>
        </c:forEach>
    </div>
</div>