package pl.lodz.p.edu.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.rest.dto.ClientDTO;
import pl.lodz.p.edu.rest.dto.UserDTO;
import pl.lodz.p.edu.rest.model.user.ClientType;
import pl.lodz.p.edu.rest.model.user.Role;
import pl.lodz.p.edu.rest.model.user.User;
import pl.lodz.p.edu.rest.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

//    @GetMapping()
//    public List<User> getAllUsers() {
//        return userService.getAllUsers();
//    }

    @GetMapping()
    public String getAllUsers() {
        return "null";
    }

    @PostMapping()
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {
        System.out.println(userDTO.getRole());
        UserDTO user = userService.addUser(userDTO);
        return ResponseEntity.ok(user);
    }
}
