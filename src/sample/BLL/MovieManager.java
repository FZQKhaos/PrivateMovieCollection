package sample.BLL;

import sample.BE.Movie;
import sample.DAL.MovieDAO;

import java.io.IOException;
import java.util.List;

public class MovieManager {
    private final MovieDAO movieDAO;
    public MovieManager() throws IOException {

        movieDAO = new MovieDAO();
    }

    public List<Movie> getAllMovies() throws Exception {
        return movieDAO.getAllMovies();
    }

    public Movie createNewMovie(Movie newMovie) throws Exception {
        return movieDAO.createMovie(newMovie);
    }

    public void updateMovie(Movie selectedMovie) throws Exception {
        movieDAO.updateMovie(selectedMovie);
    }

    public void deleteMovie(Movie selectedMovie) throws Exception {
        movieDAO.deleteMovie(selectedMovie);
    }
}
