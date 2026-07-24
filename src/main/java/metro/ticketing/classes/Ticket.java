package metro.ticketing.classes;

import metro.ticketing.enums.TicketStatus;
import metro.ticketing.enums.TicketType;

public class Ticket {

    private String ticketID; 
    private Passenger passenger; 
    private Station source; 
    private Station destination; 
    private TicketType ticketType; 
    private TicketStatus status; 
    private double fare; 

    public Ticket(String ticketID, Passenger passenger, Station source, Station destination, TicketType ticketType, double fare){

        this.ticketID = ticketID; 
        this.passenger = passenger; 
        this.source = source; 
        this.destination = destination; 
        this.ticketType = ticketType; 
        this.fare = fare; 
        this.status = TicketStatus.ACTIVE; 
    }

    public String getTicketId(){
        return ticketID; 
    }

    public Passenger getPassenger(){
        return passenger; 
    }

    public Station getSource(){
        return source; 
    }

    public Station getDestination(){
        return destination; 
    }

    public TicketType getTicketType(){
        return ticketType; 
    }

    public TicketStatus getStatus(){
        return status; 
    }

    public double getFare(){
        return fare; 
    }

    public void printTicket(){

        System.out.println("======= TICKET INFORMATION =======");
        System.out.println("Ticket ID     : " + ticketID);
        System.out.println("Passenger     : " + passenger);
        System.out.println("Source        : " + source);
        System.out.println("Destination   : " + destination);
        System.out.println("Ticket Type   : " + ticketType);
        System.out.println("Fare          : RM" + fare);
        System.out.println("Status        : " + status);
        System.out.println("==================================");
    }

    public void cancelTicket(){

        if (status == TicketStatus.CANCELLED) {
            System.out.println("Ticket already cancelled. ");
            return; 
            
        }

        status = TicketStatus.CANCELLED; 

        System.out.println("Ticket cancelled successfully. ");
    }

}
