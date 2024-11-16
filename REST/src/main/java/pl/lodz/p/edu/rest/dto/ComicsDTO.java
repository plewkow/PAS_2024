package pl.lodz.p.edu.rest.dto;

public class ComicsDTO extends ItemDTO {
    private int pagesNumber;

    public ComicsDTO(int basePrice, String itemName, boolean available, String itemType, int pagesNumber) {
        super(basePrice, itemName, available, itemType);
        this.pagesNumber = pagesNumber;
    }

    public int getPagesNumber() {
        return pagesNumber;
    }

    public void setPagesNumber(int pagesNumber) {
        this.pagesNumber = pagesNumber;
    }
}
