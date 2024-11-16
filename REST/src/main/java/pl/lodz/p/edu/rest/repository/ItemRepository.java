package pl.lodz.p.edu.rest.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.annotation.ApplicationScope;
import pl.lodz.p.edu.rest.model.item.Item;
import org.bson.types.ObjectId;

@Repository
@Transactional
@ApplicationScope
public class ItemRepository {
    private final MongoCollection<Item> itemCollection;

    public ItemRepository(MongoCollection<Item> itemCollection) {
        this.itemCollection = itemCollection;
    }

    public ObjectId addItem(Item item) {
        InsertOneResult result = itemCollection.insertOne(item);
        item.setId(result.getInsertedId().asObjectId().getValue());
        return result.getInsertedId().asObjectId().getValue();
    }

    public Item getItem(ObjectId id) {
        return itemCollection.find(Filters.eq("_id", id)).first();
    }

    public void updateItem(Item item) {
        BasicDBObject object = new BasicDBObject();
        object.put("_id", item.getId());
        itemCollection.replaceOne(object, item);
    }

    public void removeItem(ObjectId id) {
        BasicDBObject object = new BasicDBObject();
        object.put("_id", id);
        itemCollection.deleteOne(object);
    }
}