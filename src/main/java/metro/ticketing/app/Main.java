package metro.ticketing.app;

import java.util.Scanner;

import metro.ticketing.enums.UserRole;
import metro.ticketing.model.User;
import metro.ticketing.model.Passenger;
import metro.ticketing.services.UserService;
import metro.ticketing.include.func;
import metro.ticketing.exception.InvalidLoginException;

public class Main {

    static UserService userService = new UserService();

    public static void main(String[] args) {
        // welcome message to the system 
        //
        System.out.println("======================================");
        System.out.println("");
        System.out.println("   WELCOME TO SMART METRO TICKETING   ");
        System.out.println("");
        System.out.println("======================================");
        System.out.println("");

        boolean running = true;

        Scanner scanner = new Scanner(System.in);

        while(running) {
            mainMenu();

            char choice = func.getChoice();
            System.out.println("");

            switch (choice) {
                case '1':
                    login(); 
                    break;

                 case '2':
                    System.out.println("Let's Register");
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
        System.out.println("---------------Main Menu--------------");
        System.out.println("1. Login");
        System.out.println("2. Registration");
        System.out.println("3. Quit");
    }
    
    private static User login() {
        User userobj = null;

        System.out.println("-----------------Login----------------");

        String email = func.getStrInput("Email     :");
        String pwd   = func.getStrInput("Password  :");

        try {
            userobj = userService.login(email, pwd); 
            System.out.println(userobj.getName());
        } catch (InvalidLoginException e) {
            System.out.println("Login failed !!!!!!!!!!!!!!!!!!");
        }
        
        return userobj;
    }

}
