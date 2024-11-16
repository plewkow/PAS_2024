package pl.lodz.p.edu.rest.model.user;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;

@BsonDiscriminator("Admin")
public class Admin extends User {
    public Admin(String login, String password, String firstName, String lastName) {
        super(login, password, firstName, lastName);
        this.setRole(Role.ADMIN);
    }

    public Admin() {
    }
}
