package metro.ticketing.services;

import java.util.HashMap;

import metro.ticketing.include.func;
import metro.ticketing.repository.TXTFileManager;


public class RateService {
    private HashMap<String, Double> rates;
    private TXTFileManager fileManager = new TXTFileManager("data/rate.txt");

    public RateService(){
        try {
            this.rates = fileManager.loadData();
        } catch (Exception e) {
            System.out.println("Error occured when loading rate data from txt");
            this.rates = new HashMap<>();
        }
    }

    public void run(){
        while(true){
            rateMenu();

            char choice = func.getChoice();

            switch(choice){
                case '1':
                    viewRates();
                    break;

                case '2':
                    adjustRate();
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

    public void rateMenu(){
        func.clear();

        func.printHeader("Ticket Rate", '=');

        System.out.println("1. View Rates");
        System.out.println("2. Adjust Rate");
        System.out.println("3. Return");

        func.printHeader("", '-');
    }

    public void viewRates(){
        func.clear();
        func.printHeader("Ticket Rate", '=');

        for(String ticketType: rates.keySet()){
            System.out.printf("%-10s: %s/ km%n", ticketType, rates.get(ticketType));
        }
        func.printHeader("", '-');
        func.pause();
    }

    public void adjustRate(){
        func.clear();
        func.printHeader("Adjust Rate", '-');

        String[] ticketTypes = rates.keySet().toArray(new String[0]);

        for(int i = 0; i<ticketTypes.length; i++){
            System.out.println((i+1) + ". " + ticketTypes[i]);
        }

        int back = ticketTypes.length + 1;
        System.out.println(back + ". Back");

        int choice = func.getIntInput("Enter choice: ");

        if(choice == back){
            return;
        }

        if(choice < 1 || choice > ticketTypes.length){
            System.out.println("!!!INVALID INPUT!!!");
            func.pause();
            return;
        }
        double newRate = func.getDblInput("Enter new rate: ");

        while(true){
            System.out.println("1. Confirm");
            System.out.println("2. Cancel");
            char confirm = func.getChoice();

            if(confirm == '1'){
                break;
            }else if(confirm == '2'){
                System.out.println("Adjust rate cancelled.");
                func.pause();
                return;
            }else{
                System.out.println("!!!INVALID INPUT!!!");
            }
        }
        rates.put(ticketTypes[choice -1], newRate);

        try{
            fileManager.saveData(rates);
            System.out.println("Rate updated successfully");
        }catch (Exception e){
            System.out.println("Failed to save rate data");
        }
        func.pause();
    }
    
}
