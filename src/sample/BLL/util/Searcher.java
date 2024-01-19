package sample.BLL.util;

import sample.BE.Movie;

import java.util.ArrayList;
import java.util.List;

public class Searcher {

    public List<Movie> search(List<Movie> searchBase, String query) {
        List<Movie> searchResult = new ArrayList<>();

        for (Movie movie : searchBase) {
            if (matchesQuery(movie, query)) {
                searchResult.add(movie);
            }
        }
        return searchResult;
    }

    private boolean matchesQuery(Movie movie, String query) {
        return compareToMovieTitle(movie, query);
    }

    private boolean compareToMovieTitle(Movie movie, String query) {
        return movie.getTitle().toLowerCase().contains(query.toLowerCase());
    }
}
