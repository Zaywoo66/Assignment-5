# Clean Code Principles

## 1. Replace conditional algorithm selection with polymorphism

Each travel mode implements `RouteStrategy`. `Navigator` delegates to the active implementation instead of branching on a mode value.

Before:

```java
if (mode.equals("car")) return planCarRoute(request);
if (mode.equals("walk")) return planWalkingRoute(request);
return planCyclingRoute(request);
```

After:

```java
return routeStrategy.plan(request);
```

## 2. Use intention-revealing names

Names show what the object or operation means without requiring comments to decode generic labels.

Before:

```java
Result run(Data data);
```

After:

```java
RoutePlan plan(RouteRequest request);
```

## 3. Give each strategy one responsibility

Every concrete strategy owns its own distance and speed assumptions. `CarStrategy` does not also decide how walking or cycling routes work.

Before:

```java
class RoutePlanner {
    // Car, walking, and cycling calculations all live here.
}
```

After:

```java
final class WalkingStrategy implements RouteStrategy {
    // Walking route estimate only.
}
```

## 4. Keep the context independent of strategy details

`Navigator` stores only the shared interface. It does not import, construct, or inspect any concrete strategy.

Before:

```java
switch (strategyType) {
    case "car" -> ...;
    case "walk" -> ...;
}
```

After:

```java
public RoutePlan planRoute(RouteRequest request) {
    return routeStrategy.plan(request);
}
```

## 5. Make extension possible without modifying existing classes

A new algorithm can implement `RouteStrategy` and be passed to `Navigator`. The context does not need a new branch.

Before:

```java
// Adding a travel mode requires editing the existing route-selection switch.
```

After:

```java
RouteStrategy accessibleRoutes = request -> createAccessiblePlan(request);
Navigator navigator = new Navigator(accessibleRoutes);
```

The test `acceptsANewStrategyWithoutChangingTheNavigator` demonstrates this extension with a new strategy implementation.
