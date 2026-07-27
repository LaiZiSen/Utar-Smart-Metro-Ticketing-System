package metro.ticketing.services;

import metro.ticketing.enums.UserRole;
import metro.ticketing.model.Admin;
import metro.ticketing.model.Passenger;
import metro.ticketing.model.User;
import metro.ticketing.repository.FileManager;
import metro.ticketing.repository.JSONFileManager;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserService {
    HashMap<String, User> users = new HashMap<String, User>();

    public UserService() {
        FileManager fileManager = new JSONFileManager();            
        JSONArray userJsonArray = new JSONArray();

        try {
            userJsonArray = fileManager.loadData("data/users.json");
        } catch (Exception e) {
            System.out.println("Error Occured when loading user data from json");
        };

        for(int i = 0; i < userJsonArray.length(); i++) {
            JSONObject tempJsonObject = userJsonArray.getJSONObject(i);
            User outputUserObject = null;

            if (tempJsonObject.getString("UserRole").equals(UserRole.ADMIN.toString())) {
                outputUserObject = new Admin(
                    tempJsonObject.getString("userId"), 
                    tempJsonObject.getString("name"),  
                    tempJsonObject.getString("email"), 
                    tempJsonObject.getString("password"), 
                    UserRole.ADMIN
                );
            } else if (tempJsonObject.getString("UserRole").equals(UserRole.PASSENGER.toString())) {
                outputUserObject = new Passenger(
                    tempJsonObject.getString("userId"), 
                    tempJsonObject.getString("name"),  
                    tempJsonObject.getString("email"), 
                    tempJsonObject.getString("password"), 
                    UserRole.ADMIN,
                    tempJsonObject.getDouble("balance")
                );
            }
            users.put(tempJsonObject.getString("name"), outputUserObject);
        }

        // for (Map.Entry<String, User> entry : users.entrySet()) {
        //     User outputUser = entry.getValue();
            
        //     System.out.printf("%s\n", entry.getKey());
        //     System.out.printf("UserId: %s\n", outputUser.getUserId());
        //     System.out.printf("Name: %s\n", outputUser.getName());
        //     System.out.printf("Email: %s\n", outputUser.getEmail());
        //     System.out.printf("Pwd: %s\n", outputUser.getPassword());
        //     System.out.printf("Role: %s\n", outputUser.getRole());

        //     if (outputUser instanceof Passenger) {
        //         System.out.printf("Balance: %s\n", ((Passenger) outputUser).getBalance());
        //     }
        // }

    }



    public void registerUser(User registedUser) {

    }

    public User login(String email, String password) {
        User user1 = new Passenger("", "", "", "", UserRole.PASSENGER, 0);

        return user1;
    }

    public void viewAllUsers() {

    }
}
