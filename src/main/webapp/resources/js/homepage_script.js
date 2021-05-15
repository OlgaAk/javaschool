function showAllDepartureStationsList(stations) {
    let input = document.getElementById("departure-station-input");
    let popup = document.getElementById("departure-station-popup");
    popup.innerText = "";
    fillPopupWithStations(stations, input, popup);
    popup.style.display = "block";
}


function fillPopupWithStations(stations, input, popup) {
    stations.forEach(s => {
            let p = document.createElement("p");
            p.textContent = s.name;
            p.dataset.stationid = s.id;
            p.addEventListener("click", () => {
                input.value = p.textContent;
                input.dataset.stationid = p.dataset.stationid;
                popup.style.display = "none";
            })
            popup.append(p);
        }
    )
}

function showAllArrivalStationsList(stations) {
    let input = document.getElementById("arrival-station-input");
    let popup = document.getElementById("arrival-station-popup");
    popup.innerText = "";
    fillPopupWithStations(stations, input, popup);
    popup.style.display = "block";
}

async function getRoutesByQuery(departureStationInputValue, arrivalStationInputValue, departureDate) {
    let response = await fetch("/train-query?departure_station="
        + departureStationInputValue + "&arrival_station="
        + arrivalStationInputValue + "&departure_date=" + departureDate);
    if (response.ok) {
        let json = await response.json();
        console.log(json)
        return json;
    } else {
        console.log("Ошибка HTTP: " + response.status);
        return null;
    }
}

function submitSearchForm() {
    let departureStationInputValue = document.getElementById("departure-station-input").dataset.stationid;
    let arrivalStationInputValue = document.getElementById("arrival-station-input").dataset.stationid;
    let departureDate = document.getElementById("departure-date-input").value;
    if (departureStationInputValue != "" && arrivalStationInputValue != "" && departureDate != "") {
        let routes = getRoutesByQuery(departureStationInputValue, arrivalStationInputValue, departureDate);
        if (routes != null) {
            routes.forEach(route => createSearchResult(route))
        } else alert("No routes found")
    } else {
        alert("Invalid data")
    }
}

function createSearchResult(route) {
    let container = document.getElementById("train-search-result-container");
    let resultItem = container.children[0].cloneNode();
    resultItem.style.display = "block";
    resultItem.querySelector(".train-search-result-item-time").innerHTML = route.startTripTime;
    resultItem.querySelector(".train-search-result-item-station").innerHTML = route.startTripStation;
    resultItem.querySelectorAll(".train-search-result-item-time")[1].innerHTML = route.endTripTime;
    resultItem.querySelectorAll(".train-search-result-item-station")[1].innerHTML = route.endTripStation;
    resultItem.querySelector(".train-search-result-item-duration").innerHTML = route.tripDuration;
    resultItem.querySelector(".train-search-result-item-change").innerHTML = route.changeType;
    resultItem.querySelector(".train-search-result-item-price").innerHTML = route.price;
    resultItem.querySelector("a").href = "/user/purchase/" + route.id;
    container.appendChild(resultItem);
}



function setEventListenerOnSearchButton() {
    let btn = document.getElementById("train-search-btn");
    btn.addEventListener("click", submitSearchForm);
    let popup = document.getElementById("departure-station-popup");
    let input = document.getElementById("departure-station-input");
    let input2 = document.getElementById("arrival-station-input");
    let popup2 = document.getElementById("arrival-station-popup");
    document.body.addEventListener("click", (event) => {
        if (event.target != popup && event.target != input && popup.style.display=== "block")   popup.style.display = "none";
        if (event.target != popup2 && event.target != input2 && popup2.style.display=== "block")   popup2.style.display = "none"
    })

}

setEventListenerOnSearchButton();
