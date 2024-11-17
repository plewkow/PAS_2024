package pl.lodz.p.edu.rest.dto;

import org.bson.types.ObjectId;
import pl.lodz.p.edu.rest.model.item.MusicGenre;

public class MusicDTO extends ItemDTO {
    private MusicGenre genre;
    private boolean vinyl;

    public MusicDTO() {

    }

    public MusicDTO(ObjectId id, int basePrice, String itemName, MusicGenre genre, boolean vinyl) {
        super(id, basePrice, itemName);
        this.itemType = "music";
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