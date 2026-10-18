package strategy.route;

import strategy.api.RouteStrategy;
import strategy.model.RoutePlan;
import strategy.model.RouteRequest;

import java.util.Objects;

public final class CarStrategy implements RouteStrategy {
    private static final double ROAD_DISTANCE_FACTOR = 1.25;
    private static final double AVERAGE_SPEED_KMH = 55.0;

    @Override
    public RoutePlan plan(RouteRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        return RouteCalculations.createPlan(
                request,
                "Car",
                ROAD_DISTANCE_FACTOR,
                AVERAGE_SPEED_KMH,
                "Drive on main roads from %s to %s.");
    }
}
