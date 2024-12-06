package pl.lodz.p.edu.mvc.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
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
    public String registerClient(@Valid ClientDTO clientDTO, BindingResult bindingResult, HttpSession session, Model model) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        try {
            ClientDTO registeredClient = clientService.registerClient(clientDTO);
            session.setAttribute("client", registeredClient);
            return "redirect:/register-success";
        } catch (RuntimeException e) {
            if (e.getMessage().contains("Login już istnieje")) {
                model.addAttribute("loginUniqueError", e.getMessage());
                return "register";
            }
            session.setAttribute("error", "Błąd rejestracji: " + e.getMessage());
            return "redirect:/register";
        }
    }

    @GetMapping("/register-success")
    public String showRegistrationSuccess(HttpSession session, Model model) {
        ClientDTO client = (ClientDTO) session.getAttribute("client");
        if (client != null) {
            model.addAttribute("client", client);
        }
        return "registration_success";
    }
}