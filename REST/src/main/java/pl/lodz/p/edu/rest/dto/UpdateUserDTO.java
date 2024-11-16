package pl.lodz.p.edu.rest.dto;

import pl.lodz.p.edu.rest.model.user.Role;

public class UpdateUserDTO {
    private String firstName;
    private String lastName;

    public UpdateUserDTO() {
    }

    public UpdateUserDTO(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
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
