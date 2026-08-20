package metro.ticketing.services;

import metro.ticketing.enums.UserRole;

import metro.ticketing.model.Passenger;
import metro.ticketing.model.User;

import metro.ticketing.repository.FileManager;
import metro.ticketing.repository.JSONFileManager;

import metro.ticketing.include.func;
import metro.ticketing.exception.InvalidLoginException;

import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONObject;

public class UserService {
    private HashMap<String, User> users = new HashMap<String, User>();
    private FileManager fileManager = new JSONFileManager("data/users.json");

    public UserService() {
        JSONArray userJsonArray = new JSONArray();

        try {
            userJsonArray = fileManager.loadData();
        } catch (Exception e) {
            System.out.println("Error occured when loading user data from json");
        };

        for(int i = 0; i < userJsonArray.length(); i++) {
            JSONObject tempJsonObject = userJsonArray.getJSONObject(i);
            
            this.users.put(tempJsonObject.getString("email"), User.jsonToUser(tempJsonObject));
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
            fileManager.saveData(inputJsonArray);
        } catch (Exception e) {
            System.out.println("Failed to save user data");
        }
    }

    private String idIncrement() {
        int idIncrement = 0;

        for (HashMap.Entry<String, User> entry : this.users.entrySet()) {
            User tempUser = entry.getValue();
            String tempId = tempUser.getUserId();
            int tempIdNum = Integer.parseInt(tempId.substring(1));

            if(tempId.charAt(0) == 'u' && tempIdNum>idIncrement) {
                    idIncrement = Integer.parseInt(tempId.substring(1));
            }
        }

        return func.formatId("u", (idIncrement+1), 4);
    }

    // TODO - NOT DONE
    public void registerUser(String name, String email, String password) {
        Passenger newPassenger = new Passenger(idIncrement(), name, email, password, UserRole.PASSENGER);

        this.users.put(newPassenger.getEmail(), newPassenger);
        this.saveData();
    }

    // TODO - NOT DONE
    public User login(String email, String password) throws InvalidLoginException{
        // User user1 = new Passenger("", "", "", "", UserRole.PASSENGER, 0);
        
        // check if email exist
        // if not throw exception
        //
        // check if password matches
        // if not throw exception
        // if yes return user

        User output = users.get(email);
       
        if (output == null) {
            throw new InvalidLoginException();
        }

        if (!output.getPassword().equals(password)) {
            throw new InvalidLoginException();
        }

        return output;
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

    public User getUserById(String userId) {
        for (HashMap.Entry<String, User> entry : this.users.entrySet()) {
            User user= entry.getValue();
            
            if (user.getUserId().equals(userId)) {
                return user;
            }
        }
        return null;
    }

    public boolean emailExists(String email) {
        for (HashMap.Entry<String, User> entry : this.users.entrySet()) {
            User tempUser = entry.getValue();
            if (email.equals(tempUser.getEmail())) {
                return true;
            }
        }
        return false;
    }

    public void editUser(String name, User user) {
        this.users.put(name, user);

        saveData();
    }

}
