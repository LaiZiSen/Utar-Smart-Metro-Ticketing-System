package metro.ticketing.exception;

public class TicketNotFoundException extends Exception {
    public TicketNotFoundException() {
        super("!!! Ticket Not Found !!!");
    }
}
