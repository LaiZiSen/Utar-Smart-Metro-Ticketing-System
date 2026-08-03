package metro.ticketing.repository;

import org.json.JSONArray;

public interface FileManager {
    public void saveData(JSONArray data) throws Exception;

    public JSONArray loadData () throws Exception;
}
