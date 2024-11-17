package pl.lodz.p.edu.rest.controller;

import org.bson.types.ObjectId;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.lodz.p.edu.rest.dto.ComicsDTO;
import pl.lodz.p.edu.rest.dto.ItemDTO;
import pl.lodz.p.edu.rest.dto.MovieDTO;
import pl.lodz.p.edu.rest.dto.MusicDTO;
import pl.lodz.p.edu.rest.service.ItemService;

@RestController
@RequestMapping("api/items")
public class ItemController {
    ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @PostMapping("/music")
    public ResponseEntity<MusicDTO> addMusic(@RequestBody MusicDTO createdMusic) {
        MusicDTO musicDTO = itemService.addMusic(createdMusic);
        return ResponseEntity.ok(musicDTO);
    }

    @PostMapping("/movie")
    public ResponseEntity<MovieDTO> addMovie(@RequestBody MovieDTO createdMovie) {
        MovieDTO movieDTO = itemService.addMovie(createdMovie);
        return ResponseEntity.ok(movieDTO);
    }

    @PostMapping("/comics")
    public ResponseEntity<ComicsDTO> addComics(@RequestBody ComicsDTO createdComics) {
        ComicsDTO comicsDTO = itemService.addComics(createdComics);
        return ResponseEntity.ok(comicsDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItem(@PathVariable ObjectId id) {
        ItemDTO itemDTO = itemService.getItemById(id);
        return ResponseEntity.ok(itemDTO);
    }

    @PutMapping("/music/{id}")
    public ResponseEntity<Void> updateMusic(@PathVariable ObjectId id, @RequestBody MusicDTO musicDTO) {
        itemService.updateMusic(id, musicDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/movie/{id}")
    public ResponseEntity<Void> updateMovie(@PathVariable ObjectId id, @RequestBody MovieDTO movieDTO) {
        itemService.updateMovie(id, movieDTO);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/comics/{id}")
    public ResponseEntity<Void> updateComics(@PathVariable ObjectId id, @RequestBody ComicsDTO comicsDTO) {
        itemService.updateComics(id, comicsDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public void removeItem(@PathVariable ObjectId id) {
        itemService.removeItem(id);
    }
}
