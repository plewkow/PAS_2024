package pl.lodz.p.edu.rest.model.user;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@BsonDiscriminator("Manager")
public class Manager extends User {
    public Manager(String login, String password, String firstName, String lastName) {
        super(login, password, firstName, lastName);
        this.setRole(Role.MANAGER);
    }

    public Manager() {
    }
}
