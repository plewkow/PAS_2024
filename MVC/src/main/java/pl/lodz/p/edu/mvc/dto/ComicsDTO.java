package pl.lodz.p.edu.mvc.dto;


public class ComicsDTO extends ItemDTO {
    private int pagesNumber;

    public ComicsDTO() {

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