package pl.lodz.p.edu.rest.model.item;

import org.bson.codecs.pojo.annotations.*;
import org.bson.types.ObjectId;

@BsonDiscriminator("movie")
public class Movie extends Item {
    @BsonId
    ObjectId id;
    @BsonProperty("minutes")
    private int minutes;
    @BsonProperty("casette")
    private boolean casette;

    public Movie(@BsonProperty("basePrice") int basePrice,
                 @BsonProperty("itemName") String itemName,
                 @BsonProperty("minutes") int minutes,
                 @BsonProperty("casette") boolean casette) {
        super(basePrice, itemName);
        this.itemType = "movie";
        this.minutes = minutes;
        this.casette = casette;
    }

    public Movie() {

    }

    @Override
    public ObjectId getId() {
        return id;
    }

    @Override
    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public boolean isCasette() {
        return casette;
    }

    public void setCasette(boolean casette) {
        this.casette = casette;
    }
}