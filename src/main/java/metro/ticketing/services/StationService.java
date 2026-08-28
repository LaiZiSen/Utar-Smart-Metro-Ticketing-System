package metro.ticketing.services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import metro.ticketing.repository.FileManager;
import metro.ticketing.repository.JSONFileManager;
import metro.ticketing.model.Station;
import metro.ticketing.include.func;

public class StationService {
    private ArrayList<Station> stations = new ArrayList<Station>();
    private FileManager fileManager = new JSONFileManager("data/station.json");

    public StationService() {
        JSONArray stationJsonArray = new JSONArray();

        try {
            stationJsonArray = fileManager.loadData();
        } catch (Exception e) {
            System.out.println("Error occured when loading user data from json");
        };

        for(int i = 0; i < stationJsonArray.length(); i++) {
            JSONObject tempJsonObj = stationJsonArray.getJSONObject(i);

            this.stations.add(Station.jsonToStation(tempJsonObj));
        }
    }

    public void saveData() {
        JSONArray inputJsonArray = new JSONArray();

        for(int i = 0; i < this.stations.size(); i++) {
            Station stationObj = stations.get(i);

            inputJsonArray.put(Station.stationToJsonObject(stationObj));
        }

        // Write JSONArray into json file
        try {
            fileManager.saveData(inputJsonArray);
        } catch (Exception e) {
            System.out.println("Failed to save station data");
        }
    }

    public void viewStations() {
        for (Station station : this.stations) {
            station.displayInfo();
            System.out.println();
        }
    }

    public Station getStationById(String stationId) {
        for (Station station : this.stations) {
            if (station.getStationId().equals(stationId)) {
                return station;
            }
        }
        return null;
    }

    public int stationCount(){
        return this.stations.size();
    }

    public Station stationAt(int index){
        if(index < 1 || index > this.stations.size()){
            return null;
        }
        return this.stations.get(index - 1);
    }

    public Station searchStation(String name){
        for(Station station: this.stations){
            if(station.getName().equalsIgnoreCase(name)){
                return station;
            }
        }
        return null;
    }

    public String nextId(){
        int max = 0;

        for(Station station: this.stations){
            int num = Integer.parseInt(station.getStationId().substring(2));

            if(num > max){
                max = num;
            }
        }
        return func.formatId("st", max + 1, 3);
    }

    public void addStation(Station station){
        this.stations.add(station);
    }

    public void run(){
        while(true){
            stationMenu();

            char choice = func.getChoice();
            System.out.println("");

            switch(choice){
                case '1':
                    viewStation();
                    break;

                case '2':
                    addStation();
                    break;
                
                case '3':
                    return;
                    
                default:
                    System.out.println("!!!INVALID INPUT!!!");
                    func.pause();
                    break;    
            }
        }
    }

    public void stationMenu(){
        func.clear();
        func.printHeader("Station System", '=');
        System.out.println("1. View Stations");
        System.out.println("2. Add Station");
        System.out.println("3. Back");
        func.printHeader("", '-');
    }

    public void viewStation(){
        func.clear();
        viewStations();
        func.pause();
    }

    public void addStation(){
        func.clear();

        String name = func.getStrLnInput("Enter station name: ");

        if(searchStation(name) != null){
            System.out.println("A station with this name already exists.\n");
            func.pause();
            return;
        }

        String location = func.getStrLnInput("Enter station location: ");

        while(true){
            System.out.println("1. Confirm");
            System.out.println("2. Cancel");
            char confirm = func.getChoice();

            if(confirm == '1'){
                break;
            }else if(confirm == '2'){
                System.out.println("Add station cancelled.\n");
                func.pause();
                return;
            }else{
                System.out.println("!!!INVALID INPUT!!!\n");
            }
        }
        Station newStation = new Station(nextId(), name, location);
        addStation(newStation);

        saveData();
        System.out.println("Station added successfully. \n");
        func.pause();
    }
}
