package pl.lodz.p.edu.rest.model.item;

import org.bson.codecs.pojo.annotations.BsonDiscriminator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@BsonDiscriminator("comics")
public class Comics extends Item {
    @BsonId
    ObjectId id;
    @BsonProperty("pageNumber")
    private int pageNumber;

    public Comics(@BsonProperty("basePrice") int basePrice,
                  @BsonProperty("itemName") String itemName,
                  @BsonProperty("pageNumber") int pageNumber) {
        super(basePrice, itemName);
        this.itemType = "comics";
        this.pageNumber = pageNumber;
    }

    public Comics() {

    }

    @Override
    public ObjectId getId() {
        return id;
    }

    @Override
    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}