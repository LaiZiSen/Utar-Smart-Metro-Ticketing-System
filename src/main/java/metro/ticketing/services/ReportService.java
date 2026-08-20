package metro.ticketing.services;

import metro.ticketing.model.Ticket;
import metro.ticketing.include.func;

import java.util.ArrayList;

public class ReportService {
    ArrayList<Ticket> tickets;

    public ReportService() {
        // initiate report service values
    }

    public void run(){
        // run report service ui
        System.out.println("Report Service is unavailable for now \n");
        System.out.println("Please return to admin page\n");

        func.pause();

    
    }

    public void reportMenu(){
        // print report menu 
    }

    public void showTotalSales() {
        // show total sales 
        // total tickets sold, from each category
    }

    public void showTotalRevenue() {
        // show total revenue 
        // ringgit, from total, and seperated by category   
    }

    public void showCanceledTickets() {
        // show total ticket canceled 
        // seperated by category and total too 
    } 
}
