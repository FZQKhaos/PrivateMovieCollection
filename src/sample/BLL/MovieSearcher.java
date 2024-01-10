package sample.BLL;

import sample.BE.Movie;

import java.util.ArrayList;
import java.util.List;

public class MovieSearcher {

    public List<Movie> searchMovie(List<Movie> allMovies, String searchWord) {
        List<Movie> searchResult = new ArrayList<>();
        for (Movie movie : allMovies) {
            if (compareToTitle(searchWord, movie))
            {
                searchResult.add(movie);
            }
        }

        return searchResult;
    }

    private boolean compareToTitle (String searchWord, Movie movie) {
        return movie.getTitle().toLowerCase().contains(searchWord.toLowerCase());
    }
}
