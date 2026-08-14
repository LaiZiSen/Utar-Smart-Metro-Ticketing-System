package metro.ticketing.app;

import java.util.Scanner;

import metro.ticketing.exception.InvalidLoginException;
import metro.ticketing.include.func;
import metro.ticketing.model.Admin;
import metro.ticketing.model.Passenger;
import metro.ticketing.model.User;
import metro.ticketing.services.UserService;

public class Main {

    static UserService uService= new UserService();

    public static void main(String[] args) {
        // welcome message to the system 
        //
        func.printHeader("", '=');
        func.printHeader("", ' ');
        func.printHeader("WELCOME TO SMART METRO TICKETING", ' ');
        func.printHeader("", ' ');
        func.printHeader("", '=');

        System.out.println("");

        boolean running = true;

        Scanner scanner = new Scanner(System.in);

        while(running) {
            mainMenu();

            char choice = func.getChoice();
            System.out.println("");

            switch (choice) {
                case '1':
                    User user = login();
                    
                    if (user instanceof Passenger) {
                        PassengerUI.PassengerUI((Passenger)user);
                    } else if (user instanceof Admin) {
                        AdminUI.AdminUI((Admin)user);
                    }

                    break;

                 case '2':
                    System.out.println("Let's Register");
                    System.out.println("");

                    register();
                    
                    break;

                 case '3':
                    System.out.println("Quitting SMART METRO TICKETING");
                    running = false;
                    break; 
                default:
                    System.out.println("!!! INVALID INPUT !!!\n");
                    break;
            }

        }

    }

    private static void mainMenu() {
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
    }

}
