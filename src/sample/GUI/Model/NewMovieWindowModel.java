package sample.GUI.Model;

import sample.BE.Movie;
import sample.BLL.MovieManager;

import java.io.IOException;
import java.util.List;

public class NewMovieWindowModel {
    private final MovieManager movieManager;

    public NewMovieWindowModel() throws IOException {

        movieManager = new MovieManager();
    }
}
