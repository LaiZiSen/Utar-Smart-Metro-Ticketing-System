package metro.ticketing.repository;

import org.json.JSONArray;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONFileManager implements FileManager {
    
    public JSONArray loadData(String filename) throws Exception{
        String content = Files.readString(Paths.get(filename));
        
        JSONArray outputArray = new JSONArray(content);

        return outputArray;
    }

    public void saveData(JSONArray data, String filename) throws Exception{
        Files.writeString(
            Paths.get(filename),
            data.toString(4)
        );
    }
}
