function openTrainEditPopUp(id, number, seats, stations) {
    document.getElementById("train-edit-popup-container").classList.remove("hidden");
    // insert saved values into fields
    document.getElementById("edit_train_number").value = number;
    document.getElementById("edit_train_seat_count").value = seats;
    document.getElementById("edit_train_id").value = id;
    let select = document.getElementById("edit_train_select_stations");
    let values = stations.split(',');
    for (let i = 0; i < select.options.length; i++) {
        select.options[i].selected = values.indexOf(select.options[i].text) >= 0;
    }
}


function openStationEditPopUp(id, name) {
    document.getElementById("station-edit-popup-container").classList.remove("hidden");
    // insert saved values into fields
    document.getElementById("edit_station_name").value = name;
    document.getElementById("edit_station_id").value = id;
}

function openRouteEditPopUp(route) {
    document.getElementById("route-edit-popup-container").classList.remove("hidden");
    // insert saved values into fields
    document.getElementById("edit_route_id").value = route.id;
    console.log(route)
    document.getElementById("edit_route_train_id").value = route.trainId;
    let routeEditTable = document.getElementById("route-table-edit-content");
    routeEditTable.innerHTML = "";
    addRouteEditContent(route, routeEditTable);
    document.getElementById("deleteRouteBtn")
        .addEventListener("click", () => deleteRoute(route.id))
}

function addRouteEditContent(route, routeEditTable) {
    route.timetableItems.forEach((timeTable, index) => {
        let clone = document.getElementById("routes_stations_div").cloneNode(true);
        let select = clone.children[1].children[0];
        select.name = "routePlan.timetableItems[" + index + "].stationId";
        console.log(timeTable)
        select.value = timeTable.stationId;
        let timeinput = clone.children[2].children[0];
        timeinput.name = "routePlan.timetableItems[" + index + "].arrivalTime";
        timeinput.value = getFormattedTime(timeTable.arrivalTimeAsDate);
        let timeinput2 = clone.children[3].children[0];
        timeinput2.name = "routePlan.timetableItems[" + index + "].departureTime";
        timeinput2.value = getFormattedTime(timeTable.departureTimeAsDate);
        let inputForId = document.createElement("input");
        inputForId.type = "hidden";
        inputForId.name = "routePlan.timetableItems[" + index + "].id";
        inputForId.value = timeTable.id;
        clone.appendChild(inputForId);
        clone.id = ""
        clone.classList.add("route-timetable-item")
        routeEditTable.appendChild(clone);
    })
}

async function deleteRoute(id) {
    let response = await fetch("/admin/delete/route/" + id);
    if (response.ok) {
        console.log(response)
        document.getElementById("route-edit-popup-container").classList.add("hidden");
        document.getElementById("routes-section").classList.add("hidden");
    } else {
        console.log("Ошибка HTTP: " + response.status);
    }
}

function closeEditPopUp(container) {
    document.getElementById(container).classList.add("hidden");
}

async function openScheduleSection(stationId, stationName, trains) {
    hideRoutesShowSchedule(stationId, stationName);
    let station = await fetchStation(stationId);
    let select = document.getElementById("schedule_station_trains");
    // let values = trains.split(',');
    // for (let i = 0; i < select.options.length; i++) {
    //     select.options[i].selected = values.indexOf(select.options[i].text) >= 0;
    // }
    let scheduleTable = document.getElementById("schedule-table");
    scheduleTable.innerHTML = "";
    station.timetableItems.forEach(item => {
        let div = document.createElement(("div"));
        div.classList.add("schedule-item")
        div.innerHTML = getFormattedDate(item.departureDateAsDate) +
            " " + getFormattedTime(item.departureTimeAsDate)
            + " Train №" + item.trainNumber + " " +
            item.startTripStationName + "-" + item.endTripStationName
        scheduleTable.append(div);
    })
}

