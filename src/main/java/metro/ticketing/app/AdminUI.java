package metro.ticketing.app;

import java.util.ArrayList;
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
        boolean back = false;

        while(!back){
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
                    back = true;
                    break;
            
                default:
                    System.out.println("!!!INVALID INPUT!!!\n");
                    break;
            }
        }
    }

    private static void viewAllRoute(RouteService routeService){
        routeService.viewAllRoutes();

        int choice = Integer.parseInt(func.getStrLnInput("Enter number to view details (0 to skip): "));

        if(choice == 0){
            return;
        }
        routeService.showRoute(choice);
    }

    private static void addRoute(RouteService routeService, StationService stationService){
        ArrayList<Station> stations = stationService.getAllStations();

        if (stations.isEmpty()){
            System.out.println("No stations available. Please add stations first.\n");
            return;
        }
        System.out.println("Select source station: ");
        for(int i = 0; i < stations.size(); i++){
            System.out.println((i+1) + "." + stations.get(i).getName());
        }
        int srcChoice = Integer.parseInt(func.getStrInput("Enter choice: ")) - 1;
        Station source = stations.get(srcChoice);

        System.out.println("Select destination station: ");
        for(int i = 0; i < stations.size(); i++){
            System.out.println((i+1) + "." + stations.get(i).getName());
        }
        int dstChoice = Integer.parseInt(func.getStrInput("Enter choice: ")) - 1;
        Station destination = stations.get(dstChoice);

        if(routeService.isDuplicate(source, destination)){
            System.out.println("This route already exists.\n");
            return;
        }

        double distanceKm = Double.parseDouble(func.getStrInput("Enter distance in km: "));

        Route newRoute = new Route(routeService.nextId(), source, destination, distanceKm);
        routeService.addRoute(newRoute);

        routeService.saveData();
        System.out.println("Route added successfully!\n");
    }

}





                                                                            
                                                                            
                                                                            
 
