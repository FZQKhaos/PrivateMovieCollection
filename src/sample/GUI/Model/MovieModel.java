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

    public List<Movie> getAllMovies() throws Exception {
        return movieManager.getAllMovies();
    }

    public Movie createMovie(Movie newMovie) throws Exception {
        return movieManager.createMovie(newMovie);
    }

    public void updateMovie(Movie selectedMovie) throws Exception {
        movieManager.updateMovie(selectedMovie);
    }

    public void deleteMovie(Movie selectedMovie) throws Exception {
        movieManager.deleteMovie(selectedMovie);
    }

    public void addCategoryToMovie(Movie movie, Category category) throws Exception {
        movieManager.addCategoryToMovie(movie, category);
    }

    public List<Movie> getMoviesByCategory(Category category) throws Exception {
        return movieManager.getMoviesByCategory(category);
    }
}