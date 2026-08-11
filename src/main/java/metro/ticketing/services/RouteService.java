package metro.ticketing.services;

import metro.ticketing.model.Route;
import metro.ticketing.model.Station;
import metro.ticketing.repository.FileManager;
import metro.ticketing.repository.JSONFileManager;

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
        for (Route outputRoute : this.routes) {
            outputRoute.displayRoute();
            System.out.println();
        }
    }

    public void addRoute(Route route){
        this.routes.add(route);
    }

    public java.util.ArrayList<Route> findRoute(Station source){
        java.util.ArrayList<Route> result = new java.util.ArrayList<Route>();

        for(Route route : this.routes){
            if(route.getSource().getStationId().equals(source.getStationId())){
                result.add(route);
            }
        }
        return result;
    }

    public Route getRoute(String routeId) {
        for(Route route : this.routes){
            if(route.getRouteId().equals(routeId)){
                return route;
            }
        }
        return null;
    }

    public ArrayList<Route> getAllRoutesList(){
        return this.routes;
    }

    public boolean isRouteIdCanEdit(String newRouteId, String currentRouteId){
        if (newRouteId.equals(currentRouteId)){
            return true;
        }
        return getRoute(newRouteId) == null;
    }

    public boolean isSourceDestinationCanEdit(Station source, Station destination, String currentRouteId){
        for(Route route : this.routes){
            if (route.getRouteId().equals(currentRouteId)){
                continue;
            }

            if (route.getSource().getStationId().equals(source.getStationId()) && 
            route.getDestination().getStationId().equals(destination.getStationId())){
                return false;
            } 
        }
        return true;
    }

    public void updateRoute(String oldRouteId, Route updatedRoute){
        for(int i = 0; i < this.routes.size(); i++){
            if(this.routes.get(i).getRouteId().equals(oldRouteId)){
                this.routes.set(i, updatedRoute);
                return;
            }
        }
    }
}
