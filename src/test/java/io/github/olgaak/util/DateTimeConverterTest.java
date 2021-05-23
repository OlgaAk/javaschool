package io.github.olgaak.util;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.security.enterprise.authentication.mechanism.http.RememberMe;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateTimeConverterTest extends TestCase {

    @Test
    public void testParseDateToString() {
        Calendar calendar = new GregorianCalendar(2021, 4 , 1);
        Date date = calendar.getTime();
        String result = DateTimeConverter.parseDateToString(date);
        Assert.assertEquals("01.05.2021 00:00", result);
        String result2 = DateTimeConverter.parseDateToString(date, "HH:mm");
        Assert.assertEquals("00:00" , result2);
    }

    @Test
    public void testParseDateToStringWithPattern() {
        Calendar calendar = new GregorianCalendar(2021, 4 , 1);
        Date date = calendar.getTime();
        String result = DateTimeConverter.parseDateToString(date, "HH:mm");
        Assert.assertEquals("00:00" , result);
    }

}