package pl.lodz.p.edu.mvc.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import org.bson.types.ObjectId;
import pl.lodz.p.edu.mvc.model.user.Role;

public class UserDTO {
    private String id;
    @NotBlank(message = "login jest wymagany")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "login może zawierać tylko litery, cyfry i podkreślenia")
    private String login;
    @NotBlank(message = "haslo jest wymagane")
    @Size(min = 8, message = "hasło musi mieć co najmniej 8 znaków")
    private String password;
    @NotBlank(message = "imie jest wymagane")
    private String firstName;
    @NotBlank(message = "nazwisko jest wymagane")
    private String lastName;
    private Role role;

    @JsonCreator
    public UserDTO(String id, String login, String password, String firstName, String lastName, Role role) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public UserDTO(String login, String password, String firstName, String lastName, Role role) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public UserDTO() {}

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
