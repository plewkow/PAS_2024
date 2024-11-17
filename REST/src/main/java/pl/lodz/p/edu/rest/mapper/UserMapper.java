package pl.lodz.p.edu.rest.mapper;

import pl.lodz.p.edu.rest.dto.ClientDTO;
import pl.lodz.p.edu.rest.dto.UserDTO;
import pl.lodz.p.edu.rest.model.user.*;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public List<UserDTO> toDTO(List<User> users) {
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO convertToDTO(User user) {
        if (user instanceof Client client) {
            return new ClientDTO(
                    client.getLogin(),
                    client.getPassword(),
                    client.getFirstName(),
                    client.getLastName(),
                    client.getRole(),
                    client.getClientType()
            );
        } else {
            return new UserDTO(
                    user.getLogin(),
                    user.getPassword(),
                    user.getFirstName(),
                    user.getLastName(),
                    user.getRole()
            );
        }
    }

    public UserDTO convertToUserDTO(User user) {
        return new UserDTO(
                user.getLogin(),
                user.getPassword(),
                user.getFirstName(),
                user.getLastName(),
                user.getRole()
        );
    }

    public User convertToUser(UserDTO userDTO) {
        return switch (userDTO.getRole()) {
            case CLIENT -> new Client(
                    userDTO.getLogin(),
                    userDTO.getPassword(),
                    userDTO.getFirstName(),
                    userDTO.getLastName(),
                    ClientType.createNoMembership());
            case ADMIN -> new Admin(
                    userDTO.getLogin(),
                    userDTO.getPassword(),
                    userDTO.getFirstName(),
                    userDTO.getLastName());
            case MANAGER -> new Manager(
                    userDTO.getLogin(),
                    userDTO.getPassword(),
                    userDTO.getFirstName(),
                    userDTO.getLastName());
        };
    }
}