package pl.lodz.p.edu.rest.mapper;

import pl.lodz.p.edu.rest.dto.ComicsDTO;
import pl.lodz.p.edu.rest.dto.ItemDTO;
import pl.lodz.p.edu.rest.dto.MovieDTO;
import pl.lodz.p.edu.rest.dto.MusicDTO;
import pl.lodz.p.edu.rest.model.item.*;

public class ItemMapper {
    public static Music toMusic(MusicDTO dto) {
        return new Music(dto.getBasePrice(), dto.getItemName(), dto.getGenre(), dto.isVinyl());
    }

    public static Movie toMovie(MovieDTO dto) {
        return new Movie(dto.getBasePrice(), dto.getItemName(), dto.getMinutes(), dto.isCasette());
    }

    public static Comics toComics(ComicsDTO dto) {
        return new Comics(dto.getBasePrice(), dto.getItemName(), dto.getPagesNumber());
    }

    public static MusicDTO toMusicDTO(Music music) {
        return new MusicDTO(
                music.getId(),
                music.getBasePrice(),
                music.getItemName(),
                music.isAvailable(),
                music.getGenre(),
                music.isVinyl()
        );
    }

    public static MovieDTO toMovieDTO(Movie movie) {
        return new MovieDTO(
                movie.getId(),
                movie.getBasePrice(),
                movie.getItemName(),
                movie.isAvailable(),
                movie.getMinutes(),
                movie.isCasette()
        );
    }

    public static ComicsDTO toComicsDTO(Comics comics) {
        return new ComicsDTO(
                comics.getId(),
                comics.getBasePrice(),
                comics.getItemName(),
                comics.isAvailable(),
                comics.getPageNumber()
        );
    }

    public static ItemDTO toDTO(Item item) {
        if (item instanceof Music) {
            Music music = (Music) item;
            return new MusicDTO(
                    item.getId(),
                    item.getBasePrice(),
                    item.getItemName(),
                    item.isAvailable(),
                    music.getGenre(),
                    music.isVinyl()
            );
        } else if (item instanceof Movie) {
            Movie movie = (Movie) item;
            return new MovieDTO(
                    item.getId(),
                    item.getBasePrice(),
                    item.getItemName(),
                    item.isAvailable(),
                    movie.getMinutes(),
                    movie.isCasette()
            );
        } else {
            Comics comics = (Comics) item;
            return new ComicsDTO(
                    item.getId(),
                    item.getBasePrice(),
                    item.getItemName(),
                    item.isAvailable(),
                    comics.getPageNumber()
            );
        }
    }

    public static Item toItem(ItemDTO itemDTO) {
        switch (itemDTO.getItemType().toLowerCase()) {
            case "music":
                return toMusic((MusicDTO) itemDTO);
            case "movie":
                return toMovie((MovieDTO) itemDTO);
            case "comics":
                return toComics((ComicsDTO) itemDTO);
            default:
                throw new IllegalArgumentException("Nieznany type: " + itemDTO.getItemType());
        }
    }
}