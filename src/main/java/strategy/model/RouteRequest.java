package strategy.model;

import java.util.Objects;

public record RouteRequest(Location origin, Location destination) {
    public RouteRequest {
        Objects.requireNonNull(origin, "origin must not be null");
        Objects.requireNonNull(destination, "destination must not be null");
    }
}
