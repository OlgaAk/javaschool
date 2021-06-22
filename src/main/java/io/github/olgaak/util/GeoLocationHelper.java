package io.github.olgaak.util;

import io.github.olgaak.dto.RouteDto;
import io.github.olgaak.dto.StationDto;


public class GeoLocationHelper {

    public final static double AVERAGE_RADIUS_OF_EARTH = 6371;

    public final static double KM_DOLLAR_PRICE = 0.04;

    public int countDistanceByCoords(double userLat, double userLng, double venueLat, double venueLng) {
        double latDistance = Math.toRadians(userLat - venueLat);
        double lngDistance = Math.toRadians(userLng - venueLng);
        double a = (Math.sin(latDistance / 2) * Math.sin(latDistance / 2)) +
                (Math.cos(Math.toRadians(userLat))) *
                        (Math.cos(Math.toRadians(venueLat))) *
                        (Math.sin(lngDistance / 2)) *
                        (Math.sin(lngDistance / 2));
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return (int) (Math.round(AVERAGE_RADIUS_OF_EARTH * c));
    }

    public double countPrice(RouteDto route) {
        StationDto s1 = route.getRoutePlan().getStartTripStation();
        StationDto s2 = route.getRoutePlan().getEndTripStation();
        long distance = countDistanceByCoords(s1.getLatitude(), s1.getLongitude(), s2.getLatitude(), s2.getLongitude());
        return distance*KM_DOLLAR_PRICE;
    }
}
