package sample.BLL;

import sample.BE.Category;
import sample.BE.Movie;
import sample.BLL.util.Searcher;
import sample.DAL.MovieDAO;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MovieManager {
    private final MovieDAO movieDAO;
    private Searcher movieSearcher = new Searcher();
    private List<Movie> allMovies = new ArrayList<>();
    private boolean shouldUpdate;

    public MovieManager() throws IOException {

        movieDAO = new MovieDAO();
    }

    public List<Movie> getAllMovies() throws Exception {
        if (allMovies.isEmpty() || shouldUpdate){
            allMovies = movieDAO.getAllMovies();
            shouldUpdate = false;
        }

        return allMovies;
    }

    public List<Category> getMovieCategories(Movie movie) throws Exception {
        if(movie.getCategories().isEmpty()){
            movie.setAllCategories(movieDAO.getCategoriesByMovie(movie));
        }
        return movie.getCategories();
    }

    public Movie createMovie(Movie newMovie) throws Exception {
        shouldUpdate = true;
        return movieDAO.createMovie(newMovie);
    }

    public void updateMovie(Movie selectedMovie) throws Exception {
        shouldUpdate = true;
        movieDAO.updateMovie(selectedMovie);
    }

    public void deleteMovie(Movie selectedMovie) throws Exception {
        shouldUpdate = true;
        movieDAO.deleteMovie(selectedMovie);
    }

    public void deleteMovieCategory(Movie selectedMovie) throws Exception {
        shouldUpdate = true;
        movieDAO.deleteMovieCategory(selectedMovie);
    }

    public void addCategoryToMovie(Movie movie, Category category) throws Exception {
        shouldUpdate = true;
        movieDAO.addCategoryToMovie(movie, category);
    }

    public List<Movie> getMoviesByCategory(Category category) throws Exception {
        return movieDAO.getMoviesByCategory(category);
    }
    public List<Movie> searchMovies(String query) throws Exception {
        List<Movie> allMovies = getAllMovies();
        return movieSearcher.search(allMovies, query);
    }

    public List<Movie> searchMoviesByCategory(String query, Category category) throws Exception {
        List<Movie> moviesByCategory = getMoviesByCategory(category);
        return movieSearcher.search(moviesByCategory, query);
    }
}
