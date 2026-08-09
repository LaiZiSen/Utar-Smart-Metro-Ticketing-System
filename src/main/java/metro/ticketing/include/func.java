package metro.ticketing.include;

import java.util.Scanner;

public class func{
    public static char getChoice() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your choice:  ");
       
        char choice = ' ';

        choice = scanner.next().charAt(0);

        //try {
        //    choice = (char) System.in.read();
        //} catch (Exception e){}

        return choice;
    }
    
    public static String getStrInput(String ques) {
        Scanner scanner = new Scanner(System.in);
        String output;

        System.out.print(ques);
        output = scanner.next();

        return output;
    }
}
