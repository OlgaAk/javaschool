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

function openCancelTicketPopUp(ticketId) {
    document.getElementById("ticket-edit-popup-container").classList.remove("hidden");
    document.querySelector("#ticket-edit-popup a").href = "/user/delete/ticket/" + ticketId

}

function closeEditPopUp(container) {
    document.getElementById(container).classList.add("hidden");
}

setEventListeners();