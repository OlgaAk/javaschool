package io.github.olgaak.util;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class GeoLocationHelperTest extends TestCase {

    @Test
    public void testDistance() {
        GeoLocationHelper helper = new GeoLocationHelper();
        double distance = helper.distance(55.75598127252937, 37.6173375509262, 59.937500, 30.308611);
        double expected = 705.6;
        Assert.assertEquals(expected, distance, 72); // tofix delta is too big
    }
}