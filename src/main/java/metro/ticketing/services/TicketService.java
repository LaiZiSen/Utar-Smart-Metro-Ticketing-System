package metro.ticketing.services;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import metro.ticketing.repository.FileManager;
import metro.ticketing.repository.JSONFileManager;

import metro.ticketing.model.Ticket;
import metro.ticketing.model.Passenger;
import metro.ticketing.model.Route;
import metro.ticketing.enums.TicketStatus;
import metro.ticketing.enums.TicketType;

import metro.ticketing.include.func;

import metro.ticketing.services.StationService;
import metro.ticketing.services.UserService;

public class TicketService {
    private ArrayList<Ticket> tickets = new ArrayList<Ticket>();
    private FileManager fileManager = new JSONFileManager("data/ticket.json");

    public void viewAllTicket() {
        for (Ticket ticketData: this.tickets) {
            ticketData.printTicket();
            System.out.println();
        }
    }

    public TicketService(StationService stService, UserService uService) {
        JSONArray ticketJsonArray = new JSONArray();

        try {
            ticketJsonArray = fileManager.loadData();
        } catch (Exception e) {
            System.out.println("Error occured when loading ticket data from json");
        };

        for (int i = 0; i < ticketJsonArray.length(); i++) {
            JSONObject tempJsonObj = ticketJsonArray.getJSONObject(i);

            this.tickets.add(Ticket.jsonToTicket(tempJsonObj, stService, uService));
        }
    }

    public void saveData(StationService stService) {
        JSONArray inputJsonArray = new JSONArray();

        for (Ticket ticket: this.tickets) {
            inputJsonArray.put(Ticket.ticketToJSONObject(ticket));
        }
        
        try {
            fileManager.saveData(inputJsonArray);
        } catch (Exception e) {
            System.out.println("Failed to save ticket data");
        }
    }

    public String idIncrement() {
        int idIncrement = 0;

        for (Ticket ticket: this.tickets) {
            String tempId = ticket.getTicketId();
            int tempIdNum = Integer.parseInt(tempId.substring(2));

            if(tempIdNum>idIncrement) {
                    idIncrement = Integer.parseInt(tempId.substring(2));
            }
        }

        return func.formatId("tk", (idIncrement+1), 4);
    }

    public void buyTicket(Passenger passenger, Route route, TicketType type) {
        // Passenger newPassenger = new Passenger(idIncriment(), name, email, password, UserRole.PASSENGER);

        // this.users.put(newPassenger.getEmail(), newPassenger);
        // this.saveData();
    }

    public ArrayList<Ticket> getTicketsByPassenger(Passenger passenger){
        ArrayList<Ticket> passengerTickets = new ArrayList<Ticket>(); 

        for(Ticket ticket : tickets){
            if(ticket.getPassenger().getUserId().equals(passenger.getUserId())){
                passengerTickets.add(ticket); 
            }
        }
        
        return passengerTickets; 
    }

    public Ticket getTicketById(String ticketId, Passenger passenger){
        for(Ticket ticket : tickets){
            if(ticket.getTicketId().equals(ticketId) && ticket.getPassenger().getUserId().equals(passenger.getUserId())){
                return ticket;
            }
        }

        return null;
    }

    public void useTicket(Ticket ticket){
        if(ticket.getStatus() == TicketStatus.CANCELLED){
            System.out.println("Cannot use cancelled ticket. ");
            return; 
        }

        if(ticket.getStatus() == TicketStatus.USED){
            System.out.println("Ticket already used. ");
            return; 
        }

        ticket.useTicket(); 
        saveData(null);

        System.out.println("Ticket used successfully. ");
    }

    public void cancelTicket(Ticket ticket){
        if(ticket.getStatus() == TicketStatus.USED){
            System.out.println("Cannot cancel used ticket. ");
            return; 
        }

        if(ticket.getStatus() == TicketStatus.CANCELLED){
            System.out.println("Ticket already cancelled. ");
            return; 
        }

        ticket.cancelTicket(); 
        saveData(null);
    }
}
