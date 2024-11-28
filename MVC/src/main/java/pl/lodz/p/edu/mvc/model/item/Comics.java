package pl.lodz.p.edu.mvc.model.item;

import org.bson.types.ObjectId;

public class Comics extends Item {
    private int pageNumber;

    public Comics(ObjectId id, int basePrice, String itemName, int pageNumber) {
        super(id, basePrice, itemName);
        this.itemType = "comics";
        this.pageNumber = pageNumber;
    }

    public Comics(ObjectId id, int basePrice, String itemName, boolean available, int pageNumber) {
        super(id, basePrice, itemName, available);
        this.itemType = "comics";
        this.pageNumber = pageNumber;
    }

    public Comics() {

    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}