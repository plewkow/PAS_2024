package pl.lodz.p.edu.rest.repository;

import com.mongodb.BasicDBObject;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.InsertOneResult;
import com.mongodb.client.result.UpdateResult;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import pl.lodz.p.edu.rest.model.user.Role;
import pl.lodz.p.edu.rest.model.user.User;

import java.util.ArrayList;
import java.util.List;

import static com.mongodb.client.model.Updates.combine;
import static com.mongodb.client.model.Updates.set;

@Repository
public class UserRepository extends AbstractMongoEntity {
    private MongoCollection<User> userCollection;

    public UserRepository() {
        initDbConnection();
        userCollection = database.getCollection("users", User.class);
    }

    @Override
    public void close() throws Exception {
        mongoClient.close();
    }

    public ObjectId save(User user) {
        InsertOneResult result = userCollection.insertOne(user);
        return result.getInsertedId().asObjectId().getValue();
    }
    
    public User findById(ObjectId id) {
        return userCollection.find(Filters.eq("_id", id)).first();
    }

    public List<User> findByRole(Role role) {
        return userCollection.find(Filters.eq("role", role)).into(new ArrayList<>());
    }

    public List<User> findAll() {
        return userCollection.find().into(new ArrayList<>());
    }

    public UpdateResult update(ObjectId id, String firstName, String lastName) {
        return userCollection.updateOne(
                Filters.eq("_id", id),
                combine(
                        set("firstName", firstName),
                        set("lastName", lastName)
                )
        );
    }

    public UpdateResult activateUser(ObjectId id) {
        return userCollection.updateOne(
                Filters.eq("_id", id),
                set("active", true)
        );
    }

    public UpdateResult deactivateUser(ObjectId id) {
        return userCollection.updateOne(
                Filters.eq("_id", id),
                set("active", false)
        );
    }

    public boolean userExists(String login) {
        return userCollection.find(Filters.eq("login", login)).first() != null;
    }

    public User findByLogin(String login) {
        return userCollection.find(Filters.eq("login", login)).first();
    }
}
