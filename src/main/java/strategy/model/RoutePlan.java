package strategy.model;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public record RoutePlan(
        String strategyName,
        double distanceKm,
        Duration estimatedDuration,
        List<String> instructions) {

    public RoutePlan {
        Objects.requireNonNull(strategyName, "strategyName must not be null");
        Objects.requireNonNull(estimatedDuration, "estimatedDuration must not be null");
        Objects.requireNonNull(instructions, "instructions must not be null");
        if (strategyName.isBlank()) {
            throw new IllegalArgumentException("strategyName must not be blank");
        }
        if (!Double.isFinite(distanceKm) || distanceKm < 0) {
            throw new IllegalArgumentException("distanceKm must be finite and not negative");
        }
        if (estimatedDuration.isNegative()) {
            throw new IllegalArgumentException("estimatedDuration must not be negative");
        }
        instructions = List.copyOf(instructions);
    }
}