function hideRoutesShowSchedule(stationId, stationName) {
    // document.getElementById("routes-section").classList.add("hidden");
    document.getElementById("schedule-section").classList.remove("hidden");
    // document.getElementById("schedule_station_id").value = stationId;
    document.getElementById("schedule_title").innerText = "Schedule for station " + stationName;
}

function hideScheduleShowRoutes(trainNumber, trainId, seatCount) {
    // document.getElementById("schedule-section").classList.add("hidden");
    document.getElementById("routes-section").classList.remove("hidden");
    document.getElementById("routes_title").innerText = "Routes for train  " + trainNumber;
    document.getElementById("routes_train_id").value = trainId;
    document.getElementById("routes_seat_count").value = seatCount;
}

async function openRoutesSection(trainNumber, trainId, seatCount) {
    hideScheduleShowRoutes(trainNumber, trainId, seatCount);
    let routesTable = document.getElementById("routes-table-content");
    if (routesTable.dataset.train == trainId) return;
    routesTable.dataset.train = trainId;
    routesTable.innerHTML = "";
    let routes = await fetchRoutes(trainId);
    if (routes != null) {
        routes.forEach((route, index) => {
            createRouteTable(route, index, routesTable)
        })
    }
}

function createRouteTable(route, index, routesTable) {
    let routesTableHeader = document.getElementById("routes-table-header").cloneNode(true);
    routesTableHeader.style.display = "grid";
    routesTableHeader.removeAttribute("id");
    let routeTitle = document.createElement("h5");
    routeTitle.innerText = "Route " + (index + 1);
    routeTitle.classList.add("editable");
    routeTitle.addEventListener("click", () => openRouteEditPopUp(route))
    routesTable.append(routeTitle, routesTableHeader);
    createRouteTableRows(route, routesTable);
    createTicketsInfoContainer(route.tickets, routesTable);
}

function createTicketsInfoContainer(tickets, routesTable) {
    let passengerInfoTitle = document.createElement("p");
    passengerInfoTitle.innerHTML = "Passengers:";
    passengerInfoTitle.classList.add("passenger-info-title");
    routesTable.append(passengerInfoTitle);
    if (tickets.length == 0) {
        let passengerInfoRow = document.createElement("div");
        passengerInfoRow.innerHTML = "No passengers yet on this train."
        passengerInfoRow.classList.add("passenger-info-row");
        routesTable.append(passengerInfoRow);
    } else {
        tickets.forEach(ticket => createTicketsInfoRow(ticket, routesTable))
    }
    let divider = document.createElement("hr");
    routesTable.append(divider);
}

function createTicketsInfoRow(ticket, routesTable) {
    let passengerInfoRow = document.createElement("div");
    let span = document.createElement("span");
    let spanSeat = document.createElement("span");
    let spanPassenger = document.createElement("span");
    spanSeat.innerHTML = "Seat " + ticket.seatNumber + " - ";
    spanPassenger.innerHTML = ticket.passenger.firstName + " " + ticket.passenger.lastName;
    span.append(spanSeat, spanPassenger);
    passengerInfoRow.append(span);
    passengerInfoRow.classList.add("passenger-info-row");
    routesTable.append(passengerInfoRow);
}

function createRouteTableRows(route, routesTable) {
    route.timetableItems.forEach(timeTable => {
        let newCellRow = document.createElement("div");
        newCellRow.classList.add("table-row", "table-columns-3");
        let routeArrivalTimeCell = document.createElement("span");
        routeArrivalTimeCell.className = "table-cell";
        routeArrivalTimeCell.innerText = getFormattedTime(timeTable.arrivalTimeAsDate)
        let routeDeparureTimeCell = document.createElement("span");
        routeDeparureTimeCell.className = "table-cell";
        routeDeparureTimeCell.innerText = getFormattedTime(timeTable.departureTimeAsDate);
        let routeStationCell = document.createElement("span");
        routeStationCell.className = "table-cell";
        console.log(timeTable)
        routeStationCell.innerText = timeTable.stationName;
        newCellRow.append(routeStationCell, routeArrivalTimeCell, routeDeparureTimeCell);
        routesTable.append(newCellRow);
    })
}

