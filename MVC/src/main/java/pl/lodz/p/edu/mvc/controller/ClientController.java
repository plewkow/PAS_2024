package pl.lodz.p.edu.mvc.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.lodz.p.edu.mvc.dto.ClientDTO;
import pl.lodz.p.edu.mvc.service.ClientService;
import pl.lodz.p.edu.mvc.service.RentService;

@Controller
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("clientDTO", new ClientDTO());
        model.addAttribute("currentPage", "register");
        return "register";
    }

    @PostMapping("/register")
    public String registerClient(@Valid ClientDTO clientDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            ClientDTO registeredClient = clientService.registerClient(clientDTO);
            model.addAttribute("client", registeredClient);
            return "registration_success";
        } catch (Exception e) {
            model.addAttribute("error", "Błąd rejestracji: " + e.getMessage());
            return "register";
        }
    }
}


