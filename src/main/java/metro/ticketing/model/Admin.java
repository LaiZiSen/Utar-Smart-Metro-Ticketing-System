package metro.ticketing.model;

import metro.ticketing.enums.UserRole;

public class Admin extends User{
    public Admin(String userId, String name, 
        String email, String password, UserRole role) {
        super(userId, name, email, password, role);
    }

    public Admin(User adminData) {
        super(
            adminData.getUserId(), 
            adminData.getName(), 
            adminData.getEmail(), 
            adminData.getPassword(), 
            adminData.getRole()
        );
    }

    public Admin() {
        super();
    }
}
