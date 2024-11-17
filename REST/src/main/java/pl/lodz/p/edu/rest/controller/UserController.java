package pl.lodz.p.edu.rest.controller;

import org.bson.types.ObjectId;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.rest.dto.UpdateUserDTO;
import pl.lodz.p.edu.rest.dto.UserDTO;
import pl.lodz.p.edu.rest.model.user.Role;
import pl.lodz.p.edu.rest.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> getAllUsers(@RequestParam(required = false) Role role) {
        List<UserDTO> users;
        if (role == null) {
            return userService.getAllUsers();
        } else {
            return userService.getUsersByRole(role);
        }
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO addUser(@RequestBody UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUser(@PathVariable ObjectId id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UpdateUserDTO updateUser(@PathVariable ObjectId id, @RequestBody UpdateUserDTO userDTO) {
        return userService.updateUser(id, userDTO);
    }

    @PostMapping("/activate/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO activateUser(@PathVariable ObjectId id) {
        return userService.activateUser(id);
    }

    @PostMapping("/deactivate/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO deactivateUser(@PathVariable ObjectId id) {
        return userService.deactivateUser(id);
    }
}
