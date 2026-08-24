package metro.ticketing.services;

import metro.ticketing.model.Route;
import metro.ticketing.model.Station;
import metro.ticketing.repository.FileManager;
import metro.ticketing.repository.JSONFileManager;

import java.util.ArrayList;
import metro.ticketing.include.func;

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

    public ArrayList<Station> getSourceStations() {
        ArrayList<Station> sourceStations = new ArrayList<>();

        for (Route route : this.routes) {
            if (!sourceStations.contains(route.getSource())) {
                sourceStations.add(route.getSource());
            }
        }

        return sourceStations;
    }

    public ArrayList<Route> getRouteBySource(Station source){
        ArrayList<Route> routes = new ArrayList<>(); 

        for (Route route : this.routes) {
            if (route.getSource() == source) {
                routes.add(route);
            }
        }
    
        return routes;
    }

    public Route findRoute() {
        ArrayList<Station> sourceStations = getSourceStations();
        Station source = null;
        Station destination = null;
        Route output = null;

        int intChoice;

        System.out.println("Source Stations");
        for (int i = 0; i < sourceStations.size(); i++) {
            Station temp = sourceStations.get(i);
            System.out.printf("%2d. %s \n", i+1, temp.getName());
        }
        
        while(source == null) {
            intChoice = func.getIntInput("Enter Source Station: ");

            try {
                source = sourceStations.get(intChoice-1);
            } catch (Exception e) {
                System.out.println("Invalid choice !!!\n");
            }
        }

        ArrayList<Route> routeChoices = getRouteBySource(source);

        System.out.println("Destination Stations");
        for (int i = 0; i < routeChoices.size(); i++) {
            Station temp = routeChoices.get(i).getDestination();
            System.out.printf("%2d. %s \n", i+1, temp.getName());
        }
        
        while(output == null) {
            intChoice = func.getIntInput("Enter Destination Station: ");

            try {
                output = routeChoices.get(intChoice-1);
            } catch (Exception e) {
                System.out.println("Invalid choice !!!\n");
            }
        }


        return output;
    }
}
