package metro.ticketing.app;

import java.util.Scanner;

import metro.ticketing.include.func;
import metro.ticketing.model.Passenger;

import metro.ticketing.services.TicketService;
import metro.ticketing.services.StationService;
import metro.ticketing.services.UserService;


public class PassengerUI{
    private Passenger passenger;
    private UserService uService;
    private StationService stService;
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
