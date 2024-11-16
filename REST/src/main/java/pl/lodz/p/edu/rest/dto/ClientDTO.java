package pl.lodz.p.edu.rest.dto;

import pl.lodz.p.edu.rest.model.user.Role;

public class ClientDTO extends UserDTO {
    private String clientType;

    public ClientDTO(String login, String password, String firstName, String lastName, Role role, String clientType) {
        super(login, password, firstName, lastName, role);
        this.clientType = clientType;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }
}
