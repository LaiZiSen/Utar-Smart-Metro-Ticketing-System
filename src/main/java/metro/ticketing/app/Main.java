package metro.ticketing.app;

import java.util.Scanner;

import metro.ticketing.enums.UserRole;
import metro.ticketing.model.Passenger;
import metro.ticketing.services.RouteService;
import metro.ticketing.services.StationService;
import metro.ticketing.services.TrainService;
import metro.ticketing.services.StationService;
import metro.ticketing.services.UserService;
import metro.ticketing.services.TicketService;
public class Main {
    public static void main(String[] args) {
        StationService stService = new StationService();
        StationService stationService = new StationService();
        TicketService tService = new TicketService(stService);
    
        tService.viewAllTicket();     

        Scanner input = new Scanner(System.in);

        boolean running = true;

        while (running) {

            System.out.println("\n======= Smart Metro Ticketing System =======");
            System.out.println("1. Buy Ticket");
            System.out.println("2. View Ticket");
            System.out.println("3. View Profile");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            int choice = input.nextInt();

            switch (choice) {

                case 1:
                    buyTicket(input, stationService, uService);
                    break;

                case 2:
                    viewTicket(input);
                    break;

                case 3:
                    break;

                case 4:
                    break;

                case 5:
                    System.out.println("Thank you for using Metro Ticketing System.");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. ");
            }
        }

        input.close();
    }

    // BUY TICKET

    public static void buyTicket(
            Scanner input,
            StationService stationService,
            UserService uService) {

        System.out.println("\n======= Buy Ticket =======");
        /*
         * BUY TICKET FLOW
         *
         * 1. Print all stations
         * 2. Select source
         * 3. Select destination
         * 4. Choose ticket type
         * 5. Calculate fare
         * 6. Payment
         * 7. Check balance
         * 8. Deduct balance
         * 9. Payment successful / failed
         * 10. Create ticket
         * 11. Print ticket
         */

        // 1. PRINT ALL STATIONS

        System.out.println("\n======= Stations =======");
        /*
         * TODO:
         *
         * StationService getter is not available yet.
         *
         * Later, when StationService provides getStations(),
         * this section can display all stations.
         */

        // 2. SELECT SOURCE

        System.out.print("\nSelect source station: ");
        int sourceChoice = input.nextInt();
        /*
         * TODO:
         *
         * Source station will be obtained from the
         * station list after StationService is completed.
         */

        System.out.println("Source station selected: " + sourceChoice);

        // 3. SELECT DESTINATION

        System.out.print("Select destination station: ");
        int destinationChoice = input.nextInt();

        if (sourceChoice == destinationChoice) {

            System.out.println("Source and destination cannot be the same.");

            return;
        }
        /*
         * TODO:
         *
         * Destination station will be obtained from
         * StationService.
         */

        System.out.println("Destination station selected: "+ destinationChoice);

        // 4. CHOOSE TICKET TYPE

        System.out.println("\n======= Ticket Type =======");
        System.out.println("1. Single");
        System.out.println("2. Daily");
        System.out.println("3. Monthly");

        System.out.print("Choose your ticket type: ");

        int ticketTypeChoice = input.nextInt();

        switch (ticketTypeChoice) {

            case 1:
                System.out.println("Single Ticket selected.");
                break;

            case 2:
                System.out.println("Daily Ticket selected.");
                break;

            case 3:
                System.out.println("Monthly Ticket selected.");
                break;

            default:
                System.out.println(
                    "Invalid ticket type."
                );
                return;
        }

        // 5. CALCULATE FARE

        double fare = 0.0;
        /*
         * TODO:
         *
         * Fare calculation will be connected later.
         *
         * This should use the selected:
         * - source
         * - destination
         * - ticket type
         */

        System.out.println("\nFare: RM " + fare);


        // 6. PAYMENT

        System.out.println("\n======= Payment =======");

        System.out.println("Fare to pay: RM " + fare);
        /*
         * TODO:
         *
         * Payment method / balance handling will be connected through UserService.
         */

        // 7. CHECK BALANCE
        /*
         * TODO:
         *
         * Later:
         *
         * Check passenger balance, is it enough to pay the fare.
         *
         * If insufficient:
         *
         * Payment failed.
         * Please top up your balance.
         */

        // 8. DEDUCT BALANCE
        /*
         * TODO:
         *
         * Balance deduction will be handled
         * by UserService.
         */

        // 9. PAYMENT RESULT
        /*
         * TODO:
         *
         * Later:
         *
         * if payment successful:
         *
         * System.out.println("Payment successful.");
         *
         * else:
         *
         * System.out.println("Payment failed.");
         * System.out.println("Please top up your balance.");
         */

        // 10. CREATE TICKET
        /*
         * TODO:
         *
         * will connect this after TicketService / Ticket module is ready.
         */

        // 11. PRINT TICKET
        /*
         * TODO:
         *
         * Print ticket will be connected later.
         */

        System.out.println("\nBuy Ticket process completed.");
    }

    public static void viewTicket(Scanner input) {

        System.out.println("\n======= View Ticket =======");
        /*
         * VIEW TICKET FLOW
         *
         * 1. Get passenger's tickets
         * 2. Display ticket list
         * 3. Select ticket
         * 4. Display ticket details
         */

        // 1. GET TICKET
        /*
         * TODO:
         *
         * Get tickets belonging to current passenger.
         *
         * TicketService.
         */

        // 2. DISPLAY TICKETS
        /*
         * TODO:
         *
         * Display all tickets that belong to the passenger.
         */

        // 3. SELECT TICKET
        /*
         * TODO:
         *
         * Ask passenger to select ticket they want to view.
         */

        // 4. DISPLAY TICKET DETAILS
        /*
         * TODO:
         *
         * Display:
         * Ticket ID
         * Source
         * Destination
         * Ticket Type
         * Fare
         * Status(Actice/Used/Cancelled)
         */


        System.out.println(
            "View Ticket process completed."
        );
    }
}
