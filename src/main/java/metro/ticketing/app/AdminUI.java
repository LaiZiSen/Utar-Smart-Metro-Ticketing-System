package metro.ticketing.app;

import java.util.Scanner;

import metro.ticketing.model.Admin;
import metro.ticketing.include.func;

public class AdminUI{
    public static void AdminUI(Admin admin){
        func.printHeader("",'=');
        func.printHeader("",' ');
        func.printHeader(("Welcome "+ admin.getName()),' ');
        func.printHeader("",' ');
        func.printHeader("",'=');
        System.out.println("");

        boolean running = true;

        Scanner scanner = new Scanner(System.in);

        while(running) {
            adminMenu();

            char choice = func.getChoice();
            System.out.println("");

            switch (choice) {
                case '1':
                    break;
                
                case '2':
                    break;
                
                case '3':
                    break;
                
                case '4':
                    break;
               
                case '5':
                    break;

                case '6':
                    break;

                case '7':
                    System.out.println("Logging out of " + admin.getName() + '\n');
                    running = false;
                    break;

                default:
                    System.out.println("!!! INVALID INPUT !!!\n");
                    break;
            }
        }


    }
   
    private static void adminMenu() {
        func.printHeader("Admin Menu", '-');
        System.out.println("1. Route System");
        System.out.println("2. Train System");
        System.out.println("3. Station System");
        System.out.println("4. Ticket Rate");
        System.out.println("5. Report");
        System.out.println("6. Profile");
        System.out.println("7. Logout");

    }

}





                                                                            
                                                                            
                                                                            
 
