package metro.ticketing.services;

import metro.ticketing.enums.UserRole;
import metro.ticketing.model.Passenger;
import metro.ticketing.model.User;
import metro.ticketing.repository.FileManager;
import metro.ticketing.repository.JSONFileManager;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserService {
    private HashMap<String, User> users = new HashMap<String, User>();
    private FileManager fileManager = new JSONFileManager(); 
    final String userJsonFile = "data/users.json";

    public UserService() {
        JSONArray userJsonArray = new JSONArray();

        try {
            userJsonArray = fileManager.loadData(userJsonFile);
        } catch (Exception e) {
            System.out.println("Error occured when loading user data from json");
        };

        for(int i = 0; i < userJsonArray.length(); i++) {
            JSONObject tempJsonObject = userJsonArray.getJSONObject(i);
            
            this.users.put(tempJsonObject.getString("name"), User.jsonToUser(tempJsonObject));
        }
    }

    public void saveData() {
        JSONArray inputJsonArray = new JSONArray();

        // Convert hashmap into JsonArray
        for (HashMap.Entry<String, User> entry : this.users.entrySet()) {
            User userData = entry.getValue();

            inputJsonArray.put(User.userToJsonObject(userData));
        }
        
        /// Write JSONArray into json file
        try {
            fileManager.saveData(inputJsonArray, userJsonFile);
        } catch (Exception e) {
            System.out.println("Failed to save user data");
        }
    }

    // TODO - NOT DONE
    public void registerUser(User registedUser) {
        this.users.put(registedUser.getName(), registedUser);
    }

    // TODO - NOT DONE
    public User login(String email, String password) {
        User user1 = new Passenger("", "", "", "", UserRole.PASSENGER, 0);

        return user1;
    }

    // TODO - NOT DONE
    public void viewAllUsers() {
        for (HashMap.Entry<String, User> entry : this.users.entrySet()) {
            User outputUser = entry.getValue();
            
            System.out.printf("%s\n", entry.getKey());
            System.out.printf("UserId: %s\n", outputUser.getUserId());
            System.out.printf("Name: %s\n", outputUser.getName());
            System.out.printf("Email: %s\n", outputUser.getEmail());
            System.out.printf("Pwd: %s\n", outputUser.getPassword());
            System.out.printf("Role: %s\n", outputUser.getRole());

            if (outputUser instanceof Passenger) {
                System.out.printf("Balance: %s\n", ((Passenger) outputUser).getBalance());
            }
        }
    }
}
