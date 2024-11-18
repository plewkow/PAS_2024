package pl.lodz.p.edu.rest.service;

import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.rest.dto.UpdateUserDTO;
import pl.lodz.p.edu.rest.dto.UserDTO;
import pl.lodz.p.edu.rest.exception.DuplicateUserException;
import pl.lodz.p.edu.rest.exception.UserNotFoundException;
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
        if (userExists(user.getLogin())) {
            throw new DuplicateUserException("User with login " + user.getLogin() + " already exists");
        }
        User createdUser = userMapper.convertToUser(user);
        userRepository.save(createdUser);
        return userMapper.convertToUserDTO(createdUser);
    }

    public UserDTO getUserById(ObjectId id) {
        User user = userRepository.findById(id);
        if (user == null) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        return userMapper.convertToUserDTO(user);
    }

    public List<UserDTO> getUsersByRole(Role role) {
        List<User> users = userRepository.findByRole(role);
        if (users.isEmpty()) {
            throw new UserNotFoundException("Users with role " + role + " not found");
        }
        return userMapper.toDTO(users);
    }

    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new UserNotFoundException("No users found");
        }
        return userMapper.toDTO(users);
    }

    public void updateUser(ObjectId id, UpdateUserDTO userDTO) {
        if (!findUserById(id)) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        UpdateResult result = userRepository.update(id, userDTO.getFirstName(), userDTO.getLastName());
        if (result.getModifiedCount() == 0) {
            throw new RuntimeException("User with id " + id + " not updated");
        }
    }

    public void activateUser(ObjectId id) {
        if (!findUserById(id)) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        UpdateResult result = userRepository.activateUser(id);
        if (result.getModifiedCount() == 0) {
            throw new RuntimeException("User with id " + id + " not updated");
        }
    }

    public void deactivateUser(ObjectId id) {
        if (!findUserById(id)) {
            throw new UserNotFoundException("User with id " + id + " not found");
        }
        UpdateResult result = userRepository.deactivateUser(id);
        if (result.getModifiedCount() == 0) {
            throw new RuntimeException("User with id " + id + " not updated");
        }
    }

    private boolean userExists(String login) {
        return userRepository.userExists(login);
    }

    private boolean findUserById(ObjectId id) {
        return userRepository.findById(id) != null;
    }
}
