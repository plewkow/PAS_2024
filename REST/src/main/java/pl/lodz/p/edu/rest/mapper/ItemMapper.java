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
                music.getBasePrice(),
                music.getItemName(),
                music.isAvailable(),
                music.getGenre(),
                music.isVinyl()
        );
    }

    public static MovieDTO toMovieDTO(Movie movie) {
        return new MovieDTO(
                movie.getBasePrice(),
                movie.getItemName(),
                movie.isAvailable(),
                movie.getMinutes(),
                movie.isCasette()
        );
    }

    public static ComicsDTO toComicsDTO(Comics comics) {
        return new ComicsDTO(
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
                    item.getBasePrice(),
                    item.getItemName(),
                    item.isAvailable(),
                    music.getGenre(),
                    music.isVinyl()
            );
        } else if (item instanceof Movie) {
            Movie movie = (Movie) item;
            return new MovieDTO(
                    item.getBasePrice(),
                    item.getItemName(),
                    item.isAvailable(),
                    movie.getMinutes(),
                    movie.isCasette()
            );
        } else {
            Comics comics = (Comics) item;
            return new ComicsDTO(
                    item.getBasePrice(),
                    item.getItemName(),
                    item.isAvailable(),
                    comics.getPageNumber()
            );
        }
    }
}
