package metro.ticketing.app;

import java.util.Scanner;

import metro.ticketing.include.func;
import metro.ticketing.model.Admin;
import metro.ticketing.model.Route;
import metro.ticketing.model.Station;
import metro.ticketing.model.Train;
import metro.ticketing.services.ReportService;
import metro.ticketing.services.RouteService;
import metro.ticketing.services.StationService;
import metro.ticketing.services.TrainService;
import metro.ticketing.services.RateService;

public class AdminUI{
    private Admin admin;

    public AdminUI(Admin admin) {
        this.admin = admin;
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        StationService stationService = new StationService();
        RouteService routeService = new RouteService(stationService);
        TrainService trainService = new TrainService();

        while(true) {
            adminMenu();

            char choice = func.getChoice();
            System.out.println("");

            switch (choice) {
                case '1':
                    routeMenu(routeService, stationService);
                    break;
                
                case '2':
                    trainMenu(trainService);
                    break;
                
                case '3':
                    stationMenu(stationService);
                    break;
                
                case '4':
                    new RateService().run();
                    break;
               
                case '5':
                    new ReportService().run();

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

        int cancel = routeService.routeCount() + 1;
        System.out.printf("%-5s| Cancel%n", cancel + ".");

        int choice = func.getIntInput("Enter number to view details: ");

        if(choice == cancel){
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
            if(routeService.isSame(source, destination)){
                System.out.println("!!!INVALID INPUT!!! Destination cannot be the same as source station.\n");
                continue;
            }
            break;
        }

        if(routeService.isDuplicate(source, destination)){
            System.out.println("This route already exists.\n");
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
                return;
            }else{
                System.out.println("!!!INVALID INPUT!!!\n");
            }
        }
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
        String name = func.getStrLnInput("Enter station name: ");

        if(stationService.searchStation(name) != null){
            System.out.println("A station with this name already exists.\n");
            return;
        }

        String location = func.getStrLnInput("Enter station location: ");

        while(true){
            System.out.println("1. Confirm");
            System.out.println("2. Cancel");
            char confirm = func.getChoice();

            if(confirm == '1'){
                break;
            }else if(confirm == '2'){
                System.out.println("Add station cancelled.\n");
                return;
            }else{
                System.out.println("!!!INVALID INPUT!!!\n");
            }
        }
        Station newStation = new Station(stationService.nextId(), name, location);
        stationService.addStation(newStation);

        stationService.saveData();
        System.out.println("Station added successfully.\n");
    }

    private static void trainMenu(TrainService trainService){
        while(true){
            func.printHeader("Train System", '-');
            System.out.println("1. View Trains");
            System.out.println("2. Add Train");
            System.out.println("3. Back");

            char choice = func.getChoice();
            System.out.println("");

            switch(choice){
                case '1':
                    trainService.viewTrains();
                    break;
                
                case '2':
                    addTrain(trainService);
                    break;

                case '3':
                    return;
                    
                default:
                    System.out.println("!!!INVALID INPUT!!!\n");    
            }
        }
    }

    private static void addTrain(TrainService trainService){
        String name = func.getStrLnInput("Enter train name: ");

        if(trainService.isDuplicate(name)){
            System.out.println("A train with this name already exists.\n");
            return;
        }

        int capacity = func.getIntInput("Enter train capcity: ");

        while(true){
            System.out.println("1. Confirm");
            System.out.println("2. Cancel");
            char confirm = func.getChoice();

            if(confirm == '1'){
                break;
            }else if(confirm == '2'){
                System.out.println("Add train cancelled.\n");
                return;
            }else{
                System.out.println("!!!INVALID INPUT!!!\n");
            }
        }

        Train newTrain = new Train(trainService.nextId(), name, capacity);
        trainService.addTrain(newTrain);

        trainService.saveData();
        System.out.println("Train added successfully\n");
    }
}
