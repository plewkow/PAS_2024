package pl.lodz.p.edu.rest.service;

import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.rest.dto.ClientDTO;
import pl.lodz.p.edu.rest.dto.UpdateUserDTO;
import pl.lodz.p.edu.rest.dto.UserDTO;
import pl.lodz.p.edu.rest.mapper.UserMapper;
import pl.lodz.p.edu.rest.model.user.*;
import pl.lodz.p.edu.rest.repository.UserRepository;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper = new UserMapper();

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDTO addUser(UserDTO user) {
        User createdUser = userMapper.convertToUser(user);
        userRepository.save(createdUser);
        return userMapper.convertToUserDTO(createdUser);
    }

    public UserDTO getUserById(ObjectId id) {
        User user = userRepository.findById(id);
        return userMapper.convertToUserDTO(user);
    }

    public List<UserDTO> getUsersByRole(Role role) {
        List<User> users = userRepository.findByRole(role);
        return userMapper.toDTO(users);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return userMapper.toDTO(users);
    }

    public UpdateUserDTO updateUser(ObjectId id, UpdateUserDTO userDTO) {
        UpdateResult result = userRepository.update(id, userDTO.getFirstName(), userDTO.getLastName());
        if (result.getModifiedCount() == 0) {
            return null;
        }
        return userDTO;
    }

    public UserDTO activateUser(ObjectId id) {
        UpdateResult result = userRepository.activateUser(id);
        if (result.getModifiedCount() == 0) {
            return null;
        }
        return getUserById(id);
    }

    public UserDTO deactivateUser(ObjectId id) {
        UpdateResult result = userRepository.deactivateUser(id);
        if (result.getModifiedCount() == 0) {
            return null;
        }
        return getUserById(id);
    }
}
