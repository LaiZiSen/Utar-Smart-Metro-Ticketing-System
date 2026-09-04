package metro.ticketing.model;

import org.json.JSONObject;

import metro.ticketing.enums.TicketStatus;
import metro.ticketing.enums.TicketType;
import metro.ticketing.services.UserService;
import metro.ticketing.services.StationService;


public class Ticket {

    private String ticketId; 
    private Passenger passenger; 
    private Station source; 
    private Station destination; 
    private TicketType ticketType; 
    private TicketStatus status; 
    private double fare; 

    public Ticket(String ticketId, Passenger passenger, Station source, Station destination, TicketType ticketType, double fare){

        this.ticketId = ticketId; 
        this.passenger = passenger; 
        this.source = source; 
        this.destination = destination; 
        this.ticketType = ticketType; 
        this.fare = fare; 
        this.status = TicketStatus.ACTIVE; 
    }

    public String getTicketId(){
        return ticketId; 
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
        System.out.println("Ticket ID     : " + ticketId);
        System.out.println("Passenger     : " + passenger.getName());
        System.out.println("Source        : " + source.getName());
        System.out.println("Destination   : " + destination.getName());
        System.out.println("Ticket Type   : " + ticketType);
        System.out.println("Fare          : RM" + String.format("%.2f", fare));
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

    public void useTicket(){
        if(status == TicketStatus.CANCELLED){
            System.out.println("Cannot use a cancelled ticket.");
            return;
        }

        if(status == TicketStatus.USED){
            System.out.println("Ticket already used.");
            return;
        }

        status = TicketStatus.USED;
        System.out.println("Ticket used successfully.");
    }

    public static Ticket jsonToTicket (JSONObject json, StationService stService, UserService uService) {
        Ticket outputTicketObject = null;

        String userId = json.getString("passenger");

        User user = uService.getUserById(userId);

        String sourceId = json.getString("source");
        String destinationId = json.getString("destination");

        String typeStr = json.getString("ticketType");

        TicketType ticketType = null; 

        if(typeStr.equals(TicketType.SINGLE.toString())) {
            ticketType = TicketType.SINGLE;
        } else if(typeStr.equals(TicketType.DAILY.toString())) {
            ticketType = TicketType.DAILY;
        } else if(typeStr.equals(TicketType.MONTHLY.toString())) {
            ticketType = TicketType.MONTHLY;
        }

        outputTicketObject = new Ticket(
            json.getString("ticketId"),
            (Passenger) user,
            stService.getStationById(sourceId),
            stService.getStationById(destinationId),
            ticketType,
            json.getDouble("fare")
        );

        return outputTicketObject;
    }

    public static JSONObject ticketToJSONObject(Ticket ticketData) {
        JSONObject value = new JSONObject();

        value.put("ticketId", ticketData.getTicketId());
        value.put("passenger", ticketData.getPassenger().getUserId());
        value.put("source", ticketData.getSource().getStationId());
        value.put("destination", ticketData.getDestination().getStationId());
        value.put("ticketType", ticketData.getTicketType());
        value.put("TicketStatus", ticketData.getStatus());
        value.put("fare", ticketData.getFare());
    
        return value;
    }

}
