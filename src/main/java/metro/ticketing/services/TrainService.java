package metro.ticketing.services;

import metro.ticketing.model.Train;
import metro.ticketing.repository.FileManager;
import metro.ticketing.repository.JSONFileManager;

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

    public void viewAllTrains() {
        for (Train outputTrain : this.trains) {
            outputTrain.displayTrain();
            System.out.println();
        }
    }
}