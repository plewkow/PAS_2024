package pl.lodz.p.edu.rest.controller;

import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.rest.dto.RentDTO;
import pl.lodz.p.edu.rest.service.RentService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/active")
    public ResponseEntity<List<RentDTO>> getActiveRents() {
        List<RentDTO> activeRents = rentService.getActiveRents().stream()
                .map(rent -> new RentDTO(
                        rent.getBeginTime(),
                        rent.getEndTime(),
                        rent.getRentCost(),
                        rent.isArchive(),
                        rent.getClient().getId(),
                        rent.getItem().getId()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(activeRents);
    }

    @GetMapping("/item/{itemId}")
    public ResponseEntity<List<RentDTO>> getRentsByItem(@PathVariable ObjectId itemId) {
        List<RentDTO> rents = rentService.getRentsByItem(itemId).stream()
                .map(rent -> new RentDTO(
                        rent.getBeginTime(),
                        rent.getEndTime(),
                        rent.getRentCost(),
                        rent.isArchive(),
                        rent.getClient().getId(),
                        rent.getItem().getId()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(rents);
    }

    @GetMapping("/active/item/{itemId}")
    public ResponseEntity<List<RentDTO>> getActiveRentsByItem(@PathVariable ObjectId itemId) {
        List<RentDTO> activeRents = rentService.getActiveRentsByItem(itemId).stream()
                .map(rent -> new RentDTO(
                        rent.getBeginTime(),
                        rent.getEndTime(),
                        rent.getRentCost(),
                        rent.isArchive(),
                        rent.getClient().getId(),
                        rent.getItem().getId()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(activeRents);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<RentDTO>> getRentsByClient(@PathVariable ObjectId clientId) {
        List<RentDTO> rents = rentService.getRentsByClient(clientId).stream()
                .map(rent -> new RentDTO(
                        rent.getBeginTime(),
                        rent.getEndTime(),
                        rent.getRentCost(),
                        rent.isArchive(),
                        rent.getClient().getId(),
                        rent.getItem().getId()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(rents);
    }

    @GetMapping("/active/client/{clientId}")
    public ResponseEntity<List<RentDTO>> getActiveRentsByClient(@PathVariable ObjectId clientId) {
        List<RentDTO> activeRents = rentService.getActiveRentsByClient(clientId).stream()
                .map(rent -> new RentDTO(
                        rent.getBeginTime(),
                        rent.getEndTime(),
                        rent.getRentCost(),
                        rent.isArchive(),
                        rent.getClient().getId(),
                        rent.getItem().getId()
                ))
                .collect(Collectors.toList());
        return ResponseEntity.ok(activeRents);
    }

    @GetMapping("/isRented/{itemId}")
    public ResponseEntity<Boolean> isItemRented(@PathVariable ObjectId itemId) {
        boolean isRented = rentService.isItemRented(itemId);
        return ResponseEntity.ok(isRented);
    }

    @GetMapping("/hasActiveRents/{clientId}")
    public ResponseEntity<Boolean> hasActiveRentsByClient(@PathVariable ObjectId clientId) {
        boolean hasActiveRents = rentService.hasActiveRentsByClient(clientId);
        return ResponseEntity.ok(hasActiveRents);
    }
}