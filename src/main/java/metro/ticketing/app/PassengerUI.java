package metro.ticketing.app;

import java.util.Scanner;

import metro.ticketing.model.Passenger;
import metro.ticketing.include.func;

public class PassengerUI{
    public static void PassengerUI(Passenger passenger){
        func.printHeader("",'=');
        func.printHeader("",' ');
        func.printHeader(("Welcome "+ passenger.getName()),' ');
        func.printHeader("",' ');
        func.printHeader("",'=');

        System.out.println("");

        boolean running = true;

        Scanner scanner = new Scanner(System.in);

        while(running) {
            passengerMenu();

            char choice = func.getChoice();
            System.out.println("");

            switch (choice) {
                case '1':
                    break;
                
                case '2':
                    break;
                
                case '3':
                    passenger.viewProfile()
                    break;
                
                case '4':
                    break;
                
                case '5':
                    System.out.println("Logging out of " + passenger.getName() + '\n');
                    running = false;
                    break;

                default:
                    System.out.println("!!! INVALID INPUT !!!\n");
                    break;
            }
        }


    }
   
    private static void passengerMenu() {
        func.printHeader("Passenger Menu", '-');
        System.out.println("1. Buy Ticket");
        System.out.println("2. View Ticket");
        System.out.println("3. Passenger Profile");
        System.out.println("4. Balance");
        System.out.println("5. Quit");


    }

}





                                                                            
                                                                            
                                                                            
                                                                             
