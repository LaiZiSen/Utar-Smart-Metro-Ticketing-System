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
        return stationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void displayInfo(){
        System.out.println("Station ID  : " + stationId);
        System.out.println("Name        : " + name);
        System.out.println("Location    : " + location);
    }

    public static Station jsonToStation(JSONObject json) {
        Station outputStationObject = new Station(
            json.getString("stationId"),
            json.getString("name"),
            json.getString("location")
        );
        return outputStationObject;
    }

    public static JSONObject stationToJsonObject(Station stationData){
        JSONObject value = new JSONObject();
        value.put("stationId", stationData.getStationId());
        value.put("name", stationData.getName());
        value.put("location", stationData.getLocation());
        return value;
    }
}
