package metro.ticketing.app;

import java.util.Scanner;

import metro.ticketing.model.Passenger;
import metro.ticketing.include.func;
import metro.ticketing.payment.*;

import metro.ticketing.services.TicketService;
import metro.ticketing.services.StationService;
import metro.ticketing.services.UserService;
import metro.ticketing.services.PaymentService;

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

    private String getCardNumber() {
        
        while(true) {
            String cardNumber = func.getStrInput("Enter card number:  ");

            if (cardNumber.length() != 16) {continue;};
            for(int i = 0; i < 16; i++) {
                if (!Character.isDigit(cardNumber.charAt(i))) {
                    continue;
                }
            }

            return cardNumber;

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
                    String cardNumber = getCardNumber();
                
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
            
            func.pause();
        }
    }
}
