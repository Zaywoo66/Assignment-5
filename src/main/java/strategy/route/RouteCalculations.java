package strategy.route;

import strategy.model.Location;
import strategy.model.RoutePlan;
import strategy.model.RouteRequest;

import java.time.Duration;
import java.util.List;

final class RouteCalculations {
    private static final double EARTH_RADIUS_KM = 6_371.0;

    private RouteCalculations() {
    }

    static RoutePlan createPlan(
            RouteRequest request,
            String strategyName,
            double distanceFactor,
            double averageSpeedKmh,
            String instruction) {
        double directDistanceKm = distanceBetween(request.origin(), request.destination());
        double routeDistanceKm = directDistanceKm * distanceFactor;
        long durationSeconds = Math.round(routeDistanceKm / averageSpeedKmh * 3_600);

        return new RoutePlan(
                strategyName,
                Math.round(routeDistanceKm * 100.0) / 100.0,
                Duration.ofSeconds(durationSeconds),
                List.of(instruction.formatted(request.origin().name(), request.destination().name())));
    }

    private static double distanceBetween(Location origin, Location destination) {
        double originLatitude = Math.toRadians(origin.latitude());
        double destinationLatitude = Math.toRadians(destination.latitude());
        double latitudeDifference = destinationLatitude - originLatitude;
        double longitudeDifference = Math.toRadians(destination.longitude() - origin.longitude());

        double haversine = Math.pow(Math.sin(latitudeDifference / 2), 2)
                + Math.cos(originLatitude) * Math.cos(destinationLatitude)
                * Math.pow(Math.sin(longitudeDifference / 2), 2);
        return 2 * EARTH_RADIUS_KM * Math.asin(Math.sqrt(haversine));
    }
}
