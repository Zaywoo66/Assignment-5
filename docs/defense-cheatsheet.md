# Defense Guide

## Short walkthrough

1. Start with `RouteStrategy`, the common algorithm contract.
2. Show `CarStrategy`, `WalkingStrategy`, and `CyclingStrategy` as interchangeable implementations.
3. Explain that `Navigator` keeps one `RouteStrategy` reference, delegates to it, and can replace it through `setStrategy`.
4. Run `mvn -q exec:java '-Dexec.mainClass=strategy.Main'` and point out that the same navigator calculates all three routes.
5. Run `mvn test` to show the automated checks, including runtime switching and passing a new strategy implementation.

## Likely questions

**What problem does Strategy solve here?**
It keeps each route algorithm separate and lets the client choose one while the navigation context uses a stable interface.

**How does the design remove conditionals from the context?**
`Navigator` calls `routeStrategy.plan(request)` and never checks the concrete strategy's type.

**Can the strategy change after the navigator is created?**
Yes. `setStrategy` replaces the active implementation at runtime.

**How would you add a public-transit strategy?**
Create a class implementing `RouteStrategy` and pass it to `Navigator`. Existing context code stays unchanged.

**What does the demo calculate?**
It estimates distance with a Haversine coordinate formula and simple travel-mode distance multipliers and average speeds. It does not use real road data, traffic, or a map provider.

**What is the tradeoff?**
The design introduces an interface and separate classes, but gains isolated algorithms, runtime selection, and easier extension.
