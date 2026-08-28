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

    public void run(){
        while(true){
            trainMenu();

            char choice = func.getChoice();
            System.out.println("");

            switch(choice){
                case '1':
                    viewTrain();
                    break;

                case '2':
                    addTrain();
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

    public void trainMenu(){
        func.clear();
        func.printHeader("Train System", '=');
        System.out.println("1. View Trains");
        System.out.println("2. Add Train");
        System.out.println("3. Back");
        func.printHeader("", '-');
    }

    public void viewTrain(){
        func.clear();
        viewTrains();
        func.pause();
    }

    public void addTrain(){
        func.clear();

        String name = func.getStrLnInput("Enter train name: ");

        if(isDuplicate(name)){
            System.out.println("A train with this name already exists.\n");
            func.pause();
            return;
        }

        int capacity = func.getIntInput("Enter train capcity: ");

        while(true){
            System.out.println("1. Confirm");
            System.out.println("2. Cancel");
            char confirm = func.getChoice();

            if(confirm == '1'){
                break;
            }else if(confirm == '2'){
                System.out.println("Add train cancelled.\n");
                func.pause();
                return;
            }else{
                System.out.println("!!!INVALID INPUT!!!\n");
            }
        }

        Train newTrain = new Train(nextId(), name, capacity);
        addTrain(newTrain);

        saveData();
        System.out.println("Train added successfully\n");
        func.pause();
    }
}