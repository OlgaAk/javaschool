package io.github.olgaak.util;

import io.github.olgaak.dto.TimetableItemDto;
import io.github.olgaak.entity.RoutePlan;
import io.github.olgaak.entity.TimetableItem;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.HashSet;


public class RoutePlanDtoConverterTest extends TestCase {

    @Test
    public void testGetDurationMilli() throws ParseException {
        RoutePlan routePlan = new RoutePlan();
        TimetableItem item1 = new TimetableItem();
        SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss");
        item1.setDepartureTime(formatter.parse("12:30:00"));
        item1.setOrder(0);
        TimetableItem item2 = new TimetableItem();
        item2.setOrder(1);
        item2.setArrivalTime(formatter.parse(("17:20:00")));
        routePlan.setTimetableItems(new HashSet<>(Arrays.asList(item2, item1)));
        long expected = 17400000;
        long duration = RoutePlanDtoConverter.getDurationMilli(routePlan);
        Assert.assertEquals(expected , duration);
    }


}