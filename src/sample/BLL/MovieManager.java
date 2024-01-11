package sample.BLL;

import sample.BE.Category;
import sample.BE.Movie;
import sample.BLL.util.Searcher;
import sample.DAL.MovieDAO;

import java.io.IOException;
import java.util.List;

public class MovieManager {
    private final MovieDAO movieDAO;
    private Searcher movieSearcher = new Searcher();
    public MovieManager() throws IOException {

        movieDAO = new MovieDAO();
    }

    public List<Movie> getAllMovies() throws Exception {
        return movieDAO.getAllMovies();
    }

    public List<Category> getMovieCategories(Movie movie) throws Exception {
        if(movie.getCategories().isEmpty()){
            movie.setAllCategories(movieDAO.getCategoriesByMovie(movie));
        }
        return movie.getCategories();
    }

    public Movie createMovie(Movie newMovie) throws Exception {
        return movieDAO.createMovie(newMovie);
    }

    public void updateMovie(Movie selectedMovie) throws Exception {
        movieDAO.updateMovie(selectedMovie);
    }

    public void deleteMovie(Movie selectedMovie) throws Exception {
        movieDAO.deleteMovie(selectedMovie);
    }

    public void deleteMovieCategory(Movie selectedMovie) throws Exception {
        movieDAO.deleteMovieCategory(selectedMovie);
    }

    public void addCategoryToMovie(Movie movie, Category category) throws Exception {
        movieDAO.addCategoryToMovie(movie, category);
    }

    public List<Movie> getMoviesByCategory(Category category) throws Exception {
        return movieDAO.getMoviesByCategory(category);
    }
    public List<Movie> searchMovies(String query) throws Exception {
        List<Movie> allMovies = getAllMovies();
        List<Movie> resultMovies = movieSearcher.search(allMovies, query);
        return resultMovies;
    }
}
