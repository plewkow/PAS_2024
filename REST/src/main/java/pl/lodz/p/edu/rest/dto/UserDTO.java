package pl.lodz.p.edu.rest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.bson.types.ObjectId;
import pl.lodz.p.edu.rest.model.user.Role;

public class UserDTO {
    private String id;
    private String login;
    private String password;
    private String firstName;
    private String lastName;
    private Role role;

    public UserDTO(String id, String login, String password, String firstName, String lastName, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
