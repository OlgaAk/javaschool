<%--Trains    --%>
<div id="train-section" class="train-section main-subsection">
    <div class="addNew-section">
        <button id="add-train-open-btn" onclick="showCreateTrainPopUp()" class="btn btn-primary" type="submit">ADD NEW
            TRAIN
        </button>

    </div>
    <div class="train-table ">
        <h3>Train list</h3>
        <div class="table-row table-header table-columns-5">
            <div class="table-cell">Train number</div>
            <div class="table-cell">Number of seats</div>
            <div class="table-cell">Stations</div>
            <div class="table-cell"></div>
            <div class="table-cell"></div>
        </div>
        <c:choose>
            <c:when test="${trains.size()==0}">
                <br> no trains yet <br>
            </c:when>
            <c:otherwise>
                <c:forEach var="train" items="${trains}">
                    <div class="table-row table-columns-5">
                        <a href="/admin/train/${train.id}"><span class="table-cell">${train.number}</span></a>
                        <span class="table-cell">${train.seatCount}</span>
                        <span class="table-cell station-names-cell"
                              onclick="openRoutesSection(${train.number}, ${train.id},${train.seatCount},
                                      '<c:forEach var="station"
                                                  items="${train.stationsUnique}">${station},</c:forEach>')">
                    <c:choose>
                        <c:when test="${train.stationsUnique.size()==0}">
                            no stations yet
                        </c:when>
                        <c:otherwise>
                            <c:forEach var="station" items="${train.stationsUnique}" varStatus="loop">
                                ${station}<c:if test="${!loop.last}">,&nbsp;</c:if>
                            </c:forEach>
                        </c:otherwise>
                    </c:choose>

                    </span>
                        <span class="table-cell">

                    <span class="material-icons icon md-18"
                          onclick="openTrainEditPopUp(${train.id}, ${train.number}, ${train.seatCount},
                                  '<c:forEach var="station" items="${train.stations}">${station},</c:forEach>')">
                        edit
                    </span>

                </span>
                        <span class="table-cell">
                    <a href="/admin/delete/train/${train.id}" class="icon">
                        <span onclick="showSpinner()" class="material-icons icon md-18">delete</span>
                    </a>
                </span>
                    </div>
                </c:forEach>
            </c:otherwise>
        </c:choose>

    </div>
</div>

<%--CREATE TRAIN POP-UP--%>
<div id="train-create-popup-container" class="edit-popup-container hidden">
    <div class="edit-popup">
        <form method="post" action="/admin/add/train">
            <div class="input-box input-row train-input-info">
                <label for="train-number">Train number</label>
                <input id="train-number" name="number" type="number" required>
            </div>
            <div class="input-box input-row train-input-info">
                <label for="seat-count">Number of seats </label>
                <input id="seat-count" name="seatCount" type="number" required>
            </div>
            <br>
            <hr>
            <div id="select_container">
                <p class="bold">Timetable</p>
                <div id="routes_stations_div">
                    <div class="routes_stations_div_item input-row">
                        <span onclick="removeParent(this)" class="material-icons">clear</span></button>
                        <label>Station
                            <select class="timetable-input timetable-input-left" name="routePlan.timetableItems[0].stationId">
                                <c:forEach var="station" items="${stations}">
                                    <option value="${station.id}" ${station.id==1? "selected": ""}>${station.name}</option>
                                </c:forEach>
                            </select>
                        </label>
                        <label>Arrival Time
                            <input class="timetable-input" type="time" name="routePlan.timetableItems[0].arrivalTime"
                                   ></label>
                        <label>Departure Time
                            <input class="timetable-input " type="time" name="routePlan.timetableItems[0].departureTime" ></label>

                    </div>
                </div>

                    <span onclick="addNewSelect()" class="float-right add-circle material-icons">add_circle</span>
                <br>
                <br><hr>
                <p>Days of week</p>
                <div id="route-days-of-week" class="input-row">

                    <div class="route-day-of-week">
                        <input type="checkbox" id="monday" name="routePlan.weekdays" value="1">
                        <label for="monday">Monday</label>
                    </div>
                    <div class="route-day-of-week">
                        <input type="checkbox" id="tuesday" name="routePlan.weekdays" value="2">
                        <label for="tuesday">Tuesday</label>
                    </div>
                    <div class="route-day-of-week">
                        <input type="checkbox" id="wednesday" name="routePlan.weekdays" value="3">
                        <label for="wednesday">Wednesday</label>
                    </div>
                    <div class="route-day-of-week">
                        <input type="checkbox" id="thursday" name="routePlan.weekdays" value="4">
                        <label for="thursday">Thursday</label>
                    </div>
                    <div class="route-day-of-week">
                        <input type="checkbox" id="friday" name="routePlan.weekdays" value="5">
                        <label for="friday">Friday</label>
                    </div>
                    <div class="route-day-of-week">
                        <input type="checkbox" id="saturday" name="routePlan.weekdays" value="6">
                        <label for="saturday">Saturday</label>
                    </div>
                    <div class="route-day-of-week">
                        <input type="checkbox" id="sunday" name="routePlan.weekdays" value="0">
                        <label for="sunday">Sunday</label>
                    </div>
                    <div class="route-day-of-week">
                        <input type="checkbox" id="everyday" name="routePlan.weekdays" value="0,1,2,3,4,5,6"
                               checked>
                        <label for="everyday">Every day</label>
                    </div>
                </div>
            </div>
            <button class="btn btn-primary float-right" type="submit">ADD TRAIN</button>
            <button class="btn btn-close-popup" type="button" onclick="closeEditPopUp('train-create-popup-container')">
                <span class="material-icons md-18">close</span>
            </button>
        </form>
    </div>
</div>


<%--EDIT POP-UP--%>
<div id="train-edit-popup-container" class="edit-popup-container hidden">
    <div id="train-edit-popup" class="edit-popup">
        <h3>Edit train info</h3>
        <form method="post" action="/admin/edit/train">
            <input id="edit_train_id" name="id" type="hidden">
            <label><input id="edit_train_number" name="number" required type="number">Train number</label>
            <label><input id="edit_train_seat_count" name="seat_count" required type="number">Number of seats </label>
            <%--            <select multiple name="stations" id="edit_train_select_stations">--%>
            <%--                <c:forEach var="station" items="${stations}">--%>
            <%--                    <option value="${station.id}">${station.name}</option>--%>
            <%--                </c:forEach>--%>
            <%--            </select>--%>
            <button class="btn btn-primary" type="submit">EDIT</button>
            <button class="btn btn-close-popup" type="button" onclick="closeEditPopUp('train-edit-popup-container')">
                <span class="material-icons md-18">close</span>
            </button>
        </form>
    </div>
</div>