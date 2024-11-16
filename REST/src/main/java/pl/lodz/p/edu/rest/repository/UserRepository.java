package pl.lodz.p.edu.rest.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import pl.lodz.p.edu.rest.model.user.Role;
import pl.lodz.p.edu.rest.model.user.User;

import java.util.ArrayList;
import java.util.List;

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

    public void save(User user) {
        userCollection.insertOne(user);
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
}
