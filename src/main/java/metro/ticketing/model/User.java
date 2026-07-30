package metro.ticketing.model;

import metro.ticketing.enums.UserRole;

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

}
