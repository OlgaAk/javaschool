const CONFIGURATION = {
    "capabilities": {"input": true, "autocomplete": true, "directions": true, "distanceMatrix": true, "details": true},
    "locations": [
        {"title": "Moscow", "coords": {"lat": 55.75598127252937, "lng": 37.6173375509262}},
        {"title": "St Petersburg", "coords": {"lat": 59.937500, "lng": 30.308611}},
        {"title": "Bologoe", "coords": {"lat": 57.5215, "lng": 34.0425}},
        {"title": "Tver", "coords": {"lat": 56.5128, "lng": 35.5518}},
        {"title": "Samara", "coords": {"lat": 53.11, "lng": 50.07}},
        {"title": "Kazan", "coords": {"lat": 55.47, "lng": 49.06}},
        {"title": "Astrakhan", "coords": {"lat": 46.20, "lng": 48.02}},
        {"title": "Vladimir", "coords": {"lat": 56.08, "lng": 40.25}},
        {"title": "Kaliningrad", "coords": {"lat": 54.43, "lng": 20.30}},
        {"title": "Riga", "coords": {"lat": 56.56, "lng": 24.06}},
        {"title": "Nizhniy Novgorod", "coords": {"lat": 56.19, "lng": 44.00}},
        {"title": "Pskov", "coords": {"lat": 57.49, "lng": 28.20}},

    ],
    "mapOptions": {mapTypeControl: false, streetViewControl: false, fullscreenControl: false, zoom: 4},
    "mapsApiKey": "AIzaSyA0J1HRUvryzFOt2jn0Xd9uujElVEOsRFs"
};


function initMap() {
    new LocatorPlus(CONFIGURATION);

}