package pl.lodz.p.edu.rest.controller;

import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.rest.dto.RentDTO;
import pl.lodz.p.edu.rest.service.RentService;

import java.time.LocalDateTime;

@RestController
@RequestMapping("api/rents")
public class RentController {
    RentService rentService;

    public RentController(RentService rentService) {
        this.rentService = rentService;
    }

    @PostMapping
    public void rentItem(@RequestBody RentDTO rentDTO) {
        if (rentDTO.getBeginTime() == null) {
            rentDTO.setBeginTime(LocalDateTime.now());
        }
        rentService.rentItem(rentDTO);
    }

    @PutMapping("return/{id}")
    public void returnItem(@PathVariable ObjectId id) {
        rentService.returnRent(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentDTO> getRent(@PathVariable ObjectId id) {
        RentDTO rentDTO = rentService.getRentById(id);
        return ResponseEntity.ok(rentDTO);
    }
}