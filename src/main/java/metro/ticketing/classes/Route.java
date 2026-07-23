package metro.ticketing.classes;

public class Route {
    private String routeId;
    private Station source;
    private Station destination;
    private double distanceKm;

    public String getRouteId() {
        return routeId;
    }

    public Station getSource() {
        return source;
    }

    public Station getDestination() {
        return destination;
    }

    public double getDistanceKm() {
        return distanceKm;
    }

    public Route (String aRouteId, Station aSource, Station aDestination, double aDistanceKm) {
        routeId = aRouteId;
        source = aSource;
        destination = aDestination;
        distanceKm = aDistanceKm;
    }

    public double calculateDistance() {
        return distanceKm;
    }

    public void displayRoute() {
        System.out.println("Route ID    : " + routeId);
        System.out.println("Source      : " + source);
        System.out.println("Destination : " + destination);
        System.out.println("Distance    : " + distanceKm);
    }
}