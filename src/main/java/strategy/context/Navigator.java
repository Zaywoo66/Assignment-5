package strategy.context;

import strategy.api.RouteStrategy;
import strategy.model.RoutePlan;
import strategy.model.RouteRequest;

import java.util.Objects;

/** Delegates route planning to the currently selected strategy. */
public final class Navigator {
    private RouteStrategy routeStrategy;

    public Navigator(RouteStrategy routeStrategy) {
        setStrategy(routeStrategy);
    }

    public void setStrategy(RouteStrategy routeStrategy) {
        this.routeStrategy = Objects.requireNonNull(routeStrategy, "routeStrategy must not be null");
    }

    public RoutePlan planRoute(RouteRequest request) {
        Objects.requireNonNull(request, "request must not be null");
        return routeStrategy.plan(request);
    }
}
