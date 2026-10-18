package strategy.route;

import strategy.api.RouteStrategy;
import strategy.model.RoutePlan;
import strategy.model.RouteRequest;

import java.util.Objects;

public final class CyclingStrategy implements RouteStrategy {
    private static final double CYCLE_ROUTE_DISTANCE_FACTOR = 1.15;
    private static final double AVERAGE_SPEED_KMH = 16.0;

    @Override
    public RoutePlan plan(RouteRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        return RouteCalculations.createPlan(
                request,
                "Cycling",
                CYCLE_ROUTE_DISTANCE_FACTOR,
                AVERAGE_SPEED_KMH,
                "Ride on cycle-friendly roads from %s to %s.");
    }
}
