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
    
    public static char getChoice(String ques) {
        Scanner scanner = new Scanner(System.in);

        System.out.print(ques);
       
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

    public static int getIntInput(String ques) {
        Scanner scanner = new Scanner(System.in);
        int output = 0;

        do {
            String input = getStrInput(ques);
            System.out.println("");
            try {
                output = Integer.parseInt(input);
                if (output <= 0) {
                    throw new Exception();
                }
            } catch (Exception e) {

                System.out.println("INVALID INPUT!!!!");
                System.out.println("");
            }
        } while (output <= 0);
        
        return output;
    }
        
    public static double getDblInput(String ques) {
        Scanner scanner = new Scanner(System.in);
        double output = 0;

        do {
            String input = getStrInput(ques);
            System.out.println("");
            try {
                output = Double.parseDouble(input);
                if (output <= 0) {
                    throw new Exception();
                }
            } catch (Exception e) {

                System.out.println("INVALID INPUT!!!!");
                System.out.println("");
            }
        } while (output <= 0);
        
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

    public static void pause() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Press enter to continue...");
        scanner.nextLine();

        clear();
    }

    public static void clear() {
        System.out.print("\033[H\033[2J");
    }

    public static String formatId(String idHeader, int idNum, int idWidth) {
        String format = String.format("%s%d%s", "%s%0", idWidth, "d");

        return String.format(format, idHeader, idNum, idWidth);
    }
}
