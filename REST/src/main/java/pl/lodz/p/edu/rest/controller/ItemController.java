package pl.lodz.p.edu.rest.controller;

import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.rest.dto.ComicsDTO;
import pl.lodz.p.edu.rest.dto.ItemDTO;
import pl.lodz.p.edu.rest.dto.MovieDTO;
import pl.lodz.p.edu.rest.dto.MusicDTO;

@RestController
public class ItemController {
    ItemManager itemManager;

    public ItemController(ItemManager itemManager) {
        this.itemManager = itemManager;
    }

    @PostMapping("/manager/music")
    public ResponseEntity<MusicDTO> createMusic(@RequestBody MusicDTO createdMusic) {
        MusicDTO musicDTO = itemManager.addMusic(createdMusic);
        return ResponseEntity.ok(musicDTO);
    }

    @PostMapping("/manager/movie")
    public ResponseEntity<MovieDTO> createMovie(@RequestBody MovieDTO createdMovie) {
        MovieDTO movieDTO = itemManager.addMovie(createdMovie);
        return ResponseEntity.ok(movieDTO);
    }

    @PostMapping("/manager/comics")
    public ResponseEntity<ComicsDTO> createComics(@RequestBody ComicsDTO createdComics) {
        ComicsDTO comicsDTO = itemManager.addComics(createdComics);
        return ResponseEntity.ok(comicsDTO);
    }

    @GetMapping("/manager/getItem")
    public ResponseEntity<ItemDTO> getItem(@RequestParam ObjectId id) {
        ItemDTO itemDTO = itemManager.getItem(id);
        return ResponseEntity.ok(itemDTO);
    }

    @PostMapping("/manager/updateItem")
    public void updateItem(@RequestParam ObjectId id, @RequestBody ItemDTO updatedItem) {
        itemManager.updateItem(id, updatedItem);
    }

    @PostMapping("/admin/deleteItem")
    public void deleteItem(@RequestParam ObjectId id) {
        itemManager.removeItem(id);
    }
}
