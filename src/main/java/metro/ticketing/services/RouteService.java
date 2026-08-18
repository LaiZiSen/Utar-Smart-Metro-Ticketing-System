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

    public ArrayList<Route> getAllRoutes(){
        return this.routes; 
    }
    
}