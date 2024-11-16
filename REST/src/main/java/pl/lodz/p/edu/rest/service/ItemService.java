package pl.lodz.p.edu.rest.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.rest.dto.ComicsDTO;
import pl.lodz.p.edu.rest.dto.ItemDTO;
import pl.lodz.p.edu.rest.dto.MovieDTO;
import pl.lodz.p.edu.rest.dto.MusicDTO;
import pl.lodz.p.edu.rest.mapper.ItemMapper;
import pl.lodz.p.edu.rest.model.item.*;
import pl.lodz.p.edu.rest.repository.ItemRepository;

@Service
public class ItemService {
    private final ItemRepository itemRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository) {
        this.itemRepository = itemRepository;
    }

    public MusicDTO addMusic(MusicDTO musicDTO) {
        Music music = ItemMapper.toMusic(musicDTO);
        ObjectId id = itemRepository.addItem(music);
        Music addedMusic = (Music) itemRepository.getItem(id);
        return ItemMapper.toMusicDTO(addedMusic);
    }

    public MovieDTO addMovie(MovieDTO movieDTO) {
        Movie movie = ItemMapper.toMovie(movieDTO);
        ObjectId id = itemRepository.addItem(movie);
        Movie addedMovie = (Movie) itemRepository.getItem(id);
        return ItemMapper.toMovieDTO(addedMovie);
    }

    public ComicsDTO addComics(ComicsDTO comicsDTO) {
        Comics comics = ItemMapper.toComics(comicsDTO);
        ObjectId id = itemRepository.addItem(comics);
        Comics addedComics = (Comics) itemRepository.getItem(id);
        return ItemMapper.toComicsDTO(addedComics);
    }

    public ItemDTO getItem(ObjectId id) {
        Item item = itemRepository.getItem(id);
        if (item == null) {
            throw new NullPointerException("Item not found");
        }
        return ItemMapper.toDTO(item);
    }

    public void updateItem(ObjectId id, ItemDTO itemDTO) {
        Item item = itemRepository.getItem(id);
        if (item == null) {
            throw new NullPointerException("Item not found");
        }
        item.setBasePrice(itemDTO.getBasePrice());
        item.setItemName(itemDTO.getItemName());
        itemRepository.updateItem(item);
    }

    public void removeItem(ObjectId id) {
        Item item = itemRepository.getItem(id);
        if (item == null) {
            throw new NullPointerException("Item not found");
        }
        itemRepository.removeItem(id);
    }

    public void setAvailable(ObjectId id) {
        Item item = itemRepository.getItem(id);
        if (item == null) {
            throw new NullPointerException("Item not found");
        }
        item.setAvailable(true);
        itemRepository.updateItem(item);
    }

    public void setUnavailable(ObjectId id) {
        Item item = itemRepository.getItem(id);
        if (item == null) {
            throw new NullPointerException("Item not found");
        }
        item.setAvailable(false);
        itemRepository.updateItem(item);
    }
}