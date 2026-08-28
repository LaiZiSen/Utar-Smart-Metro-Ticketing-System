package metro.ticketing.repository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class TXTFileManager{
    private String filename;

    public TXTFileManager(String filename){
        this.filename = filename;
    }

    public HashMap<String, Double> loadData() throws Exception{
        List<String> lines = Files.readAllLines(Paths.get(this.filename));
        HashMap<String, Double> rates = new HashMap<>();

        for(String line: lines){
            if(line.isBlank())
            continue;

            String[] parts = line.split("=", 2);
            rates.put(parts[0].trim(), Double.parseDouble(parts[1].trim()));
        }
        return rates;
    }

    public void saveData(HashMap<String, Double> rates) throws Exception{
        StringBuilder fileContent = new StringBuilder();

        for(String key: rates.keySet()){
            fileContent.append(key).append("=").append(rates.get(key)).append("\n");
        }
        Files.writeString(Paths.get(this.filename), fileContent.toString());
    }
}
