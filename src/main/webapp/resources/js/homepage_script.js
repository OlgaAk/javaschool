function showAllDepartureStationsList(stations){
    let input = document.getElementById("departure-station-input");
    let popup = document.getElementById("departure-station-popup");
    popup.innerText = "";
    stations.forEach(s=> {
            let p = document.createElement("p");
            p.textContent = s.name;
            p.dataset.stationId = s.id;
            p.addEventListener("click", ()=> {
                input.value = p.textContent;
                popup.style.display = "none";
            })
            popup.append(p);
        }
    )
    popup.style.display = "block";

}
