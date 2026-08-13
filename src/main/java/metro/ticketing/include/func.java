package metro.ticketing.include;

import java.util.Scanner;

import java.lang.Math;

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

    public static String getStrLnInput(String ques) {
        Scanner scanner = new Scanner(System.in);
        String output;

        System.out.print(ques);
        output = scanner.nextLine();

        return output;
    }

    public static void printHeader(String msg, char filler) {
       // length 34 

        int stringLength = 38;
        int msgLength = msg.length();

        for (int i = 0; i < (stringLength - msgLength)/2; i++) {
            System.out.print(filler);
        }

        System.out.print(msg);

        for (int i = 0; i < Math.ceil(((double) stringLength - msgLength)/2); i++) {
            System.out.print(filler);
        }
        
        System.out.print("\n");

    }

    public static String formatId(String idHeader, int idNum, int idWidth) {
        String format = String.format("%s%d%s", "%s%0", idWidth, "d");

        return String.format(format, idHeader, idNum, idWidth);
    }
}
