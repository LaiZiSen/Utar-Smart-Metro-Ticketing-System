package metro.ticketing.app;

import java.util.Scanner;

import metro.ticketing.include.func;
import metro.ticketing.model.Admin;
import metro.ticketing.model.Station;
import metro.ticketing.services.ReportService;
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
                    routeService.run();
                    break;
                
                case '2':
                    break;
                
                case '3':
                    stationMenu(stationService);
                    break;
                
                case '4':
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

}
