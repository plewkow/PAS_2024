package pl.lodz.p.edu.rest.repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.Filters;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;
import pl.lodz.p.edu.rest.model.user.User;


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
}
