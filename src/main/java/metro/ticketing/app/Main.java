package metro.ticketing.app;

<<<<<<< HEAD
import java.util.Scanner;

import metro.ticketing.model.User;
import metro.ticketing.model.Passenger;
import metro.ticketing.model.Admin;

=======
import java.util.ArrayList;
import java.util.Scanner;

import metro.ticketing.enums.UserRole;
import metro.ticketing.model.Passenger;
import metro.ticketing.model.Route;
import metro.ticketing.model.Station;
>>>>>>> f9cc82d4bb403d76437208a829ef9b9706cda04a
import metro.ticketing.services.RouteService;
import metro.ticketing.services.StationService;
import metro.ticketing.services.TicketService;
import metro.ticketing.services.TrainService;
import metro.ticketing.services.UserService;
<<<<<<< HEAD

import metro.ticketing.include.func;
import metro.ticketing.enums.UserRole;
import metro.ticketing.exception.InvalidLoginException;

public class Main {

    static UserService uService= new UserService();

    public static void main(String[] args) {
        // welcome message to the system 
        //
        func.printHeader("", '=');
        func.printHeader("", ' ');
        func.printHeader("WELCOME TO SMART METRO TICKETING", ' ');
        func.printHeader("", ' ');
        func.printHeader("", '=');

        System.out.println("");

        boolean running = true;

        Scanner scanner = new Scanner(System.in);

        while(running) {
            mainMenu();

            char choice = func.getChoice();
            System.out.println("");

            switch (choice) {
                case '1':
                    User user = login();
                    
                    if (user instanceof Passenger) {
                        PassengerUI.PassengerUI((Passenger)user);
                    } else if (user instanceof Admin) {
                        AdminUI.AdminUI((Admin)user);
                    }

                    break;

                 case '2':
                    System.out.println("Let's Register");
                    System.out.println("");

                    register();
                    
                    break;

                 case '3':
                    System.out.println("Quitting SMART METRO TICKETING");
                    running = false;
                    break; 
                default:
                    System.out.println("!!! INVALID INPUT !!!\n");
                    break;
            }

        }

    }

    private static void mainMenu() {
        func.printHeader("Main Menu", '-');
        System.out.println("1. Login");
        System.out.println("2. Registration");
        System.out.println("3. Quit");
    }
    
    private static User login() {
        User userobj = null;

        func.printHeader("Login", '-');

        String email = func.getStrInput("Email     :");
        String pwd   = func.getStrInput("Password  :");

        try {
            userobj = uService.login(email, pwd); 
            System.out.println("");
        } catch (InvalidLoginException e) {
            System.out.println("Login failed !!!");
        }
        
        return userobj;
    }

    private static void register() {
        // get input 
        String format = "%-12s:";

        String name  = null; 
        String pwd   = null;
        String email = null;

        do {
            name = func.getStrLnInput(String.format(format, "Name"));

            if (name.isEmpty()) {
                name = null;
                System.out.println("Enter a name!");
            }
        } while (name == null);

        do {
            email = func.getStrLnInput(String.format(format, "Email"));

            String[] emailEndings = {
                "@gmail.com",
                "@1utar.my",
                "@utar.my",
                "@yahoo.com",
                "@hotmail.com"
            };
            
            boolean validEmail = false;

            for(String ending :  emailEndings) {
                if (email.endsWith(ending)) {
                    if (!email.replaceFirst(ending, "").isEmpty()) {
                        validEmail = true;
                        break;
                    }
                }
            }

            if (uService.emailExists(email) || !validEmail) {
                email = null;
                System.out.println("Invalid Email!");
            }
        } while (email == null);

        do {
            pwd = func.getStrLnInput(String.format(format, "Password"));

            if (pwd.length() != 8) {
                pwd = null;
                System.out.println("Password must be 8 characters!");
            }
        } while (pwd == null);

        uService.registerUser(name, email, pwd);
        System.out.println("\nRegistrated for " + name +  "!!!\n");
    }

}
=======

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
>>>>>>> f9cc82d4bb403d76437208a829ef9b9706cda04a
