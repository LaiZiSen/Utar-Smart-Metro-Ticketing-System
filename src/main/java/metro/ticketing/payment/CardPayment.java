package metro.ticketing.payment;

public class CardPayment implements Payment{
    private String cardNumber;

    public CardPayment(String cardNumber) {
        this.cardNumber = new StringBuilder(cardNumber).insert(12, " ").insert(8, " ").insert(4, " ").toString();
    }


    public boolean pay(int amount) {
        // just say paid
        
        System.out.println("\nRM[" + amount + "]paid via Card (" + cardNumber + ')' + '\n');

        return true;
    }
}
