package metro.ticketing.payment;

public class CashPayment implements Payment{
    
    public boolean pay(int amount) {
        // adds amount to balance and print it
        System.out.println(amount + "paid via Cash");

        return true;

    }
}
