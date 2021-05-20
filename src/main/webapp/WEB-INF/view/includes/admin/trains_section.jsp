<%--Trains    --%>
<div id="train-section" class="train-section main-subsection hidden">
    <div class="addNew-section">
        <form method="post" action="/admin/add/train">
            <div class="input-box">
                <label for="train-number">Train number</label>
                <input id="train-number" name="number" type="number" required>
            </div>
            <div class="input-box">
                <label for="seat-count">Number of seats </label>
                <input id="seat-count" name="seatCount" type="number" required>
            </div>
            <%--            <select multiple name="stations">--%>
            <%--                <c:forEach var="station" items="${stations}">--%>
            <%--                    <option value="${station.id}">${station.name}</option>--%>
            <%--                </c:forEach>--%>
            <%--            </select>--%>
            <button class="btn btn-primary" type="submit">ADD NEW TRAIN</button>
        </form>
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
        <c:forEach var="train" items="${trains}">
            <div class="table-row table-columns-5">
                <span class="table-cell">${train.number}</span>
                <span class="table-cell">${train.seatCount}</span>
                <span class="table-cell station-names-cell"
                      onclick="openRoutesSection(${train.number}, ${train.id},${train.seatCount},
                              '<c:forEach var="station" items="${train.stationsUnique}">${station},</c:forEach>')">
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

                    <span class="material-icons md-18"
                          onclick="openTrainEditPopUp(${train.id}, ${train.number}, ${train.seatCount},
                                  '<c:forEach var="station" items="${train.stations}">${station},</c:forEach>')">
                        edit
                    </span>

                </span>
                <span class="table-cell">
                    <a href="/admin/delete/train/${train.id}" class="icon">
                        <span class="material-icons md-18">delete</span>
                    </a>
                </span>
            </div>
        </c:forEach>
    </div>
</div>