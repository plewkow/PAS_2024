package pl.lodz.p.edu.mvc.model.item;

import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

public class Music extends Item {
    private MusicGenre genre;
    private boolean vinyl;

    public Music(ObjectId id, int basePrice, String itemName, MusicGenre genre, boolean vinyl) {
        super(id, basePrice, itemName);
        this.itemType = "music";
        this.genre = genre;
        this.vinyl = vinyl;
    }

    public Music(ObjectId id, int basePrice, String itemName, boolean available, MusicGenre genre, boolean vinyl) {
        super(id, basePrice, itemName, available);
        this.itemType = "music";
        this.genre = genre;
        this.vinyl = vinyl;
    }

    public Music() {

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