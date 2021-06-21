function fillSeatInfoContainerWithText(seatNumber) {
    let seatInfoContainer = document.getElementById("selected-seat-info");
    seatInfoContainer.innerText = "";
    let seatNumberNode = document.createElement("p");
    seatNumberNode.innerText = "Selected seat " + seatNumber;
    let ticketPriceNode = document.createElement("p");
    let price = document.getElementById("confirmation-passenger-price").dataset.price;
    ticketPriceNode.innerText = "Price $"+price;
    seatInfoContainer.append(seatNumberNode, ticketPriceNode);
}

function selectSeat(element, seatNumber) {
    if (element.classList.contains("seat-occupied")) return;
    let seatBtnList = document.querySelectorAll(".seat-btn");
    seatBtnList.forEach(btn => btn.classList.remove("seat-selected"))
    element.classList.add("seat-selected")
    fillSeatInfoContainerWithText(seatNumber);
    document.getElementsByClassName("purchase-section-next-btn")[0].disabled = false;
}

function fillConfirmInformation() {
    addPassengerDataErrorMessage("");
    let seatNumber = document.getElementsByClassName("seat-selected")[0].dataset.number;
    let firstName = document.getElementById("passengerFirstname").value;
    let lastName = document.getElementById("passengerLastname").value;
    let dateOfBirth = document.getElementById("passengerDateOfBirth").value;
    let passportNumber = document.getElementById("passengerPassportNumber").value;
    document.getElementById("confirmation-passenger-details").innerHTML =
        firstName + " " + lastName + "<br>" + dateOfBirth + "<br>" + passportNumber;
    document.getElementById("confirmation-passenger-seat").innerHTML = "Seat: " + seatNumber;
    let errorMessage = checkPassengerDataValidity(seatNumber, firstName, lastName, passportNumber, dateOfBirth);
    if (errorMessage != "") {
        addPassengerDataErrorMessage(errorMessage);
    }
}

function goToSection(sectionId) {
    if (sectionId == "purchase-section-confirm") fillConfirmInformation();
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
    let container = document.getElementById("passenger-data-error-message-box");
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
    let price = document.getElementById("confirmation-passenger-price").dataset.price;
    let routeId = document.getElementById("startTripStation").dataset.routeid;
    let errorMessage = checkPassengerDataValidity(seatId, firstName, lastName, passportNumber, dateOfBirth);
    if (errorMessage != "") {
        addPassengerDataErrorMessage(errorMessage);
        return;
    }
    let ticket = {
        price,
        seatId,
        routeId,
        startStation: {
            id: tripStartStationId
        },
        endStation: {
            id: tripEndStationId
        },
        passenger: {
            firstName,
            lastName,
            passportNumber,
            dateOfBirth,
        }
    }
    const result = await postData('/user/purchase', ticket)
    console.log(result);
    if (result.redirected == true) window.location.href = result.url;
    if (result.status == 500) alert("Server error 500")
}

async function postData(url = '', data = {}) {
    console.log(url, data)
    const response = await fetch(url, {
        method: 'POST',
        redirect: 'follow',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(data)
    });
    return response;
}
