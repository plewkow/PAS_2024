package pl.lodz.p.edu.rest.dto;

import pl.lodz.p.edu.rest.model.item.MusicGenre;

public class MusicDTO extends ItemDTO {
    private MusicGenre genre;
    private boolean vinyl;

    public MusicDTO(int basePrice, String itemName, boolean available, String itemType, MusicGenre genre, boolean vinyl) {
        super(basePrice, itemName, available, itemType);
        this.genre = genre;
        this.vinyl = vinyl;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public boolean isVinyl() {
        return vinyl;
    }

    public void setVinyl(boolean vinyl) {
        this.vinyl = vinyl;
    }
}
