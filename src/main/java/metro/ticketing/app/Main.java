package metro.ticketing.app;

import metro.ticketing.enums.UserRole;
import metro.ticketing.model.Passenger;
import metro.ticketing.services.UserService;

public class Main {
    public static void main(String[] args) {
        UserService uService = new UserService();

        uService.viewAllUsers();

        
        Passenger newUser = new Passenger("blaaa", "registed", "register@gmail.com", "pass", UserRole.PASSENGER);

        uService.registerUser(newUser);

        uService.saveData();
    }
}