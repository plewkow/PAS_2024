package pl.lodz.p.edu.mvc.model.item;

import org.bson.types.ObjectId;

public class Item {
    private ObjectId id;
    protected int basePrice;
    protected String itemName;
    protected boolean available;
    protected String itemType;

    public Item(ObjectId id, int basePrice, String itemName) {
        this.id = id;
        this.basePrice = basePrice;
        this.itemName = itemName;
        this.available = true;
    }

    public Item(ObjectId id, int basePrice, String itemName, boolean available) {
        this.id = id;
        this.basePrice = basePrice;
        this.itemName = itemName;
        this.available = available;
    }

    public Item() {

    }

    public String getItemInfo() {
        return "Item ID: " + id + ", Name: " + itemName + ", Base Price: " + basePrice;
    }

    public int getActualPrice() {
        return basePrice;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public String getItemName() {
        return itemName;
    }

    public boolean isAvailable() {
        return available;
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }
}