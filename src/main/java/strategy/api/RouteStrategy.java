package strategy.api;

import strategy.model.RoutePlan;
import strategy.model.RouteRequest;

/** An interchangeable algorithm for planning a route. */
public interface RouteStrategy {
    RoutePlan plan(RouteRequest request);
}
