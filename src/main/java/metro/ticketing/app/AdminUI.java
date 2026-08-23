package metro.ticketing.app;

import java.util.Scanner;

import metro.ticketing.include.func;
import metro.ticketing.model.Admin;
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
                    routeService.run();
                    break;
                
                case '2':
                    trainService.run();
                    break;
                
                case '3':
                    stationService.run();
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
}
