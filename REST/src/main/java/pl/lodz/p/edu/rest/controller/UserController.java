package pl.lodz.p.edu.rest.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.lodz.p.edu.rest.dto.*;
import pl.lodz.p.edu.rest.model.user.Role;
import pl.lodz.p.edu.rest.model.user.UserPrincipal;
import pl.lodz.p.edu.rest.security.JwtTokenProvider;
import pl.lodz.p.edu.rest.service.UserService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    public UserController(UserService userService, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
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

//    @PostMapping("/login")
//    @ResponseStatus(HttpStatus.OK)
//    public UserDTO getUserByLogin(@RequestBody LoginDTO login) {
//        return userService.getUserByLogin(login);
//    }

//    @PostMapping()
//    @ResponseStatus(HttpStatus.CREATED)
//    public SignedResponse<UserDTO> addUser(@RequestBody @Valid SignedRequest<CreateUserDTO> request) {
//        // Pobranie danych z żądania
//        CreateUserDTO userDTO = request.getData();
//        String signature = request.getSignature();
//
//        // Weryfikacja podpisu
//        Map<String, Object> expectedData = Map.of(
//                "login", userDTO.getLogin(),
//                "firstName", userDTO.getFirstName(),
//                "lastName", userDTO.getLastName(),
//                "role", userDTO.getRole().toString()
//        );
//
//        boolean isValid = jwtTokenProvider.verifySignature(signature, expectedData);
//        if (!isValid) {
//            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Niepoprawny podpis!");
//        }
//
//        // Dodanie użytkownika
//        UserDTO createdUser = userService.addUser(userDTO);
//
//        // Przygotowanie danych odpowiedzi
//        Map<String, Object> responseData = Map.of(
//                "id", createdUser.getId(),
//                "login", createdUser.getLogin(),
//                "role", createdUser.getRole().toString()
//        );
//
//        // Generowanie podpisu odpowiedzi
//        String responseSignature = jwtTokenProvider.signData(responseData);
//
//        // Zwrócenie odpowiedzi
//        return new SignedResponse<>(createdUser, responseSignature);
//    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO addUser(@RequestBody @Valid CreateUserDTO userDTO) {
        return userService.addUser(userDTO);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO getUser(@PathVariable String id) {
        return userService.getUserById(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateUser(@PathVariable String id, @RequestBody @Valid UpdateUserDTO userDTO) {
        userService.updateUser(id, userDTO);
    }

    @PutMapping("/activate/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void activateUser(@PathVariable String id) {
        userService.activateUser(id);
    }

    @PutMapping("/deactivate/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deactivateUser(@PathVariable String id) {
        userService.deactivateUser(id);
    }

    @PutMapping("/me/changePassword")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changePassword(@RequestBody @Valid ChangePasswordDTO dto) {
        UserPrincipal userPrincipal = (UserPrincipal) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();
        String username = userPrincipal.getUsername();
        userService.changePassword(username, dto);
    }

    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public TokenDTO login(@RequestBody @Valid LoginDTO dto) {
        String token = userService.login(dto);
        return new TokenDTO(token);
    }

    private String getTokenFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    @PostMapping("/logout")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void logout(HttpServletRequest request) {
        String token = getTokenFromRequest(request);
        if (token != null) {
            userService.invalidateToken(token);
        }
    }
}
