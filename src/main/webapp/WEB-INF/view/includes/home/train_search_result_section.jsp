<div id="train-search-result-container" class="train-search-result-container" >
    <div class="train-search-result-item" style="display: none">
        <div class="train-search-result-item-left">
            <div class="train-search-result-item-row">
                <div class="train-search-result-item-time">${route.startTripTime}</div>
                <div class="train-search-result-item-station">${route.startTripStation}</div>
            </div>
            <div class="train-search-result-item-row">
                <div class="train-search-result-item-time">${route.endTripTime}</div>
                <div class="train-search-result-item-station">${route.endTripStation}</div>
            </div>
            <div class="train-search-result-item-row">
                <div class="train-search-result-item-duration">${route.tripDuration}</div>
                <div class="train-search-result-item-change">${route.changeType}</div>
            </div>
        </div>
        <div class="train-search-result-item-right">
            <div class="train-search-result-item-price">$${route.price}</div>
            <a href="/user/purchase/${route.id}"><button class="buy-ticket">BUY</button></a>
        </div>
    </div>

</div>
