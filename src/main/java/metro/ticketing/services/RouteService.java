package metro.ticketing.services;

import metro.ticketing.model.Route;
import metro.ticketing.model.Station;
import metro.ticketing.repository.FileManager;
import metro.ticketing.repository.JSONFileManager;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class RouteService {
    private HashMap<String, Route> routes = new HashMap<String, Route>();
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

            this.routes.put(tempJsonObject.getString("routeId"), Route.jsonToRoute(tempJsonObject, stationService));
        }
    }

    public void saveData() {
        JSONArray inputJsonArray = new JSONArray();

        for (HashMap.Entry<String, Route> entry : this.routes.entrySet()) {
            Route routeData = entry.getValue();

            inputJsonArray.put(Route.routeToJsonObject(routeData));
        }
        
        try {
            fileManager.saveData(inputJsonArray);
        } catch (Exception e) {
            System.out.println("Failed to save route data");
        }
    }

    public void viewAllRoutes() {
        for (HashMap.Entry<String, Route> entry : this.routes.entrySet()) {
            Route outputRoute = entry.getValue();

            outputRoute.displayRoute();
            System.out.println();
        }
    }

    public void addRoute(Route route){
        this.routes.put(route.getRouteId(), route);
    }

    public java.util.ArrayList<Route> findRoute(Station source){
        java.util.ArrayList<Route> result = new java.util.ArrayList<Route>();

        for(HashMap.Entry<String, Route> entry:this.routes.entrySet()){
            Route route = entry.getValue();

            if(route.getSource().getStationId().equals(source.getStationId())){
                result.add(route);
            }
        }
        return result;
    }

    public Route getRoute(String routeId) {
        return this.routes.get(routeId);
    }
}