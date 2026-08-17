package metro.ticketing.app;

import java.util.Scanner;

<<<<<<< HEAD
import metro.ticketing.enums.UserRole;
import metro.ticketing.model.Passenger;
import metro.ticketing.services.StationService;
import metro.ticketing.services.UserService;
=======
import metro.ticketing.exception.InvalidLoginException;
import metro.ticketing.include.func;
import metro.ticketing.model.Admin;
import metro.ticketing.model.Passenger;
import metro.ticketing.model.User;
import metro.ticketing.services.*;

>>>>>>> origin/master
public class Main {

    static UserService uService= new UserService();
    static StationService stService = new StationService();
    static TrainService trService = new TrainService();
    static TicketService tkService = new TicketService(stService, uService);
    static RouteService rService = new RouteService(stService);
    static ReportService rpService = new ReportService(); // not done
    static PaymentService pService = new PaymentService(); // not done

    public static void main(String[] args) {
<<<<<<< HEAD
        UserService uService = new UserService();
        StationService stationService = new StationService();

        uService.viewAllUsers();

        Passenger newUser = new Passenger("blaaa", "registed", "register@gmail.com", "pass", UserRole.PASSENGER);

        uService.registerUser(newUser);

        uService.saveData();

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
                    System.out.println("Thank you for using Smart Metro Ticketing System.");
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
         * StationService getter not available yet.
         *
         * need StationService provide getStations(),
         * this section display all stations.
         */

        // 2. SELECT SOURCE

        System.out.print("\nSelect source station: ");
        int sourceChoice = input.nextInt();
        /*
         * TODO:
         *
         * Source station will be obtained from station list after StationService is completed.
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
         * Destination station will be obtained from StationService.
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
         * Fare calculation
         *
         * This should use the selected
         * - source
         * - destination
         * - ticket type
         */

        System.out.println("\nFare: RM " + fare);


        //6. PAYMENT
        System.out.println("\n======= Payment =======");
        System.out.println("Fare to pay: RM " + fare);
        System.out.print("Confirm payment? (Y/N): ");

        char confirmPayment = input.next().charAt(0);

        if (confirmPayment == 'N' || confirmPayment == 'n') {

            System.out.println("Payment cancelled.");
            return;
        }

        if (confirmPayment != 'Y' && confirmPayment != 'y') {

            System.out.println("Invalid choice. Payment cancelled.");
            return;
        }

        /*
        * TODO:
        *
        * Payment method / balance handling connected through UserService.
        */

        // 7. CHECK BALANCE
        /*
        * TODO:
        *
        * Check whether the passenger's balance is enough to pay. 
        *
        * If balance insufficient:
        *
        * System.out.println("Payment failed.");
        * System.out.println("Please top up your balance.");
        *
        * return;
        */

        // 8. DEDUCT BALANCE
        /*
        * TODO:
        *
        * If the balance is sufficient, UserService deduct the fare from passenger balance.
        */

        // 9. PAYMENT RESULT
        /*
        * TODO:
        *
        * If balance deduction is successful:
        *
        * System.out.println("Payment successful.");
        *
        * The ticket will then be created and printed.
        */

        // 10. CREATE TICKET
        /*
         * TODO:
         *
         * will connect after TicketService / Ticket module is ready.
         */

        // 11. PRINT TICKET
        /*
         * TODO:
         *
         * Print ticket information.
         *
         * Example:
         *
         * ticket.printTicket();
         */

        System.out.println("\nBuy Ticket completed.");
    }

