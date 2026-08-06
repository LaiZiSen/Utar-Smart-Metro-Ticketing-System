package metro.ticketing.model;

import org.json.JSONObject;

import metro.ticketing.services.StationService;

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

    public Route (String routeId, Station source, Station destination, double distanceKm) {
        this.routeId = routeId;
        this.source = source;
        this.destination = destination;
        this.distanceKm = distanceKm;
    }

    public double calculateDistance() {
        return this.distanceKm;
    }

    public void displayRoute() {
        System.out.println("Route ID    : " + routeId);
        System.out.println("Source      : " + source.getName());
        System.out.println("Destination : " + destination.getName());
        System.out.println("Distance    : " + distanceKm + " km");
    }

    public static Route jsonToRoute(JSONObject json, StationService stationService){
        String sourceId = json.getString("source");
        String destinationId = json.getString("destination");

        Route outputRouteObject = new Route(
            json.getString("routeId"),
            stationService.getStationById(sourceId),
            stationService.getStationById(destinationId),
            json.getDouble("distanceKm")
        );
        return outputRouteObject;
    }

    public static JSONObject routeToJsonObject(Route routeData){
        JSONObject value = new JSONObject();
        value.put("routeId", routeData.getRouteId());
        value.put("source", routeData.getSource().getStationId());
        value.put("destination", routeData.getDestination().getStationId());
        value.put("distanceKm", routeData.getDistanceKm());
        return value;
    }
}