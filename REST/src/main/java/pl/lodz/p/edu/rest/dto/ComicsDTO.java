package pl.lodz.p.edu.rest.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class ComicsDTO extends ItemDTO {
    @NotNull(message = "Pages number cannot be null")
    @Min(value = 0, message = "Pages number must be at least 0")
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