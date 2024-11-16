package pl.lodz.p.edu.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.rest.dto.ClientDTO;
import pl.lodz.p.edu.rest.dto.UserDTO;
import pl.lodz.p.edu.rest.model.user.*;
import pl.lodz.p.edu.rest.repository.UserRepository;

import java.util.Objects;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO addUser(UserDTO user) {
        User createdUser = switch (user.getRole()) {
            case CLIENT -> new Client(
                    user.getLogin(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getLastName(),
                    ClientType.createNoMembership());
            case ADMIN -> new Admin(
                    user.getLogin(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getLastName());
            case MANAGER -> new Manager(
                    user.getLogin(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getLastName());
        };
        userRepository.save(createdUser);
        return new UserDTO(
                createdUser.getLogin(),
                createdUser.getPassword(),
                createdUser.getFirstName(),
                createdUser.getLastName(),
                createdUser.getRole());
    }

    public UserDTO getUserById(ObjectId id) {
        User user = userRepository.findById(id);
        if (user.getRole() == Role.CLIENT) {
            Client client = (Client) user;
            return new ClientDTO(
                    client.getLogin(),
                    client.getPassword(),
                    client.getFirstName(),
                    client.getLastName(),
                    client.getRole(),
                    client.getClientType());
        } else {
            return new UserDTO(
                    user.getLogin(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getRole());
        }
    }
}
