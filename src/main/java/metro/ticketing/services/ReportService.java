package metro.ticketing.services;

import metro.ticketing.model.Ticket;
import metro.ticketing.include.func;

import java.util.ArrayList;

public class ReportService {
    ArrayList<Ticket> tickets;

    public ReportService(TicketService tkService) {
        this.tickets = tkService.getAllTickets(); 
    }

    public void run(){
        while(true){
            reportMenu();

            char choice = func.getChoice(); 

            switch(choice){
                case '1': 
                    showTotalSales();
                    break; 

                case '2': 
                    showTotalRevenue();
                    break; 

                case '3': 
                    showCancelledTickets();
                    break; 

                case '4': 
                    return; 
                
                default: 
                    System.out.println(" INVALID INPUT ");
                    func.pause();
                    break; 
            }
        }

    }

    public void reportMenu(){
        func.clear();

        func.printHeader("Report Menu", '=');

        System.out.println("1. Total Sales");
        System.out.println("2. Total Revenue");
        System.out.println("3. Cancelled Tickets");
        System.out.println("4. Return");

        func.printHeader(" ", '-'); 
    }

    public void showTotalSales() {
        int singleCount = 0; 
        int dailyCount = 0; 
        int monthlyCount = 0; 

        for(Ticket ticket : tickets){
            switch (ticket.getTicketType()) {
                case SINGLE:
                    singleCount++; 
                    break;

                case DAILY: 
                    dailyCount++; 
                    break; 

                case MONTHLY: 
                    monthlyCount++; 
                    break; 
            
            }
        }

        int total = singleCount + dailyCount + monthlyCount; 

        func.clear();
        func.printHeader("Total Sales", '=');

        System.out.printf("Single Ticket    : " + singleCount);
        System.out.printf("Daily Ticket     : " + dailyCount);
        System.out.printf("Monthly Ticket   : " + monthlyCount);
        System.out.println("------------------------------");
        System.out.printf("Total Tickets    : " + total);

        func.printHeader(" ", '-');
        func.pause();
    }

    public void showTotalRevenue() {
        double singleRevenue = 0; 
        double dailyRevenue = 0; 
        double monthlyRevenue = 0; 

        for(Ticket ticket : tickets){
            switch (ticket.getTicketType()) {
                case SINGLE:
                    singleRevenue += ticket.getFare(); 
                    break;

                case DAILY: 
                    dailyRevenue += ticket.getFare(); 
                    break; 

                case MONTHLY: 
                    monthlyRevenue += ticket.getFare(); 
                    break; 
            }
        }

        double totalRevenue = singleRevenue + dailyRevenue + monthlyRevenue; 

        func.clear();
        func.printHeader("Total Revenue", '=');

        System.out.printf("Single Ticket    : RM%.2f%n", singleRevenue); 
        System.out.printf("Daily Ticket     : RM%.2f%n" , monthlyRevenue); 
        System.out.printf("Monthly Ticket   : RM%.2f%n", monthlyRevenue); 
        System.out.println("------------------------------");
        System.out.printf("Total Revenue    : RM%.2f%n", totalRevenue); 

        func.printHeader(" ", '-');
        func.pause();

    }

    public void showCancelledTickets() {
        // show total ticket canceled 
        // seperated by category and total too 
    } 
}
