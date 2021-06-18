
    'use strict';

    /** Hide a DOM element. */
    function hideElement(el) {
    el.style.display = 'none';
}

    /** Show a DOM element that has been hidden. */
    function showElement(el) {
    el.style.display = 'block';
}

    /**
    * Defines an instance of the Locator+ solution, to be instantiated
    * when the Maps library is loaded.
    */
    function LocatorPlus(configuration) {
    const locator = this;

    locator.locations = configuration.locations || [];
    locator.capabilities = configuration.capabilities || {};

    const mapEl = document.getElementById('map');
    locator.panelListEl = document.getElementById('locations-panel-list');
    const sectionNameEl =
    document.getElementById('location-results-section-name');
    const resultsContainerEl = document.getElementById('location-results-list');

    const itemsTemplate = Handlebars.compile(
    document.getElementById('locator-result-items-tmpl').innerHTML);

    locator.searchLocation = null;
    locator.searchLocationMarker = null;
    locator.selectedLocationIdx = null;
    locator.userCountry = null;

    // Initialize the map -------------------------------------------------------
    const mapOptions = configuration.mapOptions;
    locator.map = new google.maps.Map(mapEl, {
    fullscreenControl: mapOptions.fullscreenControl,
    zoomControl: mapOptions.zoomControl,
    streetViewControl: mapOptions.streetViewControl,
    mapTypeControl: mapOptions.mapTypeControl,
    mapTypeControlOptions: {
    position: google.maps.ControlPosition.TOP_RIGHT,
},
});

    // Store selection.
    const selectResultItem = function(locationIdx, panToMarker) {
    locator.selectedLocationIdx = locationIdx;
    for (let locationElem of resultsContainerEl.children) {
    locationElem.classList.remove('selected');
    if (getResultIndex(locationElem) === locator.selectedLocationIdx) {
    locationElem.classList.add('selected');
}
}
    if (panToMarker && (locationIdx != null)) {
    locator.map.panTo(locator.locations[locationIdx].coords);
}
};

    // Create a marker for each location.
    const markers = locator.locations.map(function(location, index) {
    const marker = new google.maps.Marker({
    position: location.coords,
    map: locator.map,
    title: location.title,
});
    marker.addListener('click', function() {
    selectResultItem(index, false);
});
    return marker;
});

    // Fit map to marker bounds.
    locator.updateBounds = function() {
    const bounds = new google.maps.LatLngBounds();
    if (locator.searchLocationMarker) {
    bounds.extend(locator.searchLocationMarker.getPosition());
}
    for (let i = 0; i < markers.length; i++) {
    bounds.extend(markers[i].getPosition());
}
    locator.map.fitBounds(bounds);
};
    locator.updateBounds();

    // Get the distance of a store location to the user's location,
    // used in sorting the list.
    const getLocationDistance = function(location) {
    if (!locator.searchLocation) return null;

    // Use travel distance if available (from Distance Matrix).
    if (location.travelDistanceValue != null) {
    return location.travelDistanceValue;
}

    // Fall back to straight-line distance.
    return google.maps.geometry.spherical.computeDistanceBetween(
    new google.maps.LatLng(location.coords),
    locator.searchLocation.location);
};

    // Render the results list --------------------------------------------------
    const getResultIndex = function(elem) {
    return parseInt(elem.getAttribute('data-location-index'));
};

    locator.renderResultsList = function() {
    let locations = locator.locations.slice();
    for (let i = 0; i < locations.length; i++) {
    locations[i].index = i;
}
    if (locator.searchLocation) {
    sectionNameEl.textContent =
    'Nearest locations (' + locations.length + ')';
    locations.sort(function(a, b) {
    return getLocationDistance(a) - getLocationDistance(b);
});
} else {
    sectionNameEl.textContent = `All locations (${locations.length})`;
}
    const resultItemContext = {
    locations: locations,
    showDirectionsButton: !!locator.searchLocation
};
    resultsContainerEl.innerHTML = itemsTemplate(resultItemContext);
    for (let item of resultsContainerEl.children) {
    const resultIndex = getResultIndex(item);
    if (resultIndex === locator.selectedLocationIdx) {
    item.classList.add('selected');
}

    const resultSelectionHandler = function() {
    if (resultIndex !== locator.selectedLocationIdx) {
    locator.clearDirections();
}
    selectResultItem(resultIndex, true);
};

    // Clicking anywhere on the item selects this location.
    // Additionally, create a button element to make this behavior
    // accessible under tab navigation.
    item.addEventListener('click', resultSelectionHandler);
    item.querySelector('.select-location')
    .addEventListener('click', function(e) {
    resultSelectionHandler();
    e.stopPropagation();
});



    if (resultItemContext.showDirectionsButton) {
    item.querySelector('.show-directions')
    .addEventListener('click', function(e) {
    selectResultItem(resultIndex, false);
    locator.updateDirections();
    e.stopPropagation();
});
}
}
};

    // Optional capability initialization --------------------------------------

    initializeDistanceMatrix(locator);
    initializeDirections(locator);


    // Initial render of results -----------------------------------------------
    locator.renderResultsList();
}



    /** Initialize Distance Matrix for the locator. */
    function initializeDistanceMatrix(locator) {
    const distanceMatrixService = new google.maps.DistanceMatrixService();

    // Annotate travel times to the selected location using Distance Matrix.
    locator.updateTravelTimes = function() {
    if (!locator.searchLocation) return;

    const units = (locator.userCountry === 'USA') ?
    google.maps.UnitSystem.IMPERIAL :
    google.maps.UnitSystem.METRIC;
    const request = {
    origins: [locator.searchLocation.location],
    destinations: locator.locations.map(function(x) {
    return x.coords;
}),
    travelMode: google.maps.TravelMode.DRIVING,
    unitSystem: units,
};
    const callback = function(response, status) {
    if (status === 'OK') {
    const distances = response.rows[0].elements;
    for (let i = 0; i < distances.length; i++) {
    const distResult = distances[i];
    let travelDistanceText, travelDistanceValue;
    if (distResult.status === 'OK') {
    travelDistanceText = distResult.distance.text;
    travelDistanceValue = distResult.distance.value;
}
    const location = locator.locations[i];
    location.travelDistanceText = travelDistanceText;
    location.travelDistanceValue = travelDistanceValue;
}

    // Re-render the results list, in case the ordering has changed.
    locator.renderResultsList();
}
};
    distanceMatrixService.getDistanceMatrix(request, callback);
};
}

    /** Initialize Directions service for the locator. */
    function initializeDirections(locator) {
    const directionsCache = new Map();
    const directionsService = new google.maps.DirectionsService();
    const directionsRenderer = new google.maps.DirectionsRenderer({
    suppressMarkers: true,
});

    // Update directions displayed from the search location to
    // the selected location on the map.
    locator.updateDirections = function() {
    if (!locator.searchLocation || (locator.selectedLocationIdx == null)) {
    return;
}
    const cacheKey = JSON.stringify(
    [locator.searchLocation.location, locator.selectedLocationIdx]);
    if (directionsCache.has(cacheKey)) {
    const directions = directionsCache.get(cacheKey);
    directionsRenderer.setMap(locator.map);
    directionsRenderer.setDirections(directions);
    return;
}
    const request = {
    origin: locator.searchLocation.location,
    destination: locator.locations[locator.selectedLocationIdx].coords,
    travelMode: google.maps.TravelMode.DRIVING
};
    directionsService.route(request, function(response, status) {
    if (status === 'OK') {
    directionsRenderer.setMap(locator.map);
    directionsRenderer.setDirections(response);
    directionsCache.set(cacheKey, response);
}
});
};

    locator.clearDirections = function() {
    directionsRenderer.setMap(null);
};
}

