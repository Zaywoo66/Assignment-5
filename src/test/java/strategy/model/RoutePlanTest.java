package strategy.model;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RoutePlanTest {
    @Test
    void protectsItsInstructionsFromLaterChanges() {
        List<String> instructions = new ArrayList<>(List.of("Turn left."));
        RoutePlan plan = new RoutePlan("Car", 1.5, Duration.ofMinutes(3), instructions);
        instructions.add("Unexpected update.");

        assertEquals(List.of("Turn left."), plan.instructions());
        assertThrows(UnsupportedOperationException.class, () -> plan.instructions().add("Mutation."));
    }
}
