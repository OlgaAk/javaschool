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

function createRouteTable(route, index, routesTable) {
    let routesTableHeader = document.getElementById("routes-table-header").cloneNode(true);
    routesTableHeader.style.display = "grid";
    routesTableHeader.removeAttribute("id");
    let routeTitle = document.createElement("h5");
    routeTitle.innerText = "Route " + (index + 1);
    routeTitle.classList.add("editable");
    routeTitle.addEventListener("click", ()=> handleClickOnRouteTitle(route))
    routesTable.append(routeTitle, routesTableHeader);
    createRouteTableRows(route, routesTable)
}

function handleClickOnRouteTitle(route){
    console.log(route)
}

function hideScheduleShowRoutes(trainNumber, trainId) {
    document.getElementById("schedule-section").classList.add("hidden");
    document.getElementById("routes-section").classList.remove("hidden");
    document.getElementById("routes_title").innerText = "Routes for train  " + trainNumber;
    document.getElementById("routes_train_id").value = trainId;
}

async function openRoutesSection(trainNumber, trainId) {
    hideScheduleShowRoutes(trainNumber, trainId);
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

function createRouteTableRows(route, routesTable) {
    route.timetableItems.forEach(timeTable => {
        let newCellRow = document.createElement("div");
        newCellRow.classList.add("table-row", "table-columns-3");
        let routeDateCell = document.createElement("span");
        routeDateCell.className = "table-cell";
        console.log(timeTable.departureDate)
        let date = new Date(timeTable.departureDate);
        routeDateCell.innerText = date.toLocaleString().split(",")[0];
        let routeTimeCell = document.createElement("span");
        routeTimeCell.className = "table-cell";
        let time = new Date(timeTable.departureTime).toLocaleTimeString().substring(0,5);
        routeTimeCell.innerText = time;
        let routeStationCell = document.createElement("span");
        routeStationCell.className = "table-cell";
        routeStationCell.innerText = timeTable.station.name;
        newCellRow.append(routeDateCell, routeTimeCell, routeStationCell);
        routesTable.append(newCellRow);
    })
}

function addNewSelect() {
    let container = document.getElementById("select_container");
    let clone = document.getElementById("routes_stations_div").cloneNode(true);
    let nextSelectIndex = container.children.length;
    let select = clone.children[0]
    select.name = "timetableItems[" + nextSelectIndex + "].station";
    let timeinput = clone.children[1].children[0];
    timeinput.name = "timetableItems[" + nextSelectIndex + "].departureTime";
    container.appendChild(clone);
}

//get info about a train routes with REST
async function fetchRoutes(trainId) {
    let response = await fetch("/routes/" + trainId);
    if (response.ok) {
        let json = await response.json();
        console.log(json)
        return json;
    } else {
        console.log("Ошибка HTTP: " + response.status);
        return null;
    }
}

function setEventListeners(){
    setEventListenerOnProfileMenuItems();
}

// mark selected menu item with active color
function setEventListenerOnProfileMenuItems(){
    let profileMenuItemsList = document.querySelectorAll(".profile-menu-item p");
    profileMenuItemsList.forEach(item=>
        item.addEventListener("click", (e)=> {
            removeActiveClassMenuItems(profileMenuItemsList);
            e.target.classList.add("active");
        }));
}

function removeActiveClassMenuItems(profileMenuItemsList){
    profileMenuItemsList.forEach(item=>
        item.classList.remove("active"))
}

setEventListeners();