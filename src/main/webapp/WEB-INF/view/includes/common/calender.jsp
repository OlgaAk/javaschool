<div class="color-calendar glass color-calendar--large" style="--cal-color-primary:#ffd335; --cal-header-background-color:#ffde11;">
  <div class="calendar__header">
<%--    <div class="calendar__arrow calendar__arrow-prev"><div class="calendar__arrow-inner"></div></div>--%>
    <div class="calendar__monthyear">
      <span class="calendar__month" style="opacity: 1;">June</span>&nbsp;
      <span class="calendar__year" style="opacity: 1;">2021</span>
    </div>
<%--    <div class="calendar__arrow calendar__arrow-next"><div class="calendar__arrow-inner"></div></div>--%>
  </div>
  <div class="calendar__body">
    <div class="calendar__weekdays">
      <div class="calendar__weekday">S</div>

      <div class="calendar__weekday">M</div>

      <div class="calendar__weekday">T</div>

      <div class="calendar__weekday">W</div>

      <div class="calendar__weekday">T</div>

      <div class="calendar__weekday">F</div>

      <div class="calendar__weekday">S</div>
    </div>
    <div class="calendar__days">
      <div class="calendar__day calendar__day-other">30</div>

      <div class="calendar__day calendar__day-other">31</div>

      <c:forEach begin="1" end="30" var="val">
        <div class="calendar__day calendar__day-active calendar__day-no-event ${train.routePlan.checkIfRouteDate(val) ? "calendar__day-selected" : "" }">
          <span class="calendar__day-text">${val}</span>
          <div class="calendar__day-bullet"></div>
          <div class="calendar__day-box"></div>
        </div>
      </c:forEach>

      <div class="calendar__day calendar__day-other">1</div>

      <div class="calendar__day calendar__day-other">2</div>

      <div class="calendar__day calendar__day-other">3</div>

      <div class="calendar__day calendar__day-other">4</div>

      <div class="calendar__day calendar__day-other">5</div>

      <div class="calendar__day calendar__day-other">6</div>

      <div class="calendar__day calendar__day-other">7</div>

      <div class="calendar__day calendar__day-other">8</div>

      <div class="calendar__day calendar__day-other">9</div>

      <div class="calendar__day calendar__day-other">10</div>
    </div>
    <div class="calendar__picker" style="visibility: hidden; opacity: 0;">
      <div class="calendar__picker-month">
        <div class="calendar__picker-month-option" data-value="0">Jan</div>
        <div class="calendar__picker-month-option" data-value="1">Feb</div>
        <div class="calendar__picker-month-option" data-value="2">Mar</div>
        <div class="calendar__picker-month-option" data-value="3">Apr</div>
        <div class="calendar__picker-month-option" data-value="4">May</div>
        <div class="calendar__picker-month-option calendar__picker-month-today calendar__picker-month-selected" data-value="5">Jun</div>
        <div class="calendar__picker-month-option" data-value="6">Jul</div>
        <div class="calendar__picker-month-option" data-value="7">Aug</div>
        <div class="calendar__picker-month-option" data-value="8">Sep</div>
        <div class="calendar__picker-month-option" data-value="9">Oct</div>
        <div class="calendar__picker-month-option" data-value="10">Nov</div>
        <div class="calendar__picker-month-option" data-value="11">Dec</div>
      </div>
      <div class="calendar__picker-year">
        <div class="calendar__picker-year-option" data-value="0">2017</div>
        <div class="calendar__picker-year-option" data-value="1">2018</div>
        <div class="calendar__picker-year-option" data-value="2">2019</div>
        <div class="calendar__picker-year-option" data-value="3">2020</div>
        <div class="calendar__picker-year-option calendar__picker-year-today calendar__picker-year-selected" data-value="4">2021</div>
        <div class="calendar__picker-year-option" data-value="5">2022</div>
        <div class="calendar__picker-year-option" data-value="6">2023</div>
        <div class="calendar__picker-year-option" data-value="7">2024</div>
        <div class="calendar__picker-year-option" data-value="8">2025</div>
        <div class="calendar__picker-year-option" data-value="9">2026</div>
        <div class="calendar__picker-year-option" data-value="10">2027</div>
        <div class="calendar__picker-year-option" data-value="11">2028</div>
        <div class="calendar__picker-year-arrow calendar__picker-year-arrow-left">
          <div class="chevron-thin chevron-thin-left"></div>
        </div>
        <div class="calendar__picker-year-arrow calendar__picker-year-arrow-right">
          <div class="chevron-thin chevron-thin-right"></div>
        </div>
      </div>
    </div>
  </div>
</div>