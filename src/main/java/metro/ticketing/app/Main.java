package metro.ticketing.app;

import java.util.Scanner;

import metro.ticketing.model.User;
import metro.ticketing.model.Passenger;
import metro.ticketing.model.Admin;

import metro.ticketing.services.RouteService;
import metro.ticketing.services.StationService;
import metro.ticketing.services.TicketService;
import metro.ticketing.services.TrainService;
import metro.ticketing.services.UserService;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        UserService uService = new UserService();

        uService.viewAllUsers();
        
        Passenger newUser = new Passenger("blaaa", "registed", "register@gmail.com", "pass", UserRole.PASSENGER);

        uService.registerUser(newUser);

        uService.saveData();

        StationService stService = new StationService();
        UserService uServiceForTicket = new UserService();
        TicketService tService = new TicketService(stService, uServiceForTicket);
    
        tService.viewAllTicket();     
    }
}
