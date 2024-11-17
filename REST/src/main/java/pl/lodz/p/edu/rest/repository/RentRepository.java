package pl.lodz.p.edu.rest.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import org.springframework.stereotype.Repository;
import pl.lodz.p.edu.rest.model.Rent;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RentRepository extends AbstractMongoEntity {
    private final MongoCollection<Rent> rentCollection;

//    public RentRepository(MongoCollection<Rent> rentCollection) {
//        this.rentCollection = rentCollection;
//    }

    public RentRepository() {
        initDbConnection();
        this.rentCollection = database.getCollection("rents", Rent.class);
    }

    public ObjectId addRent(Rent rent) {
        InsertOneResult result = rentCollection.insertOne(rent);
        rent.setId(result.getInsertedId().asObjectId().getValue());
        return result.getInsertedId().asObjectId().getValue();
    }

    public Rent getRent(ObjectId id) {
        return rentCollection.find(Filters.eq("_id", id)).first();
    }

    public void updateRent(Rent rent) {
        BasicDBObject object = new BasicDBObject();
        object.put("_id", rent.getId());
        rentCollection.replaceOne(object, rent);
    }

    public void removeRent(ObjectId id) {
        BasicDBObject object = new BasicDBObject();
        object.put("_id", id);
        rentCollection.deleteOne(object);
    }

    public List<Rent> findActiveRents() {
        return rentCollection.find(Filters.eq("endTime", null)).into(new ArrayList<>());
    }

    public List<Rent> findRentsByItemId(ObjectId itemId) {
        return rentCollection.find(Filters.eq("itemId", itemId)).into(new ArrayList<>());
    }

    public List<Rent> findActiveRentsByItemId(ObjectId itemId) {
        return rentCollection.find(Filters.and(
                Filters.eq("itemId", itemId),
                Filters.eq("endTime", null)
        )).into(new ArrayList<>());
    }

    public List<Rent> findRentsByClientId(ObjectId clientId) {
        return rentCollection.find(Filters.eq("clientId", clientId)).into(new ArrayList<>());
    }

    public List<Rent> findActiveRentsByClientId(ObjectId clientId) {
        return rentCollection.find(Filters.and(
                Filters.eq("clientId", clientId),
                Filters.eq("endTime", null)
        )).into(new ArrayList<>());
    }

    @Override
    public void close() throws Exception {

    }
}