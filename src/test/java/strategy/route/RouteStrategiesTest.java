package strategy.route;

import org.junit.jupiter.api.Test;
import strategy.model.Location;
import strategy.model.RoutePlan;
import strategy.model.RouteRequest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RouteStrategiesTest {
    private final RouteRequest route = new RouteRequest(
            new Location("Point A", 0, 0),
            new Location("Point B", 0, 1));

    @Test
    void strategiesProduceDifferentRouteEstimatesAndInstructions() {
        RoutePlan car = new CarStrategy().plan(route);
        RoutePlan walking = new WalkingStrategy().plan(route);
        RoutePlan cycling = new CyclingStrategy().plan(route);

        assertEquals(138.99, car.distanceKm(), 0.01);
        assertTrue(walking.distanceKm() < cycling.distanceKm());
        assertTrue(cycling.distanceKm() < car.distanceKm());
        assertEquals("Drive on main roads from Point A to Point B.", car.instructions().get(0));
        assertEquals("Walk on pedestrian paths from Point A to Point B.", walking.instructions().get(0));
        assertEquals("Ride on cycle-friendly roads from Point A to Point B.", cycling.instructions().get(0));
    }
}
