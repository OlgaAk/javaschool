<div class="train-search-container">
    <form method="get" action="/train-query">
        <div class="train-search-input-groups-container">
            <div class="train-search-input-groups-box">
                <div class="train-search-input-group">
                    <label>From</label>
                    <input name="departure_station" type="search" required>
                </div>
                <div class="train-search-input-group">
                    <label>To</label>
                    <input name="arrival_station" type="search" required>
                </div>
                <div class="train-search-input-group">
                    <label>Date</label>
                    <input name="departure_date" type="date" required>
                </div>
            </div>
        </div>
        <div class="train-search-btn-container ">
            <button class="train-search-btn" type="submit">Search</button>
        </div>

    </form>
</div>