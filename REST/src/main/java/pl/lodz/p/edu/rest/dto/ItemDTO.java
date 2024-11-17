package pl.lodz.p.edu.rest.dto;

import org.bson.types.ObjectId;

public class ItemDTO {
    ObjectId id;
    private int basePrice;
    private String itemName;
    private boolean available = true;
    protected String itemType;

    public ItemDTO() {
    }

    public ItemDTO(ObjectId id, int basePrice, String itemName) {
        this.id = id;
        this.basePrice = basePrice;
        this.itemName = itemName;
//        this.available = available;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}