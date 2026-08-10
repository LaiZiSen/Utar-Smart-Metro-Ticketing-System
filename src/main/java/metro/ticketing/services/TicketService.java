package metro.ticketing.services;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import metro.ticketing.repository.FileManager;
import metro.ticketing.repository.JSONFileManager;
import metro.ticketing.model.Ticket;
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

}
