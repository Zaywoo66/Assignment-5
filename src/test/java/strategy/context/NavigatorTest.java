package strategy.context;

import org.junit.jupiter.api.Test;
import strategy.api.RouteStrategy;
import strategy.model.Location;
import strategy.model.RoutePlan;
import strategy.model.RouteRequest;
import strategy.route.CarStrategy;
import strategy.route.CyclingStrategy;
import strategy.route.WalkingStrategy;

import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NavigatorTest {
    private final RouteRequest route = new RouteRequest(
            new Location("Start", 51.1280, 71.4307),
            new Location("Airport", 51.0222, 71.4669));

    @Test
    void switchesStrategiesAtRuntimeOnTheSameNavigator() {
        Navigator navigator = new Navigator(new CarStrategy());
        RoutePlan carRoute = navigator.planRoute(route);

        navigator.setStrategy(new WalkingStrategy());
        RoutePlan walkingRoute = navigator.planRoute(route);

        navigator.setStrategy(new CyclingStrategy());
        RoutePlan cyclingRoute = navigator.planRoute(route);

        assertEquals("Car", carRoute.strategyName());
        assertEquals("Walking", walkingRoute.strategyName());
        assertEquals("Cycling", cyclingRoute.strategyName());
        assertTrue(carRoute.estimatedDuration().compareTo(cyclingRoute.estimatedDuration()) < 0);
        assertTrue(cyclingRoute.estimatedDuration().compareTo(walkingRoute.estimatedDuration()) < 0);
    }

    @Test
    void acceptsANewStrategyWithoutChangingTheNavigator() {
        RouteStrategy accessibleRouteStrategy = request -> new RoutePlan(
                "Accessible",
                2.0,
                Duration.ofMinutes(12),
                List.of("Use the step-free path."));
        Navigator navigator = new Navigator(accessibleRouteStrategy);

        RoutePlan plan = navigator.planRoute(route);

        assertEquals("Accessible", plan.strategyName());
        assertEquals("Use the step-free path.", plan.instructions().get(0));
    }

    @Test
    void rejectsNullStrategiesAndRequests() {
        assertThrows(NullPointerException.class, () -> new Navigator(null));

        Navigator navigator = new Navigator(new CarStrategy());
        assertThrows(NullPointerException.class, () -> navigator.setStrategy(null));
        assertThrows(NullPointerException.class, () -> navigator.planRoute(null));
    }
}
