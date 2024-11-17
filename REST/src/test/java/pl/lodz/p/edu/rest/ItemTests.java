//package pl.lodz.p.edu.rest;
//
//import com.mongodb.client.MongoCollection;
//import com.mongodb.client.MongoDatabase;
//import org.junit.jupiter.api.AfterAll;
//import org.junit.jupiter.api.BeforeAll;
//import pl.lodz.p.edu.rest.model.item.Item;
//import pl.lodz.p.edu.rest.repository.ItemRepository;
//import pl.lodz.p.edu.rest.repository.MongoEntity;
//import pl.lodz.p.edu.rest.service.ItemService;
//
//public class ItemTests {
//    private static MongoEntity mongoEntity;
//    private static MongoDatabase database;
//    private static MongoCollection<Item> itemCollection;
//    private static ItemRepository itemRepository;
//    private static ItemService itemService;
//
//    @BeforeAll
//    static void setUp() {
//        mongoEntity = new MongoEntity();
//        database = mongoEntity.getDatabase();
//        itemCollection = database.getCollection("items", Item.class);
//        itemRepository = new ItemRepository(itemCollection);
//        itemService = new ItemService(itemRepository);
//    }
//
//    @AfterAll
//    static void tearDown() throws Exception {
//        mongoEntity.close();
//    }
//
//}
