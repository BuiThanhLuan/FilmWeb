package service;

import DAO.MovieDAO;
import com.mongodb.client.DistinctIterable;
import model.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieService {

    public static List<String> getGenresForHeader() {
        ArrayList<String> list = new ArrayList<>();
        MovieDAO.getTopGenres(15).forEach(d -> list.add(d.getString("_id")));
        return list;
    }

    public static List<Movie> getMoviesForHomePage() {
        List<Movie> list = MovieDAO.getMovies(6);
        if (list.isEmpty()) {
            list.addAll(List.of(new Movie(), new Movie(), new Movie()));
        }
        return list;
    }

    public static DistinctIterable<String> getGenres() {
        return MovieDAO.getGenres();
    }

    public static Movie getMovieByID(String id) {
        return MovieDAO.getMovieByID(id);
    }
}