    public static void viewTicket(Scanner input) {
        System.out.println("\n======= View Ticket =======");

    
        /*
        * VIEW TICKET FLOW
        *
        * 1. Get passenger's tickets
        * 2. Display ticket list
        * 3. Select a ticket
        * 4. Display ticket details
        * 5. Choose Use or Cancel
        */

    // 1. GET TICKETS    
    /*
    * TODO:
    *
    * Get all tickets that belongs to current passenger from TicketService.
    */
    
    // 2. DISPLAY TICKETS
    /*
    * TODO:
    *
    * Display passenger's tickets in formatted list.
    *
    * Example:
    *
    * 1. ID: T001 | Status: ACTIVE
    *    Source: Kampar
    *    Destination: KL Sentral
    *    Type: SINGLE
    *    Fare: RM5.00
    *
    * 2. ID: T002 | Status: CANCELLED
    *    Source: Kampar
    *    Destination: Ipoh
    *    Type: DAILY
    *    Fare: RM10.00
    */

    // 3. SELECT TICKET    
    /*
    * TODO:
    *
    * Ask passenger to select a ticket from the displayed ticket list.(type ticketID)
    *
    * Example:
    *
    * Select ticket: T001
    */

    // 4. DISPLAY TICKET DETAILS    
    /*
    * TODO:
    *
    * Display the selected ticket using:
    *
    * ticket.printTicket();
    *
    * Information displayed:
    * - Ticket ID
    * - Passenger
    * - Source
    * - Destination
    * - Ticket Type
    * - Fare
    * - Status
    */
    
    // 5. USE / CANCEL TICKET
    /*
    * TODO:
    *
    * Ask the passenger want to use/ cancel ticket. 
    *
    * 1. Use Ticket
    * 2. Cancel Ticket
    *
    * If Use:
    *     ticket.useTicket();
    *
    * If Cancel:
    *     ticket.cancelTicket();
    */

    System.out.println("\nView Ticket process completed.");
=======
        // welcome message to the system 
        //
        Scanner scanner = new Scanner(System.in);

        while(true) {
            mainMenu();

            char choice = func.getChoice();
            System.out.println("");

            switch (choice) {
                case '1':
                    User user = login();
                    
                    if (user instanceof Passenger) {
                        new PassengerUI((Passenger) user, uService, tkService).run();
                    } else if (user instanceof Admin) {
                        new AdminUI((Admin)user).run();
                    }

                    break;

                 case '2':
                    register();
                    break;

                 case '3':
                    System.out.println("Quitting SMART METRO TICKETING");
                    return; 
                default:
                    System.out.println("!!! INVALID INPUT !!!\n");
                    func.pause();
                    break;
            }
            func.clear();
        }

    }

    private static void mainMenu() {
        func.clear();
        func.printHeader("", '=');
        func.printHeader("", ' ');
        func.printHeader("WELCOME TO SMART METRO TICKETING", ' ');
        func.printHeader("", ' ');
        func.printHeader("", '=');

        System.out.println("");

        func.printHeader("Main Menu", '-');
        System.out.println("1. Login");
        System.out.println("2. Registration");
        System.out.println("3. Quit");
    }
    
    private static User login() {
        User userobj = null;

        func.printHeader("Login", '-');

        String email = func.getStrInput("Email     :");
        String pwd   = func.getStrInput("Password  :");

        try {
            userobj = uService.login(email, pwd); 
            System.out.println("");
        } catch (InvalidLoginException e) {
            System.out.println("Login failed !!!");
            func.pause();
        }
        
        return userobj;
    }

    private static void register() {
        // get input 
        String format = "%-12s:";

        String name  = null; 
        String pwd   = null;
        String email = null;

        do {
            name = func.getStrLnInput(String.format(format, "Name"));

            if (name.isEmpty()) {
                name = null;
                System.out.println("Enter a name!");
            }
        } while (name == null);

        do {
            email = func.getStrLnInput(String.format(format, "Email"));

            String[] emailEndings = {
                "@gmail.com",
                "@1utar.my",
                "@utar.my",
                "@yahoo.com",
                "@hotmail.com"
            };
            
            boolean validEmail = false;

            for(String ending :  emailEndings) {
                if (email.endsWith(ending)) {
                    if (!email.replaceFirst(ending, "").isEmpty()) {
                        validEmail = true;
                        break;
                    }
                }
            }

            if (uService.emailExists(email) || !validEmail) {
                email = null;
                System.out.println("Invalid Email!");
            }
        } while (email == null);

        do {
            pwd = func.getStrLnInput(String.format(format, "Password"));

            if (pwd.length() != 8) {
                pwd = null;
                System.out.println("Password must be 8 characters!");
            }
        } while (pwd == null);

        uService.registerUser(name, email, pwd);
        System.out.println("\nRegistrated for " + name +  "!!!\n");
        func.pause();
>>>>>>> origin/master
    }

}
