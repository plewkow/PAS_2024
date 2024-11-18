package pl.lodz.p.edu.rest.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.RequestScope;
import pl.lodz.p.edu.rest.dto.ComicsDTO;
import pl.lodz.p.edu.rest.dto.ItemDTO;
import pl.lodz.p.edu.rest.dto.MovieDTO;
import pl.lodz.p.edu.rest.dto.MusicDTO;
import pl.lodz.p.edu.rest.exception.InvalidItemTypeException;
import pl.lodz.p.edu.rest.exception.ItemAlreadyRentedException;
import pl.lodz.p.edu.rest.exception.ItemNotFoundException;
import pl.lodz.p.edu.rest.mapper.ItemMapper;
import pl.lodz.p.edu.rest.model.Rent;
import pl.lodz.p.edu.rest.model.item.*;
import pl.lodz.p.edu.rest.repository.ItemRepository;
import pl.lodz.p.edu.rest.repository.RentRepository;

import java.util.List;
import java.util.stream.Collectors;

@RequestScope
@Service
public class ItemService {
    private final ItemRepository itemRepository;
    private final RentRepository rentRepository;

    @Autowired
    public ItemService(ItemRepository itemRepository, RentRepository rentRepository) {
        this.itemRepository = itemRepository;
        this.rentRepository = rentRepository;
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
                System.out.println(movie.getId());
                id = itemRepository.addItem(movie);
                addedItem = itemRepository.getItemById(id);
                System.out.println(addedItem.getId());
                return ItemMapper.toMovieDTO((Movie) addedItem);

            case "comics":
                Comics comics = ItemMapper.toComics((ComicsDTO) itemDTO);
                id = itemRepository.addItem(comics);
                addedItem = itemRepository.getItemById(id);
                return ItemMapper.toComicsDTO((Comics) addedItem);

            default:
                throw new InvalidItemTypeException("Invalid item type: " + itemDTO.getItemType());
        }
    }

    public ItemDTO getItemById(ObjectId id) {
        Item item = itemRepository.getItemById(id);
        if (item == null) {
            throw new ItemNotFoundException("Item with ID: " + id + " not found");
        }
        return ItemMapper.toDTO(item);
    }

    public List<ItemDTO> getItemsByBasePrice(int basePrice) {
        List<Item> items = itemRepository.getItemsByBasePrice(basePrice);
        return ItemMapper.toDTOList(items);
//        return items.stream()
//                .map(item -> {
//                    if (item instanceof Music) {
//                        Music music = (Music) item;
//                        return new MusicDTO(
//                                music.getId(),
//                                music.getBasePrice(),
//                                music.getItemName(),
//                                music.getGenre(),
//                                music.isVinyl()
//                        );
//                    } else if (item instanceof Movie) {
//                        Movie movie = (Movie) item;
//                        return new MovieDTO(
//                                movie.getId(),
//                                movie.getBasePrice(),
//                                movie.getItemName(),
//                                movie.getMinutes(),
//                                movie.isCasette()
//                        );
//                    } else if (item instanceof Comics) {
//                        Comics comics = (Comics) item;
//                        return new ComicsDTO(
//                                comics.getId(),
//                                comics.getBasePrice(),
//                                comics.getItemName(),
//                                comics.getPageNumber()
//                        );
//                    } else {
//                        throw new InvalidItemTypeException("Unknown item type: " + item.getClass());
//                    }
//                })
//                .collect(Collectors.toList());
    }

    public List<ItemDTO> getItemsByItemName(String itemName) {
        List<Item> items = itemRepository.getItemsByItemName(itemName);
        return ItemMapper.toDTOList(items);
//        return items.stream()
//                .map(item -> {
//                    if (item instanceof Music) {
//                        Music music = (Music) item;
//                        return new MusicDTO(
//                                music.getId(),
//                                music.getBasePrice(),
//                                music.getItemName(),
//                                music.getGenre(),
//                                music.isVinyl()
//                        );
//                    } else if (item instanceof Movie) {
//                        Movie movie = (Movie) item;
//                        return new MovieDTO(
//                                movie.getId(),
//                                movie.getBasePrice(),
//                                movie.getItemName(),
//                                movie.getMinutes(),
//                                movie.isCasette()
//                        );
//                    } else if (item instanceof Comics) {
//                        Comics comics = (Comics) item;
//                        return new ComicsDTO(
//                                comics.getId(),
//                                comics.getBasePrice(),
//                                comics.getItemName(),
//                                comics.getPageNumber()
//                        );
//                    } else {
//                        throw new InvalidItemTypeException("Unknown item type: " + item.getClass());
//                    }
//                })
//                .collect(Collectors.toList());
    }

    public List<ItemDTO> getItemsByItemType(String itemType) {
        List<Item> items = itemRepository.getItemsByItemType(itemType);
        return ItemMapper.toDTOList(items);
//        return items.stream()
//                .map(item -> {
//                    if (item instanceof Music) {
//                        Music music = (Music) item;
//                        return new MusicDTO(
//                                music.getId(),
//                                music.getBasePrice(),
//                                music.getItemName(),
//                                music.getGenre(),
//                                music.isVinyl()
//                        );
//                    } else if (item instanceof Movie) {
//                        Movie movie = (Movie) item;
//                        return new MovieDTO(
//                                movie.getId(),
//                                movie.getBasePrice(),
//                                movie.getItemName(),
//                                movie.getMinutes(),
//                                movie.isCasette()
//                        );
//                    } else if (item instanceof Comics) {
//                        Comics comics = (Comics) item;
//                        return new ComicsDTO(
//                                comics.getId(),
//                                comics.getBasePrice(),
//                                comics.getItemName(),
//                                comics.getPageNumber()
//                        );
//                    } else {
//                        throw new InvalidItemTypeException("Unknown item type: " + item.getClass());
//                    }
//                })
//                .collect(Collectors.toList());
    }

    public void updateItem(ObjectId id, ItemDTO itemDTO) {
        Item item = itemRepository.getItemById(id);
        if (item == null) {
            throw new ItemNotFoundException("Item with ID: " + id + " not found");
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
                throw new InvalidItemTypeException("Unknown item type: " + itemDTO.getClass().getSimpleName());
        }

        itemRepository.updateItem(item);
    }

    public void removeItem(ObjectId id) {
        Item item = itemRepository.getItemById(id);
        if (item == null) {
            throw new ItemNotFoundException("Item with ID: " + id + " not found");
        }

        List<Rent> activeRents = rentRepository.findActiveRentsByItemId(id);
        if (!activeRents.isEmpty()) {
            throw new ItemAlreadyRentedException("Item cannot be removed because it is currently rented.");
        }
        itemRepository.removeItem(id);
    }

    public void setAvailable(ObjectId id) {
        Item item = itemRepository.getItemById(id);
        if (item == null) {
            throw new ItemNotFoundException("Item with ID: " + id + " not found");
        }
        item.setAvailable(true);
        itemRepository.updateItem(item);
    }

    public void setUnavailable(ObjectId id) {
        Item item = itemRepository.getItemById(id);
        if (item == null) {
            throw new ItemNotFoundException("Item with ID: " + id + " not found");
        }
        item.setAvailable(false);
        itemRepository.updateItem(item);
    }
}