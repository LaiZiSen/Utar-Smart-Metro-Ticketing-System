package metro.ticketing.app;

import java.util.Scanner;

import metro.ticketing.include.func;
import metro.ticketing.model.Admin;
import metro.ticketing.model.Route;
import metro.ticketing.model.Station;
import metro.ticketing.services.RouteService;
import metro.ticketing.services.StationService;

public class AdminUI{
    private Admin admin;

    public AdminUI(Admin admin) {
        this.admin = admin;
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        StationService stationService = new StationService();
        RouteService routeService = new RouteService(stationService);

        while(true) {
            adminMenu();

            char choice = func.getChoice();
            System.out.println("");

            switch (choice) {
                case '1':
                    routeMenu(routeService, stationService);
                    break;
                
                case '2':
                    break;
                
                case '3':
                    stationMenu(stationService);
                    break;
                
                case '4':
                    break;
               
                case '5':
                    break;

                case '6':
                    admin.viewProfile();
                    break;

                case '7':
                    System.out.println("Logging out of " + admin.getName() + '\n');
                    func.pause();
                    return; 

                default:
                    System.out.println("!!! INVALID INPUT !!!\n");
                    break;
            }
        }


    }
   
    private void adminMenu() {
        func.clear();
        func.printHeader("",'=');
        func.printHeader("",' ');
        func.printHeader(("Welcome "+ admin.getName()),' ');
        func.printHeader("",' ');
        func.printHeader("",'=');
        System.out.println("");

        func.printHeader("Admin Menu", '-');
        System.out.println("1. Route System");
        System.out.println("2. Train System");
        System.out.println("3. Station System");
        System.out.println("4. Ticket Rate");
        System.out.println("5. Report");
        System.out.println("6. Profile");
        System.out.println("7. Logout");
    }

    private static void routeMenu(RouteService routeService, StationService stationService){
        while(true){
            func.printHeader("Route System", '-');
            System.out.println("1. View All Route");
            System.out.println("2. Add Route");
            System.out.println("3. Back");

            char choice = func.getChoice();
            System.out.println("");

            switch (choice){
                case '1':
                    viewAllRoute(routeService);
                    break;

                case '2':
                    addRoute(routeService, stationService);
                    break;

                case '3':
                    return;

                default:
                    System.out.println("!!!INVALID INPUT!!!\n");
                    break;
            }
        }
    }

    private static void viewAllRoute(RouteService routeService){
        routeService.viewAllRoutes();

        int choice = Integer.parseInt(func.getStrLnInput("Enter number to view details (Press 0 to cancel): "));

        if(choice == 0){
            return;
        }
        routeService.showRoute(choice);
    }

    private static void addRoute(RouteService routeService, StationService stationService){
            
        if (stationService.stationCount() == 0){
            System.out.println("No stations available. Please add stations first.\n");
            return;
        }
        
        Station source;
        while(true){
            System.out.println("Select source station (Press 0 to cancel): ");
            for(int i = 1; i <= stationService.stationCount(); i++){
                System.out.println(i + "." + stationService.stationAt(i).getName());
            }

            try{
                int srcChoice = Integer.parseInt(func.getStrInput("Enter choice: "));

                if(srcChoice == 0){
                    System.out.println("Add Route cancelled.\n");
                    return;
                }

                source = stationService.stationAt(srcChoice);

                if(source == null){
                    System.out.println("Invalid choice. Please enter a number between 1 and " + stationService.stationCount() + ".\n");
                    continue;
                }
                break;    
            } catch(NumberFormatException e){
                System.out.println("Invalid input. Please enter a number.\n");
            }
        }

        Station destination;
        while(true){
            System.out.println("Select destination station (Press 0 to cancel): ");
            for(int i = 1; i <= stationService.stationCount(); i++){
                System.out.println(i + "." + stationService.stationAt(i).getName());
            }

            try{
                int dstChoice = Integer.parseInt(func.getStrInput("Enter choice: "));

                if(dstChoice == 0){
                    System.out.println("Add Route Cancelled.\n");
                    return;
                }

                destination = stationService.stationAt(dstChoice);

                if(destination == null){
                    System.out.println("Invalid choice. Please enter a number between 1 and " + stationService.stationCount() + ".\n");
                    continue;
                }
                break;
            } catch(NumberFormatException e){
                System.out.println("Invalid input. Please enter a number.\n");
            }
        }

        if(routeService.isDuplicate(source, destination)){
            System.out.println("This route already exists.\n");
            return;
        }
        double distanceKm = Double.parseDouble(func.getStrInput("Enter distance in km: "));

        Route newRoute = new Route(routeService.nextId(), source, destination, distanceKm);
        routeService.addRoute(newRoute);

        routeService.saveData();
        System.out.println("Route added successfully\n");
    }

    private static void stationMenu(StationService stationService){
        while(true){
            func.printHeader("Station System", '-');
            System.out.println("1. View Stations");
            System.out.println("2. Add Station");
            System.out.println("3. Back");

            char choice = func.getChoice();
            System.out.println("");

            switch(choice){
                case '1':
                    stationService.viewStations();
                    break;

                case '2':
                    addStation(stationService);
                    break;
                
                case '3':
                    return;
                    
                default:
                    System.out.println("!!!INVALID INPUT!!!\n");    
            }
        }
    }

    private static void addStation(StationService stationService){
        String name = func.getStrLnInput("Enter station name (Press 0 to cancel): ");

        if(name.equals("0")){
            System.out.println("Add station cancalled.\n");
            return;
        }

        if(stationService.searchStation(name) != null){
            System.out.println("A station with this name already exists.\n");
            return;
        }

        String location = func.getStrLnInput("Enter station location: ");

        Station newStation = new Station(stationService.nextId(), name, location);
        stationService.addStation(newStation);

        stationService.saveData();
        System.out.println("Station added successfully\n");
    }

}





                                                                            
                                                                            
                                                                            
 
