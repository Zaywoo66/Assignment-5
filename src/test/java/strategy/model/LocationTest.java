package strategy.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class LocationTest {
    @Test
    void acceptsCoordinatesAtTheGeographicBounds() {
        Location location = new Location("Northwest", 90, -180);

        assertEquals(90, location.latitude());
        assertEquals(-180, location.longitude());
    }

    @Test
    void rejectsCoordinatesOutsideTheGeographicBounds() {
        assertThrows(IllegalArgumentException.class, () -> new Location("Invalid", 91, 0));
        assertThrows(IllegalArgumentException.class, () -> new Location("Invalid", 0, 181));
    }
}
