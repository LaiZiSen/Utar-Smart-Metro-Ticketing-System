package metro.ticketing.app;

import java.util.Scanner;
import java.util.ArrayList;

import metro.ticketing.model.Passenger;
import metro.ticketing.model.Ticket;
import metro.ticketing.enums.TicketStatus;
import metro.ticketing.include.func;
import metro.ticketing.payment.*;

import metro.ticketing.services.TicketService;
import metro.ticketing.services.UserService;
import metro.ticketing.services.PaymentService;

public class PassengerUI{
    private Passenger passenger;
    private UserService uService;
    private TicketService tkService;

    public PassengerUI(Passenger passenger, UserService uService, TicketService tkService) {
        this.passenger = passenger;
        this.uService = uService;
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
                    System.out.println("next ticket id is :  " + tkService.idIncrement()); // this is for testing only
                    func.pause();
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

            System.out.printf("%-5s | %-10s | %-25s | %-25s | %-12s | %-10s%n", "No.", "Ticket ID", "Source", "Destination", "Ticket Type", "Status");
            func.printHeader("", '-');

            for(int i = 0; i < myTickets.size(); i++){
                Ticket ticket = myTickets.get(i);

                System.out.printf("%-5d | %-10s | %-25s | %-25s | %-12s | %-10s%n", i + 1, 
                ticket.getTicketId(), ticket.getSource().getName(), ticket.getDestination().getName(), ticket.getTicketType(), ticket.getStatus());
            }

            System.out.println((myTickets.size() + 1) + ". Return");
            int choice = func.getIntInput("Enter number to view details: ");

            if(choice == myTickets.size() + 1){
                return;
            }

            if(choice < 1 || choice > myTickets.size()){
                System.out.println("INVALID INPUT");
                func.pause();
                continue;
            }

            Ticket selectedTicket = myTickets.get(choice - 1);

            ticketActionUI(selectedTicket);
        }
    }

private void ticketActionUI(Ticket ticket){
        while (true) {
            func.clear();
            ticket.printTicket();

            if(ticket.getStatus() == TicketStatus.USED || ticket.getStatus() == TicketStatus.CANCELLED){
                System.out.println();
                func.pause();
                return;
            }

            System.out.println();
            System.out.println("1. Use Ticket");
            System.out.println("2. Cancel Ticket");
            System.out.println("3. Return");
            System.out.println();

            char choice = func.getChoice();

            switch (choice) {
                case '1':
                    tkService.useTicket(ticket);
                    func.pause();
                    return;

                case '2':
                    System.out.println("! WARNING: No refund will be provided after cancellation !");
                    System.out.print("Are you sure you want to cancel this ticket? (Y/N): ");
                    char confirm = func.getChoice();

                    if (confirm == 'Y' || confirm == 'y') {
                        tkService.cancelTicket(ticket);
                        func.pause();
                        return;
                    } 
                    
                    else if (confirm == 'N' || confirm == 'n') {
                        System.out.println("Ticket cancellation aborted.");
                        func.pause();
                    } 
                    
                    else {
                        System.out.println("INVALID INPUT");
                        func.pause();
                    }

                    break;

                case '3':
                    return;

                default:
                    System.out.println("INVALID INPUT");
                    func.pause();
            }
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
