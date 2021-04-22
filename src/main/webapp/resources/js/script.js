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

function openRoutesSection(trainNumber, trainId, stations) {
    document.getElementById("schedule-section").classList.add("hidden");
    document.getElementById("routes-section").classList.remove("hidden");
    // insert saved values into fields
    document.getElementById("routes_title").innerText = "Routes for train  " + trainNumber;
    document.getElementById("routes_train_id").value = trainId;
    let select = document.getElementById("routes_stations");
    let values = stations.split(',');
    for (let i = 0; i < select.options.length; i++) {
        select.options[i].selected = values.indexOf(select.options[i].text) >= 0;
    }
}
function addNewSelect(){
    let container = document.getElementById("select_container");
    let clone = document.getElementById("routes_stations_div").cloneNode(true);
    let nextSelectIndex = container.children.length;
    let select = clone.children[0]
    select.name = "timetableItems["+ nextSelectIndex+"].station";
    let timeinput = clone.children[1].children[0];
    timeinput.name = "timetableItems["+ nextSelectIndex+"].departureTime";
    container.appendChild(clone);
}

// async function sendRequestAddRoute(){
//     const rawResponse = await fetch('/add/route', {
//         method: 'POST',
//         body: '{ "train": "13","station": "2","departureTime": "2021-04-23T23:48"}'
//     });
//     const content = await rawResponse.json();
//     console.log(content);
//
// }