function getFormattedDate(date) {
    return new Date(date).toLocaleString().split(",")[0];
}

function getFormattedDateYYYYMMDD(date) {
    return new Date(date).toISOString().split("T")[0];
}

function getFormattedTime(time) {
    return new Date(time).toLocaleTimeString().substring(0, 5);
}

function addNewSelect() {
    let container = document.getElementById("routes_stations_div");
    let clone = document.querySelector(".routes_stations_div_item").cloneNode(true);
    let nextSelectIndex = container.children.length; // button excluded
    console.log(nextSelectIndex)
    let select = clone.children[1].children[0]  // first child is an icon x
    select.name = "routePlan.timetableItems[" + nextSelectIndex + "].stationId";
    let timeinputArrival = clone.children[2].children[0]; //input is inside label
    timeinputArrival.name = "routePlan.timetableItems[" + nextSelectIndex + "].arrivalTime";
    timeinputArrival.value = null;
    let timeinputDeparture = clone.children[3].children[0];
    timeinputDeparture.name = "routePlan.timetableItems[" + nextSelectIndex + "].departureTime";
    timeinputDeparture.value = null;
    container.appendChild(clone);
}

//get info about a train routes with REST
async function fetchRoutes(trainId) {
    let response = await fetch("/admin/routes/" + trainId);
    if (response.ok) {
        let json = await response.json();
        console.log(json)
        return json;
    } else {
        console.log("Ошибка HTTP: " + response.status);
        return null;
    }
}


async function fetchStation(id) {
    let response = await fetch("/admin/station/" + id);
    if (response.ok) {
        let json = await response.json();
        console.log(json)
        return json;
    } else {
        console.log("Ошибка HTTP: " + response.status);
        return null;
    }
}


setOnclickListenerOnAdminMenuItems()

function openTrainsSection(event) {
    document.querySelectorAll(".admin_menu_item span").forEach(span => {
        span.classList.remove("active")
    })
    event.target.classList.add("active");
    document.getElementById("stations-subpage").classList.add("hidden");
    document.getElementById("trains-subpage").classList.remove("hidden");
}

function openStationsSection(event) {
    document.querySelectorAll(".admin_menu_item span").forEach(span => {
        span.classList.remove("active")
    })
    event.target.classList.add("active");
    document.getElementById("trains-subpage").classList.add("hidden");
    document.getElementById("stations-subpage").classList.remove("hidden");
}

function setOnclickListenerOnAdminMenuItems() {
    document.getElementById("admin_menu_item_trains").addEventListener("click", (event) => openTrainsSection(event))
    document.getElementById("admin_menu_item_stations").addEventListener("click", (event) => openStationsSection(event))
}


function showCreateTrainPopUp() {
    document.getElementById("train-create-popup-container").classList.remove("hidden");
}

function showPopUpById(id) {
    document.getElementById(id).classList.remove("hidden");
}

function fixInputNames(htmlElement) {
    console.log(htmlElement)
    for (let index = 0; index < htmlElement.children.length; index++) {
        let div = htmlElement.children[index];
        console.log(div)
        div.children[1].children[0].name = "routePlan.timetableItems[" + index + "].stationId";
        div.children[2].children[0].name = "routePlan.timetableItems[" + index + "].arrivalTime";
        div.children[3].children[0].name = "routePlan.timetableItems[" + index + "].departureTime";
    }
}

function removeParent(e) {
    let parent = e.parentElement;
    if (document.getElementsByClassName(parent.className).length > 1) {
        let container = parent.parentElement
        parent.remove();
        fixInputNames(container);
    }
}

function showSpinner(){
    document.getElementById("spinner").classList.remove("hidden");
    console.log("spinner");

}




