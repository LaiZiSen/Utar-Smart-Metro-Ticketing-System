package metro.ticketing.services;

import metro.ticketing.model.Route;
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
}
