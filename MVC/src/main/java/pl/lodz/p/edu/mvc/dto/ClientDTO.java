package pl.lodz.p.edu.mvc.dto;

import pl.lodz.p.edu.mvc.model.user.ClientType;
import pl.lodz.p.edu.mvc.model.user.Role;

public class ClientDTO extends UserDTO {
    private ClientType clientType;

    public ClientDTO(String id, String login, String password, String firstName, String lastName, Role role, ClientType clientType) {
        super(id, login, password, firstName, lastName, role);
        this.clientType = clientType;
    }

    public ClientDTO() {}

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }
}
