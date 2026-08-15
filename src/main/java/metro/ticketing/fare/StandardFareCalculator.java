package metro.ticketing.fare;

import metro.ticketing.enums.TicketType;

public class StandardFareCalculator implements FareCalculator {

    private static final double SINGLE_RATE  = 0.60; 
    private static final double DAILY_RATE = 1.10; 
    private static final double MONTHLY_RATE = 11.00; 

    @Override
    public double calculateFare(double distance, TicketType ticketType){

        double rate; 

        switch (ticketType) {
            case SINGLE:
                rate = SINGLE_RATE; 
                break;

            case DAILY: 
                rate = DAILY_RATE; 
                break; 

            case MONTHLY: 
                rate = MONTHLY_RATE; 
                break; 
        
            default:
                    throw new IllegalArgumentException("Invalid ticket type. "); 
        }

        return distance * rate; 
    }

}
