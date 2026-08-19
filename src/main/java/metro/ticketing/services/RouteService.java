package metro.ticketing.services;

import metro.ticketing.model.Route;
import metro.ticketing.model.Station;
import metro.ticketing.repository.FileManager;
import metro.ticketing.repository.JSONFileManager;
import metro.ticketing.include.func;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class RouteService {
    private ArrayList<Route> routes = new ArrayList<Route>();
    private FileManager fileManager = new JSONFileManager("data/route.json");

    public RouteService(StationService stationService) {
        JSONArray routeJsonArray = new JSONArray();

        try {
            routeJsonArray = fileManager.loadData();
        } catch (Exception e) {
            System.out.println("Error occured when loading route data from json");
        };

        for(int i = 0; i < routeJsonArray.length(); i++) {
            JSONObject tempJsonObject = routeJsonArray.getJSONObject(i);

            this.routes.add(Route.jsonToRoute(tempJsonObject, stationService));
        }
    }

    public void saveData() {
        JSONArray inputJsonArray = new JSONArray();

        for (Route routeData : this.routes) {
            inputJsonArray.put(Route.routeToJsonObject(routeData));
        }
        
        try {
            fileManager.saveData(inputJsonArray);
        } catch (Exception e) {
            System.out.println("Failed to save route data");
        }
    }

    public void viewAllRoutes() {
        for (int i = 0; i < this.routes.size(); i++) {
            Route route = this.routes.get(i);
            System.out.println((i + 1) + ". " + route.getRouteId() + " | "
                + route.getSource().getName() + " to " + route.getDestination().getName());
        }
    }

    public int routeCount(){
        return this.routes.size();
    }

    public void showRoute(int index){
        if(index < 1 || index > this.routes.size()){
            System.out.println("Invalid selection.\n");
            return;
        }

        Route route = this.routes.get(index - 1);

        System.out.println();
        System.out.println("Route ID    : " + route.getRouteId());
        System.out.println("Distance    : " + route.getDistanceKm() + " km");

        System.out.println();
        System.out.println("Source Station:");
        route.getSource().displayInfo();

        System.out.println();
        System.out.println("Destination Station:");
        route.getDestination().displayInfo();
        System.out.println();
    }


    public Route getRoute(String routeId){
        for (Route route: this.routes){
            if (route.getRouteId().equals(routeId)){
                return route;
            }
        }
        return null;
    }

    public String nextId(){
        int max = 0;

        for(Route route: this.routes){
            int num = Integer.parseInt(route.getRouteId().substring(1));

            if(num > max){
                max = num;
            }
        }
        return func.formatId("r", max + 1, 3);
    }


    public boolean isDuplicate(Station source, Station destination){
        for(Route route: this.routes){
            String src = route.getSource().getStationId();
            String dst = route.getDestination().getStationId();

            if((src.equals(source.getStationId()) && dst.equals(destination.getStationId()))
                || (src.equals(destination.getStationId()) && dst.equals(source.getStationId()))){
                    return true;
                }
        }
        return false;
    }

    public void addRoute(Route route){
        this.routes.add(route);
    }
}
