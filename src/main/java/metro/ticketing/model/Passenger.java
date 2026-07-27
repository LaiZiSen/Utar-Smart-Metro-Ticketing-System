package metro.ticketing.model;

import metro.ticketing.enums.UserRole;

public class Passenger extends User{
    private double balance;
    
    public Passenger(String userId, String name, 
        String email, String password, UserRole role) {
        super(userId, name, email, password, role);
        this.balance = 0;
    }

    public Passenger(String userId, String name, 
        String email, String password, UserRole role, double balance) {
        super(userId, name, email, password, role);
        this.balance = balance;
    }

    public double getBalance() {
        return balance;
    }



}
