package strategy.route;

import strategy.api.RouteStrategy;
import strategy.model.RoutePlan;
import strategy.model.RouteRequest;

import java.util.Objects;

public final class WalkingStrategy implements RouteStrategy {
    private static final double PEDESTRIAN_DISTANCE_FACTOR = 1.10;
    private static final double AVERAGE_SPEED_KMH = 5.0;

    @Override
    public RoutePlan plan(RouteRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        return RouteCalculations.createPlan(
                request,
                "Walking",
                PEDESTRIAN_DISTANCE_FACTOR,
                AVERAGE_SPEED_KMH,
                "Walk on pedestrian paths from %s to %s.");
    }
}
