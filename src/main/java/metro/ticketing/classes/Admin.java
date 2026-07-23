package metro.ticketing.classes;

import metro.ticketing.enums.UserRole;

public class Admin extends User{
    public Admin(String userId, String name, 
        String email, String password, UserRole role) {
        super(userId, name, email, password, role);
    }
}
