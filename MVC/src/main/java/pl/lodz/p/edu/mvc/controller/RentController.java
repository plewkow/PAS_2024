package pl.lodz.p.edu.mvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import pl.lodz.p.edu.mvc.dto.ClientDTO;
import pl.lodz.p.edu.mvc.dto.RentDTO;
import pl.lodz.p.edu.mvc.service.RentService;

@Controller
public class RentController {
    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping("/createRent")
    public String showRegistrationForm(Model model) {
        model.addAttribute("rentDTO", new RentDTO());
        return "create_allocation";
    }

    @PostMapping("/createRent")
    public String registerClient(RentDTO rentDTO, Model model) {
        try {
            RentDTO rent = rentService.createRent(rentDTO);
            model.addAttribute("rent", rent);
            return "create_success";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errorPage";
        }
    }
}
