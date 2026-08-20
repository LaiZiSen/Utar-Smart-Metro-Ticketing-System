package metro.ticketing.model;

import org.json.JSONObject;

import metro.ticketing.enums.UserRole;

import metro.ticketing.include.func; 

public abstract class User {
    private String userId;
    private String name;
    private String email;
    private String password;
    private UserRole role;

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public User(String userId, String name, String email, String password, UserRole role) {
        this.userId = userId;
        this.name=  name;
        this.email = email;
        this.password = password;
        this.role = role;
    }

    public User() {
        this.userId = "";
        this.name = "";
        this.email = "";
        this.password = "";
        this.role = null;
    }

    public static User jsonToUser(JSONObject json) {
        User outputUserObject = null;

        if (json.getString("UserRole").equals(UserRole.ADMIN.toString())) {
            outputUserObject = new Admin(
                json.getString("userId"), 
                json.getString("name"),  
                json.getString("email"), 
                json.getString("password"), 
                UserRole.ADMIN
            );
        } else if (json.getString("UserRole").equals(UserRole.PASSENGER.toString())) {
            outputUserObject = new Passenger(
                json.getString("userId"), 
                json.getString("name"),  
                json.getString("email"), 
                json.getString("password"), 
                UserRole.PASSENGER,
                json.getDouble("balance")
            );
        }

        return outputUserObject;
    }

    public static JSONObject userToJsonObject(User userData) {
        JSONObject value = new JSONObject();

        value.put("userId", userData.getUserId());
        value.put("name", userData.getName());
        value.put("email", userData.getEmail());
        value.put("password", userData.getPassword());
        value.put("UserRole", userData.getRole());

        if (userData instanceof Passenger) {
            value.put("balance", ((Passenger) userData).getBalance());
        }

        return value;
    }
    
    public void viewProfile() {
        func.clear();
        System.out.println("");
        func.printHeader("", '=');
        func.printHeader("PROFILE", ' ');
        func.printHeader("", '=');
   
        System.out.printf("%-15s:%s\n", "Name:", this.name);
        System.out.printf("%-15s:%s\n", "Email", this.email);
        System.out.printf("%-15s:%s\n", "Password", this.password);
        System.out.printf("%-15s:%s\n", "userId", this.userId);
            
        if (this instanceof Passenger) {
            System.out.printf("%-15s:%f\n","Balance",((Passenger) this).getBalance());
        }
        func.printHeader("", '=');
        System.out.println("");
        
        func.pause();
    }
}
