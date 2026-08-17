package metro.ticketing.model;

import metro.ticketing.enums.UserRole;
import metro.ticketing.include.func;

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

    public Passenger() {
        super();
        this.balance = 0;
    }

    public double getBalance() {
        return balance;
    }

    public void viewBalance() {
        func.printHeader("",'=');
        func.printHeader(("Your Balance is RM"+ this.balance),' ');
        func.printHeader("",'=');
        func.printHeader("",' ');
    }

    public void topupBalance(int amount) {
        this.balance = this.balance + amount;
    }

}
