package metro.ticketing.model;

import metro.ticketing.enums.UserRole;
import metro.ticketing.include.func;

import metro.ticketing.model.Route;
import metro.ticketing.services.TicketService;
import metro.ticketing.services.RouteService;

public class Passenger extends User{
    private double balance;
    
    public Passenger(String userId, String name, 
        String email, String password, UserRole role) {
        super(userId, name, email, password, role);
        this.balance = 0;
    }

    public Passenger(String userId, String name, 
        String email, String password, UserRole role, double balance) {
        super(userId, name, email, password, role);
        this.balance = balance;
    }

    public Passenger() {
        super();
        this.balance = 0;
    }

    public double getBalance() {
        return balance;
    }

    public void viewBalance() {
        func.printHeader("",'=');
        func.printHeader(("Your Balance is RM"+ this.balance),' ');
        func.printHeader("",'=');
        func.printHeader("",' ');
    }

    public void topupBalance(int amount) {
        this.balance = this.balance + amount;
    }

    public void buyTicket(RouteService rtService, TicketService tkService) {
        // find the route
        //
        // determine ticket type
        //
        // calculate fare price 
        //
        // confirm payment 
        //
        // reduce from user balance and create ticket 
       
        func.clear();
        func.printHeader("",'=');
        func.printHeader("Buy Ticket",' ');
        func.printHeader("",'=');
        func.printHeader("",' ');

        // find route
        
        Route route = rtService.findRoute();
        route.displayRoute();
    }

}
