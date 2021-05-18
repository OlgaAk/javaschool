package io.github.olgaak.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeConverter {
    public static String parseDateToString(Date date) {
        DateFormat format = new SimpleDateFormat("dd.MM.yyyy HH:mm");
        String dateString = "";
        dateString = format.format(date);
        return dateString;
    }
}
