package metro.ticketing.fare;

import metro.ticketing.model.Route;
import metro.ticketing.enums.TicketType;

public interface FareCalculator {
    public double calculateFare(Route route, TicketType ticketType);
}
