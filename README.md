# Strategy Pattern Route Planner

A Java 17 example of the Strategy behavioral design pattern. The navigator plans the same route with interchangeable car, walking, and cycling algorithms. The client changes the strategy at runtime while the `Navigator` context remains independent of concrete strategy classes.

The estimates use geographic coordinates, a distance multiplier, and an average travel speed. This is a classroom demonstration; it does not query live maps, traffic, or road networks.

## Pattern roles

| Role | Class | Responsibility |
| --- | --- | --- |
| Strategy | `RouteStrategy` | Common contract for a route-planning algorithm. |
| Concrete strategies | `CarStrategy`, `WalkingStrategy`, `CyclingStrategy` | Estimate a route for one travel mode. |
| Context | `Navigator` | Stores the selected strategy and delegates route planning to it. |
| Client | `Main` | Selects and switches strategies at runtime. |
| Models | `Location`, `RouteRequest`, `RoutePlan` | Carry validated input and route results. |

## Run the tests

Install JDK 17 or newer and Maven, then run:

```sh
mvn test
```

The JUnit suite checks runtime switching, strategy-specific route estimates, the ability to inject a new strategy, geographic input validation, and immutable route instructions.

## Run the demonstration

```sh
mvn clean package
mvn -q exec:java -Dexec.mainClass=strategy.Main
```

In PowerShell, quote the Maven property: `mvn -q exec:java '-Dexec.mainClass=strategy.Main'`.

`Main` builds one route in Astana and prints its estimate three times, switching the same `Navigator` from car to walking to cycling. A client can also use the context directly:

```java
Navigator navigator = new Navigator(new CarStrategy());
RoutePlan carRoute = navigator.planRoute(routeRequest);

navigator.setStrategy(new WalkingStrategy());
RoutePlan walkingRoute = navigator.planRoute(routeRequest);
```

Example console output:

```text
Car route: 15.04 km, about 16 min
Drive on main roads from Astana city center to Astana International Airport.

Walking route: 13.24 km, about 2 hr 38 min
Walk on pedestrian paths from Astana city center to Astana International Airport.

Cycling route: 13.84 km, about 51 min
Ride on cycle-friendly roads from Astana city center to Astana International Airport.
```

## Project structure

```text
src/main/java/strategy/
├── Main.java
├── api/RouteStrategy.java
├── context/Navigator.java
├── model/Location.java, RouteRequest.java, RoutePlan.java
└── route/CarStrategy.java, WalkingStrategy.java, CyclingStrategy.java
src/test/java/strategy/
├── context/NavigatorTest.java
├── model/LocationTest.java, RoutePlanTest.java
└── route/RouteStrategiesTest.java
docs/
├── uml.puml
├── clean-code-notes.md
└── defense-cheatsheet.md
```

The UML class diagram is in [docs/uml.puml](docs/uml.puml). Strategy implementations and the context are documented in [the Clean Code notes](docs/clean-code-notes.md), with likely defense questions in [the defense guide](docs/defense-cheatsheet.md).

GitHub repository: <https://github.com/Zaywoo66/Assignment-5>
