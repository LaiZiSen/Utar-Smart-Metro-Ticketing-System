package metro.ticketing.fare;

import metro.ticketing.enums.TicketType;

public interface FareCalculator {

    double calculateFare(double distance, TicketType ticketType);

}