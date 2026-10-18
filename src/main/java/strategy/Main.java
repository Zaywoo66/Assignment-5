package strategy;

import strategy.context.Navigator;
import strategy.model.Location;
import strategy.model.RoutePlan;
import strategy.model.RouteRequest;
import strategy.route.CarStrategy;
import strategy.route.CyclingStrategy;
import strategy.route.WalkingStrategy;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        RouteRequest route = new RouteRequest(
                new Location("Astana city center", 51.1280, 71.4307),
                new Location("Astana International Airport", 51.0222, 71.4669));

        Navigator navigator = new Navigator(new CarStrategy());
        printPlan(navigator.planRoute(route));

        navigator.setStrategy(new WalkingStrategy());
        printPlan(navigator.planRoute(route));

        navigator.setStrategy(new CyclingStrategy());
        printPlan(navigator.planRoute(route));
    }

    private static void printPlan(RoutePlan plan) {
        System.out.printf("%s route: %.2f km, about %s%n",
                plan.strategyName(), plan.distanceKm(), formatDuration(plan.estimatedDuration().toMinutes()));
        plan.instructions().forEach(System.out::println);
        System.out.println();
    }

    private static String formatDuration(long totalMinutes) {
        long hours = totalMinutes / 60;
        long minutes = totalMinutes % 60;
        return hours == 0 ? minutes + " min" : hours + " hr " + minutes + " min";
    }
}
