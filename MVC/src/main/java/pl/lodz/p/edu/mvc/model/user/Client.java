package pl.lodz.p.edu.mvc.model.user;

import org.bson.types.ObjectId;

public class Client extends User {
    private ClientType clientType;

    public Client(ObjectId id,
                  String login,
                  String password,
                  String firstName,
                  String lastName,
                  ClientType clientType) {
        super(id, login, password, firstName, lastName);
        this.setRole(Role.CLIENT);
        this.clientType = clientType;
    }

    public Client() {
    }

    public ClientType getClientType() {
        return clientType;
    }

    public void setClientType(ClientType clientType) {
        this.clientType = clientType;
    }
}
