package metro.ticketing.app;

import metro.ticketing.enums.UserRole;
import metro.ticketing.model.Passenger;
import metro.ticketing.services.RouteService;
import metro.ticketing.services.StationService;
import metro.ticketing.services.TrainService;
import metro.ticketing.services.UserService;

public class Main {
    public static void main(String[] args) {
        UserService uService = new UserService();

        uService.viewAllUsers();
        
        Passenger newUser = new Passenger("blaaa", "registed", "register@gmail.com", "pass", UserRole.PASSENGER);

        uService.registerUser(newUser);

        uService.saveData();

        // train test
        TrainService trainService = new TrainService();

        trainService.viewAllTrains();

        // station test
        StationService stationService = new StationService();

        stationService.viewAllStations();

        // route test
        RouteService routeService = new RouteService(stationService);

        routeService.viewAllRoutes();

    }
}