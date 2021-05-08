<%--Trains    --%>
    <div class="train-section main-subsection">
        <div class="addNew-section">
            <form method="post" action="/admin/add/train">
                <label for="train-number">Train number</label>
                <input id="train-number" name="number" type="number" required>
                <label for="seat-count">Number of seats </label>
                <input id="seat-count" name="seat_count" type="number" required>
                <select multiple name="stations">
                    <c:forEach var="station" items="${stations}">
                        <option value="${station.id}">${station.name}</option>
                    </c:forEach>
                </select>
                <button class="btn btn-primary" type="submit"> ADD</button>
            </form>
        </div>
        <div class="train-table scrollable" >
            <h3>Train list</h3>
            <div class="table-row table-header table-columns-5">
                <div class="table-cell">Train number</div>
                <div class="table-cell">Number of seats</div>
                <div class="table-cell">Stations</div>
                <div class="table-cell"></div>
                <div class="table-cell"></div>
            </div>
            <c:forEach var="train" items="${trains}">
                <div class="table-row table-columns-5">
                    <span class="table-cell">${train.number}</span>
                    <span class="table-cell">${train.seat_count}</span>
                    <span class="table-cell" onclick="openRoutesSection(${train.number}, ${train.id},
                            '<c:forEach var="station" items="${train.stations}">${station.name},</c:forEach>')">
<%--                        <c:forEach var="station" items="${train.stations}" varStatus="loop">--%>
<%--                            ${station.name}<c:if test="${!loop.last}">,&nbsp;</c:if>--%>
<%--                        </c:forEach>--%>
                    </span>
                    <span class="table-cell">

                    <span class="material-icons md-18"
                          onclick="openTrainEditPopUp(${train.id}, ${train.number}, ${train.seat_count},
                                  '<c:forEach var="station" items="${train.stations}">${station.name},</c:forEach>')">
                        edit
                    </span>

                </span>
                    <span class="table-cell">
                    <a href="/admin/delete/train/${train.id}" class="icon">
                        <span class="material-icons md-18">clear</span>
                    </a>
                </span>
                </div>
            </c:forEach>
        </div>
    </div>