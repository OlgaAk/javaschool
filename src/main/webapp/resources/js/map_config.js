const CONFIGURATION = {
    "capabilities": {"input": true, "autocomplete": true, "directions": true, "distanceMatrix": true, "details": true},
    "locations": [
        {"title": "Moskau", "coords": {"lat": 55.75598127252937, "lng": 37.6173375509262}},
        {"title": "Saint Petersburg", "coords": {"lat": 59.937500, "lng": 30.308611}},
        {"title": "Bologoe", "coords": {"lat": 57.5215, "lng": 34.0425}},
        {"title": "Tver", "coords": {"lat": 56.5128, "lng": 35.5518}},

    ],
    "mapOptions": {mapTypeControl: false, streetViewControl: false, fullscreenControl: false},
    "mapsApiKey": "AIzaSyA0J1HRUvryzFOt2jn0Xd9uujElVEOsRFs"
};

function initMap() {
    new LocatorPlus(CONFIGURATION);

}