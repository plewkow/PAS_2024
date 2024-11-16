package pl.lodz.p.edu.rest.mapper;

import pl.lodz.p.edu.rest.dto.ClientDTO;
import pl.lodz.p.edu.rest.dto.UserDTO;
import pl.lodz.p.edu.rest.model.user.Client;
import pl.lodz.p.edu.rest.model.user.User;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public List<UserDTO> toDTO(List<User> users) {
        return users.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private UserDTO convertToDTO(User user) {
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
}
