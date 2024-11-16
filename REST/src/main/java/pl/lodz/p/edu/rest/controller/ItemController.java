package pl.lodz.p.edu.rest.controller;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import pl.lodz.p.edu.rest.dto.ComicsDTO;
import pl.lodz.p.edu.rest.dto.ItemDTO;
import pl.lodz.p.edu.rest.dto.MovieDTO;
import pl.lodz.p.edu.rest.dto.MusicDTO;
import pl.lodz.p.edu.rest.service.ItemService;

@RestController
@RequestScope
@RequestMapping("/items")
public class ItemController {
    ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/manager/music")
    public ResponseEntity<MusicDTO> createMusic(@RequestBody MusicDTO createdMusic) {
        MusicDTO musicDTO = itemService.addMusic(createdMusic);
        return ResponseEntity.ok(musicDTO);
    }

    @PostMapping("/manager/movie")
    public ResponseEntity<MovieDTO> createMovie(@RequestBody MovieDTO createdMovie) {
        MovieDTO movieDTO = itemService.addMovie(createdMovie);
        return ResponseEntity.ok(movieDTO);
    }

    @PostMapping("/manager/comics")
    public ResponseEntity<ComicsDTO> createComics(@RequestBody ComicsDTO createdComics) {
        ComicsDTO comicsDTO = itemService.addComics(createdComics);
        return ResponseEntity.ok(comicsDTO);
    }

    @GetMapping("/manager/getItem")
    public ResponseEntity<ItemDTO> getItem(@RequestParam ObjectId id) {
        ItemDTO itemDTO = itemService.getItemById(id);
        return ResponseEntity.ok(itemDTO);
    }

    @PostMapping("/manager/updateItem")
    public void updateItem(@RequestParam ObjectId id, @RequestBody ItemDTO updatedItem) {
        itemService.updateItem(id, updatedItem);
    }

    @PostMapping("/admin/deleteItem")
    public void deleteItem(@RequestParam ObjectId id) {
        itemService.removeItem(id);
    }
}
