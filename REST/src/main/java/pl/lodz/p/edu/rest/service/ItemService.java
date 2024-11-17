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

    public ItemDTO addItem(ItemDTO itemDTO) {
        ObjectId id;
        Item addedItem;

        switch (itemDTO.getItemType().toLowerCase()) {
            case "music":
                Music music = ItemMapper.toMusic((MusicDTO) itemDTO);
                id = itemRepository.addItem(music);
                addedItem = itemRepository.getItemById(id);
                return ItemMapper.toMusicDTO((Music) addedItem);

            case "movie":
                Movie movie = ItemMapper.toMovie((MovieDTO) itemDTO);
                id = itemRepository.addItem(movie);
                addedItem = itemRepository.getItemById(id);
                return ItemMapper.toMovieDTO((Movie) addedItem);

            case "comics":
                Comics comics = ItemMapper.toComics((ComicsDTO) itemDTO);
                id = itemRepository.addItem(comics);
                addedItem = itemRepository.getItemById(id);
                return ItemMapper.toComicsDTO((Comics) addedItem);

            default:
                throw new IllegalArgumentException("Invalid item type: " + itemDTO.getItemType());
        }
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

    public void updateItem(ObjectId id, ItemDTO itemDTO) {
        Item item = itemRepository.getItemById(id);
        if (item == null) {
            throw new NullPointerException("Item not found");
        }

        item.setBasePrice(itemDTO.getBasePrice());
        item.setItemName(itemDTO.getItemName());

        switch (itemDTO.getClass().getSimpleName().toLowerCase()) {
            case "musicdto":
                Music music = (Music) item;
                MusicDTO musicDTO = (MusicDTO) itemDTO;
                music.setGenre(musicDTO.getGenre());
                music.setVinyl(musicDTO.isVinyl());
                break;

            case "moviedto":
                Movie movie = (Movie) item;
                MovieDTO movieDTO = (MovieDTO) itemDTO;
                movie.setMinutes(movieDTO.getMinutes());
                movie.setCasette(movieDTO.isCasette());
                break;

            case "comicsdto":
                Comics comics = (Comics) item;
                ComicsDTO comicsDTO = (ComicsDTO) itemDTO;
                comics.setPageNumber(comicsDTO.getPagesNumber());
                break;

            default:
                throw new IllegalArgumentException("Unknown item type: " + itemDTO.getClass().getSimpleName());
        }

        itemRepository.updateItem(item);
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