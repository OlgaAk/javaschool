<div class="train-search-container">
    <form method="get" action="/train-query">
        <div class="train-search-input-groups-container">
            <div class="train-search-input-groups-box">
                <div class="train-search-input-group" id="departure-station-input-container">
                    <label>From</label>
                    <input id="departure-station-input" autocomplete="off" name="departure_station" type="search"
                           required
                           onclick="showAllDepartureStationsList([<c:forEach var="station"
                                                                             items="${stations}">{'id':'${station.id}', 'name':'${station.name}'},</c:forEach>])">
                    <div id="departure-station-popup"></div>
                </div>
                <div id="arrival-station-container" class="train-search-input-group">
                    <label>To</label>
                    <input id="arrival-station-input" name="arrival_station" autocomplete="off" type="search" required
                           onclick="showAllArrivalStationsList([<c:forEach var="station"
                                                                           items="${stations}">{'id':'${station.id}', 'name':'${station.name}'},</c:forEach>])">
                    <div id="arrival-station-popup"></div>
                </div>
                <div class="train-search-input-group">
                    <label>Date</label>
                    <input id="departure-date-input" name="departure_date" type="date" required>
                </div>
            </div>
        </div>
        <div class="train-search-btn-container ">
            <button id="train-search-btn" class="train-search-btn" type="button">Search</button>
        </div>

    </form>
</div>