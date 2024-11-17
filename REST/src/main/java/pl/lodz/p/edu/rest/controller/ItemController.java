package pl.lodz.p.edu.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.rest.dto.ComicsDTO;
import pl.lodz.p.edu.rest.dto.ItemDTO;
import pl.lodz.p.edu.rest.dto.MovieDTO;
import pl.lodz.p.edu.rest.dto.MusicDTO;
import pl.lodz.p.edu.rest.service.ItemService;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/items")
public class ItemController {
    ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping
    public ResponseEntity<ItemDTO> addItem(@RequestBody Map<String, Object> payload) {
        String itemType = (String) payload.get("itemType");
        ObjectMapper mapper = new ObjectMapper();
        ItemDTO itemDTO = switch (itemType.toLowerCase()) {
            case "music" -> mapper.convertValue(payload, MusicDTO.class);
            case "movie" -> mapper.convertValue(payload, MovieDTO.class);
            case "comics" -> mapper.convertValue(payload, ComicsDTO.class);
            default -> throw new IllegalArgumentException("Invalid item type: " + itemType);
        };

        ItemDTO addedItem = itemService.addItem(itemDTO);
        return ResponseEntity.ok(addedItem);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItem(@PathVariable ObjectId id) {
        ItemDTO itemDTO = itemService.getItemById(id);
        return ResponseEntity.ok(itemDTO);
    }

//    @GetMapping("/name/{itemName}")
//    public ResponseEntity<List<ItemDTO>> getItemsByItemName(@PathVariable String itemName) {
//        List<ItemDTO> items = itemService.getItemsByItemName(itemName);
//        return ResponseEntity.ok(items);
//    }
//
//    @GetMapping("/type/{itemType}")
//    public ResponseEntity<List<ItemDTO>> getItemsByItemType(@PathVariable String itemType) {
//        List<ItemDTO> items = itemService.getItemsByItemType(itemType);
//        return ResponseEntity.ok(items);
//    }

    @PutMapping("/{id}")
    public ResponseEntity<ItemDTO> updateItem(@PathVariable ObjectId id, @RequestBody Map<String, Object> payload) {
        String itemType = (String) payload.get("itemType");
        ObjectMapper mapper = new ObjectMapper();
        ItemDTO itemDTO = switch (itemType.toLowerCase()) {
            case "music" -> mapper.convertValue(payload, MusicDTO.class);
            case "movie" -> mapper.convertValue(payload, MovieDTO.class);
            case "comics" -> mapper.convertValue(payload, ComicsDTO.class);
            default -> throw new IllegalArgumentException("Invalid item type: " + itemType);
        };

        itemService.updateItem(id, itemDTO);

        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public void removeItem(@PathVariable ObjectId id) {
        itemService.removeItem(id);
    }
}
