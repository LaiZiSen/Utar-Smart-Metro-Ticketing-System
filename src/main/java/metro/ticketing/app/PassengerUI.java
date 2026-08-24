package metro.ticketing.app;

import java.util.Scanner;
import java.util.ArrayList;

import metro.ticketing.model.Passenger;
import metro.ticketing.model.Route;
import metro.ticketing.model.Ticket;
import metro.ticketing.enums.TicketType;
import metro.ticketing.include.func;
import metro.ticketing.payment.*;

import metro.ticketing.services.TicketService;
import metro.ticketing.services.RouteService;
import metro.ticketing.services.StationService;
import metro.ticketing.services.UserService;
import metro.ticketing.services.PaymentService;

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
                    viewTicketUI(); 
                    break;
                
                case '3':
                    passenger.viewProfile();
                    break;
                
                case '4':     
                    balanceUI();
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

    private void viewTicketUI(){
        while (true) {
             func.clear();
            func.printHeader("My Tickets", '=');

            ArrayList<Ticket> myTickets = tkService.getTicketsByPassenger(passenger); 

            if(myTickets.isEmpty()){
                System.out.println("No tickets found. ");
                func.printHeader("", '=');
                func.pause();
                return; 
            }

            System.out.printf("%-10s | %-12s | %-10s%n", "TicketID", "Ticket Type", "Ticket Status"); 
            func.printHeader("", '-');

            for(Ticket ticket : myTickets){
                System.out.printf("%-10s | %-12s | %-10s%n", ticket.getTicketId(), ticket.getTicketType(), ticket.getStatus()); 
            }

            func.printHeader("", '=');

            String ticketId = func.getStrInput("Enter Ticket ID to select ticket (or press 0 to return): "); 

            if(ticketId.equals(0)){
                return; 
            }

            Ticket selectedTicket = tkService.getTicketById(ticketId, passenger); 

            if(selectedTicket == null){
                System.out.println("Ticket not found. ");
                func.pause();
                continue; 
            }

            ticketActionUI(selectedTicket); 
        }
    }

    private void ticketActionUI(Ticket ticket){
        while (true) {
            func.clear();
            func.printHeader("Ticket Action", '-');

            System.out.println("Ticket ID     : " + ticket.getTicketId());
            System.out.println("Ticket Type   : " + ticket.getTicketType());
            System.out.println("Ticket Status : " + ticket.getStatus());
            System.out.println();
            System.out.println("1. View Detail");
            System.out.println("2. Use Ticket");
            System.out.println("3. Cancel Ticket");
            System.out.println("4. Return");
            System.out.println();
        
            char choice = func.getChoice();

            switch (choice) {
                case '1':
                    ticket.printTicket();
                    func.pause();
                    break;

                case '2':
                    tkService.useTicket(ticket);
                    func.pause();
                    break;

                case '3':
                    tkService.cancelTicket(ticket);
                    func.pause();
                    break;

                case '4':
                    return;

                default:
                    System.out.println("INVALID INPUT");
                    func.pause();
            }
        }
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

    private void balanceMenu(){
        func.printHeader("Balance Menu", '-');
        System.out.println("1. Reload Balance");
        System.out.println("2. Return to Passenger menu");
        func.printHeader("", ' ');
    }

    private void balanceUI() {
        while (true) {
            func.clear();
            passenger.viewBalance();

            balanceMenu();

            char choice = func.getChoice();

            switch (choice) {
                case '1':
                    // get reload amount
                    // get reload method 
                    // if using card, get card number
                    // execute payment
                    // if true, which will always be true, add amount to balance
                    reloadBalance();
                    break;

                case '2':
                    System.out.println("Returning to Passenger menu........");
                    System.out.println("");
                    func.pause();

                    return;
            }

        }
    }

    private void reloadBalance() {
        int reloadAmount = 0;
        
        reloadAmount = func.getIntInput("\nInput reload amount:  RM");
        Payment payment = null;

        System.out.println("Choose payment method");
        System.out.println("1. Cash payment");
        System.out.println("2. Card payment");

        boolean running = true;
        while(running) {
            char choice = func.getChoice();
            System.out.println("");

            switch (choice) {
                case '1':
                    payment = new CashPayment();
                    running = false;
                    break;
                
                case '2':
                    String cardNumber = func.getStrInput("Enter card number:  ");
                    payment = new CardPayment(cardNumber);
                    running = false;
                    break;

                default:
                    System.out.println("INVALID CHOICE!!!");
            }
        }

        PaymentService pService = new PaymentService();

        if (pService.processPayment((Payment)payment, reloadAmount)) {
            passenger.topupBalance(reloadAmount);
            
            uService.saveData();
            System.out.println("Passenger balance after topup: " + ((Passenger) uService.getUserById(passenger.getUserId())).getBalance());
            
            System.out.println("Topup successful, please check your balance!");
            func.pause();
        }
    }
}
