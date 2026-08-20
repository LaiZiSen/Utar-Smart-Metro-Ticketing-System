package metro.ticketing.repository;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class TXTFileManager implements FileManager {
    private String filename;

    public TXTFileManager(String filename){
        this.filename = filename;
    }

    public JSONArray loadData() throws Exception{
        List<String> lines = Files.readAllLines(Paths.get(this.filename));
        JSONArray outputArray = new JSONArray();

        for(String line: lines){
            if(line.isBlank()) 
            continue;

            String[] parts = line.split("=",2);
            JSONObject entry = new JSONObject();
            entry.put("ticketType", parts[0].trim());
            entry.put("rate", Double.parseDouble(parts[1].trim()));
            outputArray.put(entry);
        }
        return outputArray;
    }

    public void saveData(JSONArray data) throws Exception{
        StringBuilder content = new StringBuilder();

        for(int i=0; i<data.length(); i++){
            JSONObject entry = data.getJSONObject(i);
            content.append(entry.getString("ticketType")).append("=").append(entry.getDouble("rate")).append("\n");
        }
        Files.writeString(Paths.get(this.filename), content.toString());
    }
}
