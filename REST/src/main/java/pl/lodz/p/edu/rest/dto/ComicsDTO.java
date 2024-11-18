package pl.lodz.p.edu.rest.dto;

import org.bson.types.ObjectId;

public class ComicsDTO extends ItemDTO {
    private int pagesNumber;

    public ComicsDTO() {

    }

    public ComicsDTO(String id, int basePrice, String itemName, int pagesNumber) {
//        super(id, basePrice, itemName);
//        this.itemType = "comics";
//        this.pagesNumber = pagesNumber;
        this(id, basePrice, itemName, true, pagesNumber);
    }

    public ComicsDTO(String id, int basePrice, String itemName, boolean available, int pagesNumber) {
        super(id, basePrice, itemName, available);
        this.itemType = "comics";
        this.pagesNumber = pagesNumber;
    }

    public int getPagesNumber() {
        return pagesNumber;
    }

    public void setPagesNumber(int pagesNumber) {
        this.pagesNumber = pagesNumber;
    }
}