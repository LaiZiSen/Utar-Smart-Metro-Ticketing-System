package metro.ticketing.services;

import metro.ticketing.model.Train;
import metro.ticketing.repository.FileManager;
import metro.ticketing.repository.JSONFileManager;
import metro.ticketing.include.func;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;

public class TrainService {
    private ArrayList<Train> trains = new ArrayList<Train>();
    private FileManager fileManager = new JSONFileManager("data/train.json");

    public TrainService() {
        JSONArray trainJsonArray = new JSONArray();

        try {
            trainJsonArray = fileManager.loadData();
        } catch (Exception e) {
            System.out.println("Error occured when loading train data from json");
        };

        for(int i = 0; i < trainJsonArray.length(); i++) {
            JSONObject tempJsonObject = trainJsonArray.getJSONObject(i);

            this.trains.add(Train.jsonToTrain(tempJsonObject));
        }
    }

    public void saveData() {
        JSONArray inputJsonArray = new JSONArray();

        for (Train trainData: this.trains) {
            inputJsonArray.put(Train.trainToJsonObject(trainData));
        }
        
        try {
            fileManager.saveData(inputJsonArray);
        } catch (Exception e) {
            System.out.println("Failed to save train data");
        }
    }

    public void viewTrains() {
        for (Train outputTrain : this.trains) {
            outputTrain.displayTrain();
            System.out.println();
        }
    }

    public boolean isDuplicate(String trainName){
        for(Train train: this.trains){
            if(train.getTrainName().equalsIgnoreCase(trainName)){
                return true;
            }
        }
        return false;
    }

    public String nextId(){
        int max = 0;

        for(Train train: this.trains){
            int num = Integer.parseInt(train.getTrainId().substring(2));

            if(num > max){
                max = num;
            }
        }
        return func.formatId("tr", max + 1, 3);
    }

    public void addTrain(Train train){
        this.trains.add(train);
    }
}