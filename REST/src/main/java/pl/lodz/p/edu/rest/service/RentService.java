package pl.lodz.p.edu.rest.service;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import pl.lodz.p.edu.rest.dto.*;
import pl.lodz.p.edu.rest.mapper.ItemMapper;
import pl.lodz.p.edu.rest.model.Rent;
import pl.lodz.p.edu.rest.model.item.Item;
import pl.lodz.p.edu.rest.model.user.Client;
import pl.lodz.p.edu.rest.repository.ItemRepository;
import pl.lodz.p.edu.rest.repository.MongoEntity;
import pl.lodz.p.edu.rest.repository.RentRepository;
import pl.lodz.p.edu.rest.mapper.UserMapper;

import java.time.LocalDateTime;

@Service
public class RentService {
    private final RentRepository rentRepository;
    private final UserService userService;
    private final ItemService itemService;
    private final MongoEntity mongoEntity;
    private final UserMapper userMapper = new UserMapper();
    private final ItemRepository itemRepository;

    public RentService(RentRepository rentRepository, UserService userService, ItemService itemService, ItemRepository itemRepository) {
        this.rentRepository = rentRepository;
        this.userService = userService;
        this.itemService = itemService;
        mongoEntity = new MongoEntity();
        this.itemRepository = itemRepository;
    }

    public RentDTO rentItem(RentDTO rentDTO) {
        UserDTO userDTO = userService.getUserById(rentDTO.getClientId());
        if (userDTO == null) {
            throw new IllegalArgumentException("Klient nie istnieje.");
        }

        Client client = (Client) userMapper.convertToUser(userDTO);
        System.out.println("Item ID from DTO: " + rentDTO.getItemId());

        ItemDTO itemDTO = itemService.getItemById(rentDTO.getItemId());
        if (itemDTO == null) {
            throw new IllegalArgumentException("Przedmiot nie istnieje.");
        }

        System.out.println(itemDTO.getItemName());

        System.out.println("Item ID from DTO: " + rentDTO.getItemId());

        Item item = ItemMapper.toItem(itemDTO);

        item.setId(rentDTO.getItemId());

        if (item == null || item.getId() == null) {
            throw new IllegalArgumentException("Przedmiot nie został znaleziony w bazie.");
        }

        if (!item.isAvailable()) {
            throw new IllegalStateException("Przedmiot jest już wypożyczony.");
        }

        try (var session = mongoEntity.getMongoClient().startSession()) {
            session.startTransaction();

            itemService.setUnavailable(item.getId());

            Rent rent = new Rent(rentDTO.getBeginTime(), client, item);
            rentRepository.addRent(rent);

            session.commitTransaction();

            return new RentDTO(
                    rent.getBeginTime(),
                    rent.getEndTime(),
                    rent.getRentCost(),
                    rent.isArchive(),
                    rentDTO.getClientId(),
                    rentDTO.getItemId()
            );
        } catch (Exception e) {
            throw new RuntimeException("Błąd podczas wynajmu: " + e.getMessage(), e);
        }
    }

    public RentDTO getRentById(ObjectId rentId) {
        Rent rent = rentRepository.getRent(rentId);
        if (rent == null) {
            throw new IllegalArgumentException("Wynajem o podanym ID nie istnieje.");
        }

        RentDTO rentDTO = new RentDTO(
                rent.getBeginTime(),
                rent.getEndTime(),
                rent.getRentCost(),
                rent.isArchive(),
                rent.getClient().getId(),
                rent.getItem().getId()
        );

        return rentDTO;
    }

    public RentDTO returnRent(ObjectId rentId) {
        Rent rent = rentRepository.getRent(rentId);
        if (rent == null) {
            throw new IllegalArgumentException("Wynajem o podanym ID nie istnieje.");
        }

        Item item = rent.getItem();
        if (item == null) {
            throw new IllegalArgumentException("Przedmiot nie istnieje w bazie.");
        }

        LocalDateTime end = LocalDateTime.now();

        rent.setEndTime(end);

        item.setAvailable(true);
        itemRepository.updateItem(item);
        rentRepository.updateRent(rent);

        return new RentDTO(
                rent.getBeginTime(),
                rent.getEndTime(),
                rent.getRentCost(),
                rent.isArchive(),
                rent.getClient().getId(),
                rent.getItem().getId()
        );
    }
}