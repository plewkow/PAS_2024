package pl.lodz.p.edu.rest.dto;

public class ItemDTO {
    private int basePrice;
    private String itemName;
    private boolean available;
    private String itemType;

    public ItemDTO(int basePrice, String itemName, boolean available, String itemType) {
        this.basePrice = basePrice;
        this.itemName = itemName;
        this.available = available;
        this.itemType = itemType;
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
