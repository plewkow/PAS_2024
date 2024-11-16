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
        System.out.println(createdUser.getRole());
        return new UserDTO(
                createdUser.getLogin(),
                createdUser.getPassword(),
                createdUser.getFirstName(),
                createdUser.getLastName(),
                createdUser.getRole());
    }
}
