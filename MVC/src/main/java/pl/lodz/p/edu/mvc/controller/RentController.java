package pl.lodz.p.edu.mvc.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.mvc.dto.RentDTO;
import pl.lodz.p.edu.mvc.service.RentService;

import java.util.Collections;
import java.util.List;

@Controller
public class RentController {
    private final RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @GetMapping("/rents")
    public String showAllocations(Model model) {
        List<RentDTO> rents = rentService.getRents();

        if (rents.isEmpty()) {
            model.addAttribute("message", "Brak rentów");
        }

        model.addAttribute("rents", rents);
        model.addAttribute("currentPage", "Rents");
        return "allocations";
    }

    @GetMapping("/rents/active")
    @ResponseBody
    public ResponseEntity<List<RentDTO>> getAllRents() {
        List<RentDTO> rents = rentService.getRents();
        if (rents.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Collections.emptyList());
        }
        return ResponseEntity.ok(rents);
    }

    @GetMapping("/rents/active/clientId/{clientId}")
    public ResponseEntity<List<RentDTO>> showAllocationsByClient(@PathVariable String clientId) {
        List<RentDTO> rents;

        try {
            rents = rentService.getRentsByClientId(clientId);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        }
        return ResponseEntity.ok(rents);
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("currentPage", null);
        return "home";
    }


    @GetMapping("/createRent")
    public String showRegistrationForm(Model model) {
        model.addAttribute("rentDTO", new RentDTO());
        model.addAttribute("currentPage", "createRent");
        return "create_allocation";
    }

    @PostMapping("/createRent")
    public String registerClient(@Valid @ModelAttribute RentDTO rentDTO, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "create_allocation";
        }

        try {
            RentDTO rent = rentService.createRent(rentDTO);
            model.addAttribute("rent", rent);
            return "create_success";
        } catch (RuntimeException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "errorPage";
        }
    }

    @PostMapping("/return/{id}")
    public String returnRent(@PathVariable String id) {
        rentService.returnRent(id);
        return "redirect:/rents";
    }
}
