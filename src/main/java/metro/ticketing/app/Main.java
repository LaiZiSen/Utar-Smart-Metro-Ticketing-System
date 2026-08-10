package metro.ticketing.app;

import metro.ticketing.enums.UserRole;
import metro.ticketing.model.Passenger;
import metro.ticketing.services.RouteService;
import metro.ticketing.services.StationService;
import metro.ticketing.services.TrainService;
import metro.ticketing.services.UserService;
import metro.ticketing.services.TicketService;

public class Main {
    public static void main(String[] args) {
        StationService stService = new StationService();
        TicketService tService = new TicketService(stService);
    
        tService.viewAllTicket();     
    }
}
