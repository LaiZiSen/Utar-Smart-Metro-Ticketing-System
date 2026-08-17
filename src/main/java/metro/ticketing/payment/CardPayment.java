package metro.ticketing.payment;

public class CardPayment implements Payment{
    private String cardNumber;

    public CardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }


    public boolean pay(int amount) {
        // just say paid
        
        System.out.println(amount + "paid via Card (" + cardNumber + ')');

        return true;
    }
}
