package metro.ticketing.app;

import java.util.ArrayList;
import java.util.Scanner;

import metro.ticketing.enums.UserRole;
import metro.ticketing.model.Passenger;
import metro.ticketing.model.Route;
import metro.ticketing.model.Station;
import metro.ticketing.services.RouteService;
import metro.ticketing.services.StationService;
import metro.ticketing.services.TicketService;
import metro.ticketing.services.TrainService;
import metro.ticketing.services.UserService;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
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

        // prepare to call three method: add route, edit route and find route (passenger)
        StationService stationService2 = new StationService();
        RouteService routeService2 = new RouteService(stationService2);
        ArrayList<Station> allStations = stationService2.getAllStations();

        addRouteTest(routeService2, allStations);
        editRouteTest(routeService2, allStations);
        findRouteTest();


        StationService stService = new StationService();
        UserService uServiceForTicket = new UserService();
        TicketService tService = new TicketService(stService, uServiceForTicket);
    
        tService.viewAllTicket();     
    }

    /*
    add route test (admin)
    only support link the station already exist in the train.json
    need use add station function to add more option(havent finish now)
    */
    private static void addRouteTest(RouteService routeService2, ArrayList<Station> allStations){

        System.out.println("\u001B[106mAdd Route test (Admin)\u001B[0m");

        String routeId;
        while(true){
            System.out.println("Please enter new Route ID, example - r001:");
            routeId = scanner.nextLine().trim();

            if(routeId.isEmpty()){
                System.out.println("Route ID cannot be empty. Please try again.");
            }else if(!routeId.matches("r\\d{3}")){
                System.out.println("Invalid format. Route ID must start with r and 3 number.");
            }else if(routeService2.getRoute(routeId) != null){
                System.out.println("This Route ID already exiists. Please use different Route ID.");
            }else {
                break;
            }
        }

        if (allStations.isEmpty()){
            System.out.println("No stations available. Please add stations first.");
            return;
        } 

            System.out.println();

            Station source;
            while(true){
                System.out.println("Please select source station: ");
                for(int i = 0; i < allStations.size(); i++){
                    System.out.println((i+1) + ". " + allStations.get(i).getName());
                }

                System.out.print("Enter your choice: ");
                String input = scanner.nextLine().trim();

                try{
                    int choice = Integer.parseInt(input);

                    if (choice < 1 || choice > allStations.size()){
                        System.out.println("Invalid choice. Please enter a number 1 to " + allStations.size() + ".");
                        continue;
                    }

                    source = allStations.get(choice - 1);
                    break;

                }catch (NumberFormatException e){
                    System.out.println("Invalid input. Please enter a number.");
                }
            }
            
            System.out.println();

            Station destination;
            while (true){
                System.out.println("Please select destination station:");
                for(int i = 0; i<allStations.size(); i++){
                    System.out.println((i+1) + ". " + allStations.get(i).getName());
                }

                System.out.print("Enter your choice: ");
                String input = scanner.nextLine().trim();

                try{
                    int choice = Integer.parseInt(input);

                    if(choice < 1 || choice > allStations.size()){
                        System.out.println("Invalid choice. Please enter a number 1 to " + allStations.size() + ".");
                        continue;
                    }

                    Station chosen = allStations.get(choice - 1);

                    if (chosen.getStationId().equals(source.getStationId())){
                        System.out.println("Source and destination cannot be the same station.");
                        continue;
                    }

                    destination = chosen;
                    break;
                }catch (NumberFormatException e){
                    System.out.println("Invalid input. Please enter a number.");
                }
            }

            System.out.println();

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

            System.out.println();
            System.out.println("New route added Successfully!");
            newRoute.displayRoute();

            routeService2.saveData();
        }

        // edit route test (admin)
        private static void editRouteTest(RouteService routeService2, ArrayList<Station> allStations)
        {
            System.out.println();
            System.out.println("\u001B[106mEdit route test (admin)\u001B[0m");

            ArrayList<Route> allRoutes = routeService2.getAllRoutesList();

            if (allRoutes.isEmpty()){
                System.out.println("No routes available to edit.");
                return;
            }
            
            Route routeToEdit;
            while(true){
                System.out.println("Please select a route to edit.");

                for (int i = 0; i<allRoutes.size(); i++){
                    Route r = allRoutes.get(i);
                    System.out.println((i+1) + ". " + r.getRouteId() + "( " + r.getSource().getName() + "-> " + r.getDestination().getName() + ", " + r.getDistanceKm() + " km)");
                }

                System.out.println("Enter your choice: ");
                String input = scanner.nextLine().trim();

                try{
                    int choice = Integer.parseInt(input);

                    if (choice < 1 || choice > allRoutes.size()){
                        System.out.println("Invalid choice. Please enter a number 1 to " + allRoutes.size() + ".");
                        continue;
                    }

                    routeToEdit = allRoutes.get(choice - 1);
                    break;
                } catch (NumberFormatException e){
                    System.out.println("Invalid input. Please enter a number.");
                }
            }
            
            String originalRouteId = routeToEdit.getRouteId();

            System.out.println();
            String newRouteId;
            while(true){
                System.out.println("Current Route ID: " + routeToEdit.getRouteId());
                System.out.println("Enter new Route ID (or press Enter to keep current): ");
                String input = scanner.nextLine().trim();

                if(input.isEmpty()){
                    newRouteId = routeToEdit.getRouteId();
                    break;
                }else if (!input.matches("r\\d{3}")){
                    System.out.println("Invalid format. Route ID must start with r and 3 numbers.");
                }else if(!routeService2.isRouteIdCanEdit(input, originalRouteId)){
                    System.out.println("This Route ID already exists. Please use a different Route ID.");
                }else{
                    newRouteId = input;
                    break;
                }
            }

            System.out.println();
            Station newSource;
            while(true){
                System.out.println("Current Source: " + routeToEdit.getSource().getName());
                System.out.println("Select new source station (or press Enter to keep current): ");

                for(int i = 0; i<allStations.size(); i++){
                System.out.println((i+1) + ". " + allStations.get(i).getName());
                }

                System.out.println("Enter your choice: ");
                String input = scanner.nextLine().trim();

                if (input.isEmpty()){
                    newSource = routeToEdit.getSource();
                    break;
                }

                try{
                    int choice = Integer.parseInt(input);

                    if (choice < 1 || choice > allStations.size()){
                        System.out.println("Invalid choice. Please enter a number 1 to " + allStations.size() + ". ");
                        continue;
                    }

                    newSource = allStations.get(choice - 1);
                    break;
                } catch(NumberFormatException e){
                    System.out.println("Invalid input. Please enter a number.");
                }
            }

            System.out.println();
            Station newDestination;
            while(true){
                System.out.println("Current Destination: " + routeToEdit.getDestination().getName());
                System.out.println("Select new destination station (or press Enter to keep current): ");

                for(int i = 0; i<allStations.size(); i++){
                    System.out.println((i+1) + ". " + allStations.get(i).getName());
                }

                System.out.print("Enter your choice: ");

                String input = scanner.nextLine().trim();

                Station chosen;

                if(input.isEmpty()){
                    chosen = routeToEdit.getDestination();
                }else{
                    try{
                        int choice = Integer.parseInt(input);

                        if(choice < 1 || choice > allStations.size()){
                            System.out.println("Invalid choice. Please enter a number 1 to " + allStations.size() + ". ");
                            continue;
                        }

                        chosen = allStations.get(choice - 1);
                    } catch (NumberFormatException e){
                        System.out.println("Invalid input. Please enter a number.");
                        continue;
                    }
                }

                if(chosen.getStationId().equals(newSource.getStationId())){
                    System.out.println("Source and destination cannot be the same station. Please try again.");
                    continue;
                }

                if(!routeService2.isSourceDestinationCanEdit(newSource, chosen, originalRouteId)){
                    System.out.println("This source and destination combination already exists in another route. Please try again.");
                    continue;
                }
                newDestination = chosen;
                break;
            }

            System.out.println();
            double newDistance;
            while(true){
                System.out.println("Current Distance: " + routeToEdit.getDistanceKm() + " km");
                System.out.println("Enter new distance in km (or press Enter to keep current): ");
                String input = scanner.nextLine().trim();

                if(input.isEmpty()){
                    newDistance = routeToEdit.getDistanceKm();
                    break;
                }

                try{
                    newDistance = Double.parseDouble(input);

                    if(newDistance <= 0){
                        System.out.println("Distance must be greater than 0. Please try again.");
                    }else{
                        break;
                    }
                } catch (NumberFormatException e){
                    System.out.println("Invalid distance. Please enter a valid number.");
                }
            }
            routeToEdit.setRouteId(newRouteId);
            routeToEdit.setSource(newSource);
            routeToEdit.setDestination(newDestination);
            routeToEdit.setDistanceKm(newDistance);

            routeService2.updateRoute(originalRouteId, routeToEdit);

            System.out.println();
            System.out.println("Route updated successfully!");
            routeToEdit.displayRoute();

            routeService2.saveData();
        
        }

        private static void findRouteTest(){
        // find route test (passenger)
        System.out.println();
        System.out.println("\u001B[106mFind Route test (Passenger)\u001B[0m");

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

        System.out.println();
        System.out.println("Possible routes from " + source2.getName() + ": ");

        for(int i = 0; i < possibleRoutes.size(); i++){
            Route r = possibleRoutes.get(i);
            System.out.println((i+1) + "." + r.getDestination().getName()+ " (" + r.getDistanceKm() + " km)");
        }
    }
}