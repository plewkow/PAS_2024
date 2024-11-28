package pl.lodz.p.edu.mvc.model.user;

import org.bson.types.ObjectId;

public class Manager extends User {
    public Manager(ObjectId id, String login, String password, String firstName, String lastName) {
        super(id, login, password, firstName, lastName);
        this.setRole(Role.MANAGER);
    }

    public Manager() {
    }
}
