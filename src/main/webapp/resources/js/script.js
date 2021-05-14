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
        let select = clone.children[0];
        select.name = "timetableItems[" + index + "].station";
        select.value = timeTable.station.id;
        let dateinput = clone.children[1].children[0];
        dateinput.name = "timetableItems[" + index + "].departureDate";
        dateinput.value = getFormattedDateYYYYMMDD(timeTable.fullDepartureDate);
        let timeinput = clone.children[2].children[0];
        timeinput.name = "timetableItems[" + index + "].departureTime";
        timeinput.value = getFormattedTime(timeTable.departureTimeAsDate);
        let inputForId = document.createElement("input");
        inputForId.type = "hidden";
        inputForId.name = "timetableItems[" + index + "].id";
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

function openScheduleSection(stationId, stationName, trains) {
    document.getElementById("routes-section").classList.add("hidden");
    document.getElementById("schedule-section").classList.remove("hidden");
    // insert saved values into fields
    document.getElementById("schedule_station_id").value = stationId;
    document.getElementById("schedule_title").innerText = "Schedule for station " + stationName;
    let select = document.getElementById("schedule_station_trains");
    let values = trains.split(',');
    for (let i = 0; i < select.options.length; i++) {
        select.options[i].selected = values.indexOf(select.options[i].text) >= 0;
    }
}


function hideScheduleShowRoutes(trainNumber, trainId, seatCount) {
    document.getElementById("schedule-section").classList.add("hidden");
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
    createRouteTableRows(route, routesTable)
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

function createRouteTableRows(route, routesTable) {
    route.timetableItems.forEach(timeTable => {
        let newCellRow = document.createElement("div");
        newCellRow.classList.add("table-row", "table-columns-3");
        let routeDateCell = document.createElement("span");
        routeDateCell.className = "table-cell";
        routeDateCell.innerText = getFormattedDate(timeTable.departureDateAsDate)
        let routeTimeCell = document.createElement("span");
        routeTimeCell.className = "table-cell";
        routeTimeCell.innerText = getFormattedTime(timeTable.departureTimeAsDate);
        let routeStationCell = document.createElement("span");
        routeStationCell.className = "table-cell";
        routeStationCell.innerText = timeTable.station.name;
        newCellRow.append(routeDateCell, routeTimeCell, routeStationCell);
        routesTable.append(newCellRow);
    })
}

function addNewSelect(containerName) {
    let container = document.getElementById(containerName);
    let clone = document.getElementById("routes_stations_div").cloneNode(true);
    let nextSelectIndex = container.children.length;
    let select = clone.children[0]
    select.name = "timetableItems[" + nextSelectIndex + "].station";
    let dateinput = clone.children[1].children[0];
    dateinput.name = "timetableItems[" + nextSelectIndex + "].departureDate";
    let timeinput = clone.children[2].children[0];
    timeinput.name = "timetableItems[" + nextSelectIndex + "].departureTime";
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

function setEventListeners() {
    setEventListenerOnProfileMenuItems();
}

// mark selected menu item with active color
function setEventListenerOnProfileMenuItems() {
    let profileMenuItemsList = document.querySelectorAll(".profile-menu-item p");
    let profileContentItemsList = document.querySelectorAll(".profile-content-item");
    profileMenuItemsList.forEach(item =>
        item.addEventListener("click", (e) => {
            removeActiveClassMenuItems(profileMenuItemsList, profileContentItemsList);
            e.target.classList.add("active");
            let contentToShow = document.getElementById("profile-content-item-" + e.target.innerText.toLowerCase())
            contentToShow.classList.remove("hidden");
        }));
}

function removeActiveClassMenuItems(profileMenuItemsList, profileContentItemsList) {
    profileMenuItemsList.forEach(item =>
        item.classList.remove("active"))
    profileContentItemsList.forEach(item => item
        .classList.add("hidden"))
}

function fillSeatInfoContainerWithText(seatNumber) {
    let seatInfoContainer = document.getElementById("selected-seat-info");
    seatInfoContainer.innerText = "";
    let seatNumberNode = document.createElement("p");
    seatNumberNode.innerText = "Selected seat " + seatNumber;
    let ticketPriceNode = document.createElement("p");
    ticketPriceNode.innerText = "Price $100";
    seatInfoContainer.append(seatNumberNode, ticketPriceNode);
}

function selectSeat(element, seatNumber) {
    let seatBtnList = document.querySelectorAll(".seat-btn");
    seatBtnList.forEach(btn => btn.classList.remove("seat-selected"))
    element.classList.add("seat-selected")
    fillSeatInfoContainerWithText(seatNumber);
    document.getElementsByClassName("purchase-section-next-btn")[0].disabled = false;
}

function goToSection(sectionId) {
    let sectionsList = document.querySelectorAll(".purchase-section")
    sectionsList.forEach(section =>
        section.classList.remove("active"))
    document.getElementById(sectionId).classList.add("active");
    let tabsList = document.querySelectorAll(".purchase-tab")
    tabsList.forEach(tab =>
        tab.classList.remove("active"))
    let tabId = sectionId.replace("section", "tab");
    console.log(tabId)
    document.getElementById(tabId).classList.add("active");
}

function checkPassengerDataValidity(seatId, firstName, lastName, passportNumber, dateOfBirth) {
    let errorMessage = "";
    if (!seatId) errorMessage += "Please select a seat. \n";
    if (!firstName) errorMessage += "Please fill in your first name. \n";
    if (!lastName) errorMessage += "Please fill in your last name. \n";
    if (!passportNumber) errorMessage += "Please fill in your passport number. \n";
    if (!dateOfBirth) errorMessage += "Please fill in your date of birth. \n";
    return errorMessage;
}

function addPassengerDataErrorMessage(errorMessage) {
    let container = document.getElementById("passanger-data-error-message-box");
    container.innerText = "";
    container.innerText = errorMessage;
}

async function purchaseTicket() {
    let seatId = document.getElementsByClassName("seat-selected")[0].dataset.id;
    let firstName = document.getElementById("passengerFirstname").value;
    let lastName = document.getElementById("passengerLastname").value;
    let passportNumber = document.getElementById("passengerPassportNumber").value;
    let dateOfBirth = document.getElementById("passengerDateOfBirth").value;
    let tripStartStationId = document.getElementById("startTripStation").dataset.id;
    let tripEndStationId = document.getElementById("endTripStation").dataset.id;
    let routeId = document.getElementById("startTripStation").dataset.routeid;
    let errorMessage = checkPassengerDataValidity(seatId, firstName, lastName, passportNumber, dateOfBirth);
    if (errorMessage != "") {
        addPassengerDataErrorMessage(errorMessage);
        return;
    }
    let ticket = {
        price: 100, //todo make dynamic
        seat: {
            id: seatId
        },
        routeId,
        tripStartStationId,
        tripEndStationId,
        passenger: {
            firstName,
            lastName,
            passportNumber,
            dateOfBirth,
        }
    }
    postData('/user/purchase', ticket)
        .then((result) => {
            console.log(result);
        });
}

async function postData(url = '', data = {}) {
    console.log(url, data)
    const response = await fetch(url, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    return await response.json();
}

setEventListeners();