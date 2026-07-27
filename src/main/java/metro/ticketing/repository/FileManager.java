package metro.ticketing.repository;

import org.json.JSONArray;

public interface FileManager {
    public void saveData(JSONArray data, String fileNameString) throws Exception;

    public JSONArray loadData (String fileName) throws Exception;
}
