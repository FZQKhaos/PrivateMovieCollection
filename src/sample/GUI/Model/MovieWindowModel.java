package sample.GUI.Model;

import sample.BE.Movie;
import sample.BLL.MovieManager;

import java.io.IOException;
import java.util.List;

public class MovieWindowModel {

    private MovieManager movieManager;

    public MovieWindowModel() throws IOException {
        movieManager = new MovieManager();
    }
    public List<Movie> getAllMovies() throws Exception {
        return movieManager.getAllMovies();
    }
}
