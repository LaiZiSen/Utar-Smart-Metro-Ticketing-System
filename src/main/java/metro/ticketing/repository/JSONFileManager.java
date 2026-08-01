package metro.ticketing.repository;

import org.json.JSONArray;

import java.nio.file.Files;
import java.nio.file.Paths;

public class JSONFileManager implements FileManager {
    private String filename;

    public JSONFileManager(String filename) {
        this.filename = filename;
    }


    public JSONArray loadData() throws Exception{
        String content = Files.readString(Paths.get(this.filename));
        
        JSONArray outputArray = new JSONArray(content);

        return outputArray;
    }

    public void saveData(JSONArray data) throws Exception{
        Files.writeString(
            Paths.get(this.filename),
            data.toString(4)
        );
    }
}
