package metro.ticketing.classes;

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

    public Train(String aTrainId, String aTrainName, int aCapacity) {
        trainId = aTrainId;
        trainName = aTrainName;
        capacity = aCapacity;
    }

    public void displayTrain() {
        System.out.println("Train ID    : " + trainId);
        System.out.println("Train Name  : " + trainName);
        System.out.println("Capacity    : " + 0 + "/" + capacity);

        // zero is placeholder, use to test
        // later need to change to the total number of passenger already book this train
    }
}
