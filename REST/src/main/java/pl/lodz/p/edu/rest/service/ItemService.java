package pl.lodz.p.edu.rest.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import pl.lodz.p.edu.rest.dto.ComicsDTO;
import pl.lodz.p.edu.rest.dto.ItemDTO;
import pl.lodz.p.edu.rest.dto.MovieDTO;
import pl.lodz.p.edu.rest.dto.MusicDTO;
import pl.lodz.p.edu.rest.mapper.ItemMapper;
import pl.lodz.p.edu.rest.model.item.*;
import pl.lodz.p.edu.rest.repository.ItemRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequestScope
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
        Music addedMusic = (Music) itemRepository.getItemById(id);
        return ItemMapper.toMusicDTO(addedMusic);
    }

    public MovieDTO addMovie(MovieDTO movieDTO) {
        Movie movie = ItemMapper.toMovie(movieDTO);
        ObjectId id = itemRepository.addItem(movie);
        Movie addedMovie = (Movie) itemRepository.getItemById(id);
        return ItemMapper.toMovieDTO(addedMovie);
    }

    public ComicsDTO addComics(ComicsDTO comicsDTO) {
        Comics comics = ItemMapper.toComics(comicsDTO);
        ObjectId id = itemRepository.addItem(comics);
        Comics addedComics = (Comics) itemRepository.getItemById(id);
        return ItemMapper.toComicsDTO(addedComics);
    }

    public ItemDTO getItemById(ObjectId id) {
        Item item = itemRepository.getItemById(id);
        if (item == null) {
            throw new NullPointerException("Item not found");
        }
        return ItemMapper.toDTO(item);
    }

    public List<ItemDTO> getItemsByBasePrice(int basePrice) {
        List<Item> items = itemRepository.getItemsByBasePrice(basePrice);
        return items.stream()
                .map(item -> new ItemDTO(
                        item.getBasePrice(),
                        item.getItemName(),
                        item.isAvailable()
                )).collect(Collectors.toList());
    }

    public List<ItemDTO> getItemsByItemName(String itemName) {
        List<Item> items = itemRepository.getItemsByItemName(itemName);
        return items.stream()
                .map(item -> new ItemDTO(
                        item.getBasePrice(),
                        item.getItemName(),
                        item.isAvailable()
                )).collect(Collectors.toList());
    }

    public List<ItemDTO> getItemsByItemType(String itemType) {
        List<Item> items = itemRepository.getItemsByItemType(itemType);
        return items.stream()
                .map(item -> new ItemDTO(
                        item.getBasePrice(),
                        item.getItemName(),
                        item.isAvailable()
                )).collect(Collectors.toList());
    }

    public void updateMusic(ObjectId id, MusicDTO musicDTO) {
        Music music = (Music) itemRepository.getItemById(id);
        if (music == null) {
            throw new NullPointerException("Music item not found");
        }
        music.setBasePrice(musicDTO.getBasePrice());
        music.setItemName(musicDTO.getItemName());
        music.setGenre(musicDTO.getGenre());
        music.setVinyl(musicDTO.isVinyl());
        itemRepository.updateItem(music);
    }

    public void updateMovie(ObjectId id, MovieDTO movieDTO) {
        Movie movie = (Movie) itemRepository.getItemById(id);
        if (movie == null) {
            throw new NullPointerException("Movie item not found");
        }
        movie.setBasePrice(movieDTO.getBasePrice());
        movie.setItemName(movieDTO.getItemName());
        movie.setMinutes(movieDTO.getMinutes());
        movie.setCasette(movieDTO.isCasette());
        itemRepository.updateItem(movie);
    }

    public void updateComics(ObjectId id, ComicsDTO comicsDTO) {
        Comics comics = (Comics) itemRepository.getItemById(id);
        if (comics == null) {
            throw new NullPointerException("Comics item not found");
        }
        comics.setBasePrice(comicsDTO.getBasePrice());
        comics.setItemName(comicsDTO.getItemName());
        comics.setPageNumber(comicsDTO.getPagesNumber());
        itemRepository.updateItem(comics);
    }

    public void removeItem(ObjectId id) {
        Item item = itemRepository.getItemById(id);
        if (item == null) {
            throw new NullPointerException("Item not found");
        }
        itemRepository.removeItem(id);
    }

    public void setAvailable(ObjectId id) {
        Item item = itemRepository.getItemById(id);
        if (item == null) {
            throw new NullPointerException("Item not found");
        }
        item.setAvailable(true);
        itemRepository.updateItem(item);
    }

    public void setUnavailable(ObjectId id) {
        Item item = itemRepository.getItemById(id);
        if (item == null) {
            throw new NullPointerException("Item not found");
        }
        item.setAvailable(false);
        itemRepository.updateItem(item);
    }
}