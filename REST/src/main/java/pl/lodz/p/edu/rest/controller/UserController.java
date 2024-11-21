package pl.lodz.p.edu.rest.controller;

import jakarta.validation.Valid;
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
    public List<UserDTO> getAllUsers(
            @RequestParam(required = false) Role role,
            @RequestParam(required = false) String firstName) {
        if (role != null && firstName != null) {
            return userService.getUsersByRoleAndFirstName(role, firstName);
        } else if (firstName != null) {
            return userService.getUsersByFirstName(firstName);
        } else if (role == null) {
            return userService.getAllUsers();
        } else {
            return userService.getUsersByRole(role);
        }
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO addUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUser(@PathVariable ObjectId id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable ObjectId id, @RequestBody @Valid UpdateUserDTO userDTO) {
        userService.updateUser(id, userDTO);
    }

    @PutMapping("/activate/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activateUser(@PathVariable ObjectId id) {
        userService.activateUser(id);
    }

    @PutMapping("/deactivate/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateUser(@PathVariable ObjectId id) {
        userService.deactivateUser(id);
    }
}
