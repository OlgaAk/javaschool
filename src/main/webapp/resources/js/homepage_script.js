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

async function submitSearchForm() {
    let departureStationInputValue = document.getElementById("departure-station-input").dataset.stationid;
    let arrivalStationInputValue = document.getElementById("arrival-station-input").dataset.stationid;
    let departureDate = document.getElementById("departure-date-input").value;
    if (departureStationInputValue != "" && arrivalStationInputValue != "" && departureDate != "") {
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
    } else {
        alert("Invalid data")
    }
}


function setEventListenerOnSearchButton() {
    let btn = document.getElementById("train-search-btn");
    btn.addEventListener("click", submitSearchForm)
}

setEventListenerOnSearchButton();
