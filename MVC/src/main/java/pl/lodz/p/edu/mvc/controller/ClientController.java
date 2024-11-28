package pl.lodz.p.edu.mvc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.lodz.p.edu.mvc.dto.ClientDTO;
import pl.lodz.p.edu.mvc.service.RentService;

@Controller
public class ClientController {
    private final RentService rentService;

    public ClientController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("clientDTO", new ClientDTO());
        return "register";
    }

    @PostMapping("/register")
    public String registerClient(ClientDTO clientDTO, Model model) {
        try {
            ClientDTO registeredClient = rentService.registerClient(clientDTO);
            model.addAttribute("client", registeredClient);
            return "registration_success";
        } catch (Exception e) {
            model.addAttribute("error", "Registration failed: " + e.getMessage());
            return "register";
        }
    }
}


