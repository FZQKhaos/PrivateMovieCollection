package sample.GUI.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BE.Category;
import sample.BE.Movie;
import sample.BLL.MovieManager;

import java.util.List;

public class MovieModel {

    private MovieManager movieManager;
    private ObservableList<Movie> observableMovies;

    public MovieModel() throws Exception {
        movieManager = new MovieManager();

        observableMovies = FXCollections.observableArrayList();
        observableMovies.addAll(movieManager.getAllMovies());
    }

    public ObservableList<Movie> getObservableMovies() {
        return observableMovies;
    }

    public List<Category> getMovieCategories(Movie movie) throws Exception {
        return movieManager.getMovieCategories(movie);
    }

    public void createMovie(Movie newMovie) throws Exception {
        movieManager.createMovie(newMovie);
    }

    public void updateMovie(Movie selectedMovie) throws Exception {
        movieManager.updateMovie(selectedMovie);
    }

    public void deleteMovie(Movie selectedMovie) throws Exception {
        movieManager.deleteMovie(selectedMovie);
    }

    public void deleteMovieCategory(Movie selectedMovie) throws Exception {
        movieManager.deleteMovieCategory(selectedMovie);
    }

    public void addCategoryToMovie(Movie movie, Category category) throws Exception {
        movieManager.addCategoryToMovie(movie, category);
    }

   public List <Movie>getMovieByCategory(Category category) throws Exception{
        return movieManager.getMoviesByCategory(category);
   }
    public void updateMoviesByCategory(Category category) {
        try {
            List<Movie> moviesByCategory = movieManager.getMoviesByCategory(category);
            observableMovies.clear();
            observableMovies.addAll(moviesByCategory);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchMovies(String query) throws Exception {
        List<Movie> searchResults = movieManager.searchMovies(query);
        observableMovies.clear();
        observableMovies.addAll(searchResults);
    }
}