package metro.ticketing.model;

import org.json.JSONObject;

public class Station {
    private String stationId;
    private String name;
    private String location;

    public Station(String stationId, String name, String location) {
        this.stationId = stationId;
        this.name = name;
        this.location = location;
    }

    // Remove before finalizing
    public Station() {
        this("", "", "");
    }

    public String getStationId() {
        return this.stationId;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // TODO : Incomplete
    public void displayInfo(){

    }

    public static Station jsonToStation(JSONObject json) {
        Station outputStationObject = null;

        return outputStationObject;
    }

    public static JSONObject stationToJsonObject(Station stationData){
        JSONObject value = new JSONObject();

        return value;
    }


}
