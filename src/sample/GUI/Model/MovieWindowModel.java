package sample.GUI.Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.BE.Movie;
import sample.BLL.MovieManager;

import java.io.IOException;
import java.util.List;

public class MovieWindowModel {

    private MovieManager movieManager;
    private ObservableList<Movie> observableMovies;

    public MovieWindowModel() throws Exception {

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
}
