package metro.ticketing.app;

import java.util.Scanner;

import metro.ticketing.include.func;
import metro.ticketing.model.Admin;
import metro.ticketing.services.RateService;
import metro.ticketing.services.ReportService;
import metro.ticketing.services.RouteService;
import metro.ticketing.services.StationService;
import metro.ticketing.services.TicketService;
import metro.ticketing.services.TrainService;

public class AdminUI{
    private Admin admin;
    private TicketService tkService; 
    private StationService stationService;
    private RouteService routeService;
    private TrainService trainService;
    private RateService rateService;
    private ReportService rpService;


    public AdminUI(Admin admin, TicketService tkService, StationService stationService, RouteService routeService, TrainService trainService, RateService rateService, ReportService rpService) {
        this.admin = admin;
        this.tkService = tkService; 
        this.stationService = stationService;
        this.routeService = routeService;
        this.trainService  = trainService;
        this.rateService = rateService;
        this.rpService = rpService;
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);

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
                    rateService.run();
                    break;
               
                case '5':
                    rpService.run();

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
