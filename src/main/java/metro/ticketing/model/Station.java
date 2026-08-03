package metro.ticketing.model;

import org.json.JSONObject;

public class Station {
    private String stationId;
    private String name;
    private String location;

    public Station(String aStationId, String aName, String aLocation) {
        stationId = aStationId;
        name = aName;
        location = aLocation;
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
        Station outputStationObject = null;

        return outputStationObject;
    }

    public static JSONObject stationToJsonObject(Station stationData){
        JSONObject value = new JSONObject();

        return value;
    }

}
