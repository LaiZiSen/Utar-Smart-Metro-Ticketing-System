package metro.ticketing.model;

import org.json.JSONObject;

public class Train {

    private String trainId;
    private String trainName;
    private int capacity;

    public String getTrainId() {
        return trainId;
    }

    public String getTrainName() {
        return trainName;
    }

    public int getCapacity() {
        return capacity;
    }

    public Train(String trainId, String trainName, int capacity) {
        this.trainId = trainId;
        this.trainName = trainName;
        this.capacity = capacity;
    }

    public void displayTrain() {
        System.out.println("Train ID    : " + trainId);
        System.out.println("Train Name  : " + trainName);
        System.out.println("Capacity    : " + 0 + "/" + capacity);

        // zero is placeholder, use to test
        // later need to change to the total number of passenger already book this train
    }

    public static Train jsonToTrain(JSONObject json) {
        Train outputTrainObject = new Train(
            json.getString("trainId"),
            json.getString("trainName"),
            json.getInt("capacity")
        );
        return outputTrainObject;
    }

    public static JSONObject trainToJsonObject(Train trainData){
        JSONObject value = new JSONObject();
        value.put("trainId", trainData.getTrainId());
        value.put("trainName", trainData.getTrainName());
        value.put("capacity", trainData.getCapacity());
        return value;
    }
}

