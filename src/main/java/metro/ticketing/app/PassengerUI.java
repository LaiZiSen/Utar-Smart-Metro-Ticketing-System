package metro.ticketing.app;

import java.util.Scanner;
import java.util.ArrayList;

import metro.ticketing.model.Passenger;
import metro.ticketing.model.Route;
import metro.ticketing.enums.TicketType;
import metro.ticketing.include.func;

import metro.ticketing.services.TicketService;
import metro.ticketing.services.RouteService;
import metro.ticketing.services.StationService;
import metro.ticketing.services.UserService;


public class PassengerUI{
    private Passenger passenger;
    private UserService uService;
    private StationService stService;
    private RouteService rService; 
    private TicketService tkService;

    public PassengerUI(Passenger passenger, UserService uService, StationService stService, RouteService rService, TicketService tkService) {
        this.passenger = passenger;
        this.uService = uService;
        this.stService = stService; 
        this.rService = rService; 
        this.tkService = tkService;
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);

        while(true) {
            passengerMenu();

            char choice = func.getChoice();
            System.out.println("");

            switch (choice) {
                case '1':
                    buyTicketUI(); 
                    break;
                
                case '2':
                    break;
                
                case '3':
                    passenger.viewProfile();
                    break;
                
                case '4':
                    func.clear(); 
                    balanceUI();
                    func.getChoice();

                    break;
                
                case '5':
                    System.out.println("Logging out of " + passenger.getName() + '\n');
                    func.pause();
                    return;

                default:
                    System.out.println("!!! INVALID INPUT !!!\n");
                    break;
            }
        }


    }

    private void buyTicketUI(){
        func.clear();
        func.printHeader("Buy Ticket", '-');

        Route route = selectRoute(); 

        if(route == null){
            return; 
        }

        TicketType type = selectTicketType(); 

        if(type == null){
            return; 
        }

        tkService.buyTicket(passenger, route, type);
    }

    private Route selectRoute(){
        System.out.println("Available Routes: ");
        System.out.println();

        ArrayList<Route> routes = rService.getAllRoutes(); 

        for(int i = 0; i < routes.size(); i++){

            System.out.println((i + 1) + ". " + routes.get(i).getSource().getName() + " --> " + routes.get(i).getDestination().getName() + " (" + routes.get(i).getDistanceKm() + " km)");
        }

        System.out.println();

        int choice = Integer.parseInt(func.getStrInput("Select Route: ")); 

        if(choice < 1 || choice > routes.size()){
            System.out.println("Invalid route selection. ");
            func.pause();
            return null; 
        }

        return routes.get(choice - 1); 
    }

    private TicketType selectTicketType(){
        System.out.println();
        System.out.println("Please Select Ticket Type: ");
        System.out.println("1. Single Ticket");
        System.out.println("2. Daily Ticket");
        System.out.println("3. Monthly Ticket");
        System.out.println();

        char choice = func.getChoice(); 

        switch (choice) {
            case '1':
                return TicketType.SINGLE; 

            case '2': 
                return TicketType.DAILY; 

            case '3': 
                return TicketType.MONTHLY; 
        
            default:
                System.out.println("Invalid ticket type. ");
                func.pause();
                return null; 
        }
    }
   
    private void passengerMenu() {
        func.clear();
        func.printHeader("",'=');
        func.printHeader("",' ');
        func.printHeader(("Welcome "+ passenger.getName()),' ');
        func.printHeader("",' ');
        func.printHeader("",'=');

        System.out.println("");

        func.printHeader("Passenger Menu", '-');
        System.out.println("1. Buy Ticket");
        System.out.println("2. View Ticket");
        System.out.println("3. Passenger Profile");
        System.out.println("4. Balance");
        System.out.println("5. Logout");
    }

    private void balanceUI() {
        passenger.viewBalance();

        func.printHeader("Balance Menu", '-');
        System.out.println("1. Reload Balance");
        System.out.println("2. Reload History");
        System.out.println("3. Return to Passenger menu");
        func.printHeader("", ' ');
    }
}
