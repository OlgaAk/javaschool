package io.github.olgaak.dto;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

public class DatePickerMaxMinDto {
    private String min;
    private String max;

    public DatePickerMaxMinDto() {
        this.min = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        this.max = new SimpleDateFormat("yyyy-MM-dd")
                .format(java.sql.Date.valueOf(LocalDate.now().plusMonths(1)));
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }
}
