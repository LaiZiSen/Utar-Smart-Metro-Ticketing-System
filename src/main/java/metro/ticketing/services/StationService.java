package metro.ticketing.services;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

import metro.ticketing.repository.FileManager;
import metro.ticketing.repository.JSONFileManager;
import metro.ticketing.model.Station;

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

        /// Write JSONArray into json file
        try {
            fileManager.saveData(inputJsonArray);
        } catch (Exception e) {
            System.out.println("Failed to save station data");
        }
    }

}
