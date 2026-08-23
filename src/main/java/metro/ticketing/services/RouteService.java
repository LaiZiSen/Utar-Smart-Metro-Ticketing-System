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

    private StationService stationService;

    public RouteService(StationService stationService) {
        this.stationService = stationService;

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
        System.out.printf("%-5s| %-10s| %-30s| %-30s%n", "No.","Route ID", "Source", "Destination");
        for (int i = 0; i < this.routes.size(); i++) {
            Route route = this.routes.get(i);
            System.out.printf("%-5s| %-10s| %-30s| %-30s%n", (i+1) + ".", route.getRouteId(), route.getSource().getName(), route.getDestination().getName());
        }
    }

    public void viewRoute(){
        func.clear();
        viewAllRoutes();

        int cancel = routeCount() + 1;
        System.out.printf("%-5s| Cancel%n", cancel + ".");

        int choice = func.getIntInput("Enter number to view details: ");

        if(choice == cancel){
            return;
        }
        showRoute(choice);
        func.pause();
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

    public boolean isSame(Station source, Station destination){
        return source.getStationId().equals(destination.getStationId());
    }

    public void addRoute(Route route){
        this.routes.add(route);
    }

    public void addRoute(){
        func.clear();
        if(stationService.stationCount() == 0){
            System.out.println("No stations available. Please add stations first.\n");
            func.pause();
            return;
        }

        Station source;
        while(true){
            System.out.println("Select source station: ");
            for(int i = 1; i <= stationService.stationCount(); i++){
                System.out.println(i + ". " + stationService.stationAt(i).getName());
            }
            int srcChoice = func.getIntInput("Enter choice: ");

            source = stationService.stationAt(srcChoice);
            if(source == null){
                System.out.println("!!!INVALID INPUT!!! Please enter a number between 1 and " + stationService.stationCount() +".\n");
                continue;
            }
            break;
        }

        Station destination;
        while(true){
            System.out.println("Select destination station: ");
            for(int i = 1; i<=stationService.stationCount(); i++){
                System.out.println(i + ". " + stationService.stationAt(i).getName());
            }
            int dstChoice = func.getIntInput("Enter choice: ");

            destination = stationService.stationAt(dstChoice);
            if(destination == null){
                System.out.println("!!!INVALID INPUT!!! Please enter a number between 1 and " + stationService.stationCount() + ".\n");
                continue;
            }

            if(isSame(source, destination)){
                System.out.println("!!!INVALID INPUT!!! Destination cannot be the same as source station.\n");
                continue;
            }
            break;
        }    
        if(isDuplicate(source, destination)){
            System.out.println("This route already exists.\n");
            func.pause();
            return;
        }
        double distanceKm = func.getDblInput("Enter distance in km: ");

        while(true){
            System.out.println("1. Confirm");
            System.out.println("2. Cancel");
            char confirm = func.getChoice();

            if(confirm == '1'){
                break;
            }else if(confirm == '2'){
                System.out.println("Add route cancelled.\n");
                func.pause();
                return;
            }else{
                System.out.println("!!!INVALID INPUT!!!\n");
            }
        }
        Route newRoute = new Route(nextId(), source, destination, distanceKm);
        addRoute(newRoute);

        saveData();
        System.out.println("Route added successfully\n");
        func.pause();
    }

    public void run(){
        while(true){
            routeMenu();

            char choice = func.getChoice();
            System.out.println("");

            switch(choice){
                case '1':
                viewRoute();
                break;

                case '2':
                    addRoute();
                    break;

                case '3':
                    return;
                    
                default:
                    System.out.println("!!!INVALID INPUT!!!");
                    func.pause();
                    break;    
            }
        }
    }
    
    public void routeMenu(){
        func.printHeader("Route System", '=');
        System.out.println("1. View All Route");
        System.out.println("2. Add Route");
        System.out.println("3. Back");
        func.printHeader("", '-');
    }
}
