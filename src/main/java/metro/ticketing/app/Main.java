package metro.ticketing.app;

import java.util.ArrayList;
import java.util.Scanner;

import metro.ticketing.enums.UserRole;
import metro.ticketing.model.Passenger;
import metro.ticketing.model.Route;
import metro.ticketing.model.Station;
import metro.ticketing.services.RouteService;
import metro.ticketing.services.StationService;
import metro.ticketing.services.TrainService;
import metro.ticketing.services.UserService;

public class Main {
    public static void main(String[] args) {
        UserService uService = new UserService();

        uService.viewAllUsers();
        
        Passenger newUser = new Passenger("blaaa", "registed", "register@gmail.com", "pass", UserRole.PASSENGER);

        uService.registerUser(newUser);

        uService.saveData();

        // train test
        TrainService trainService = new TrainService();

        trainService.viewAllTrains();

        // station test
        StationService stationService = new StationService();

        stationService.viewAllStations();

        // route test
        RouteService routeService = new RouteService(stationService);

        routeService.viewAllRoutes();

        /*
        add route test (admin)
        only support link the station already exist in the train.json
        need use add station function to add more option(havent finish now)
        */
        StationService stationService2 = new StationService();
        RouteService routeService2 = new RouteService(stationService2);

        Scanner scanner = new Scanner(System.in);

        String routeId;
        while(true){
            System.out.println("Please enter new Route ID, example - r001:");
            routeId = scanner.nextLine().trim();

            if(routeId.isEmpty()){
                System.out.println("Route ID cannot be empty. Please try again.");
            } else if(!routeId.matches("r\\d{3}")){
                System.out.println("Invalid format. Route ID must start with r and 3 number.");
            }else if(routeService2.getRoute(routeId) != null){
                System.out.println("This Route ID already exiists. Please use different Route ID.");
            }else {
                break;
            }
        }        

        Station source;
        while(true){
            System.out.println("Please enter source station Name:");
            String sourceName = scanner.nextLine().trim();
            source = stationService2.searchStation(sourceName);

            if (source == null){
                System.out.println("Source station entered is invalid. Please try again.");
            }else{
                break;
            }
        }    

        Station destination;
        while (true){
            System.out.println("Please enter destination station Name:");
            String destinationName = scanner.nextLine().trim();
            destination = stationService2.searchStation(destinationName);

            if (destination == null){
                System.out.println("Destination station entered is invalid. Please try again.");
            }else if(source.getStationId().equals(destination.getStationId())){
                System.out.println("Source and destination cannot be the same station. Please try again.");
            }else{
                break;
            }
        }

        double distance;
        while(true){
            System.out.println("Please enter distance in km: ");
            String distanceInput = scanner.nextLine().trim();

            try{
                distance = Double.parseDouble(distanceInput);

                if(distance <= 0){
                    System.out.println("Distance must be greater than 0. Please try again.");
                }else{
                    break;
                }
            } catch (NumberFormatException e){
                System.out.println("Invalid distance. Please enter a valid number.");
            }
        }

        Route newRoute = new Route(routeId, source, destination, distance);
        routeService2.addRoute(newRoute);

        System.out.println("New route added Successfully!");
        newRoute.displayRoute();

        routeService2.saveData();

        // find route test (passenger)
        StationService stationService3 = new StationService();
        RouteService routeService3 = new RouteService(stationService3);

        Station source2;
        ArrayList<Route> possibleRoutes;

        while(true){
            System.out.println("Please Enter source station name: ");
            String inputName = scanner.nextLine().trim();

            if (inputName.isEmpty()){
                System.out.println("Station name cannot be empty. Please try again.");
                continue;
            }

            source2 = stationService3.searchStation(inputName);

            if(source2 == null){
                System.out.println("Station not found. Please try again.");
                continue;
            }

            possibleRoutes = routeService3.findRoute(source2);

            if (possibleRoutes.isEmpty()){
                System.out.println("This source station has no routes. Please try again.");
                continue;
            }

            break;
        }

        System.out.println("Possible routes from " + source2.getName() + ": ");

        for(int i = 0; i < possibleRoutes.size(); i++){
            Route r = possibleRoutes.get(i);
            System.out.println((i+1) + "." + r.getDestination().getName() + " (" + r.getDistanceKm() + " km)");
        }
    }
}