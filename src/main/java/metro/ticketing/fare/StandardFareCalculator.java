package metro.ticketing.fare;

import metro.ticketing.model.Route;
import metro.ticketing.enums.TicketType;

import metro.ticketing.services.RateService;

public class StandardFareCalculator {
    private RateService rateService;

    public StandardFareCalculator(RateService rateService) {
        this.rateService = rateService;
    }

    public double calculateFare(Route route, TicketType ticketType) {
        return route.getDistanceKm() * rateService.getRate(ticketType.toString());
    }
}
