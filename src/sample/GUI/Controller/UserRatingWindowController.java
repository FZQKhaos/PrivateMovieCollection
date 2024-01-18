package sample.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import sample.BE.Movie;
import sample.GUI.Model.MovieModel;

import java.net.URL;
import java.util.ResourceBundle;

public class UserRatingWindowController implements Initializable {

    @FXML
    private Label lblStatus;

    @FXML
    private ComboBox<Movie> cbSelectMovie;

    @FXML
    private TextField txtfNewRating;

    private MovieModel movieModel;
    private MovieWindowController movieWindowController;

    public UserRatingWindowController() throws Exception {
        movieModel = new MovieModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbSelectMovie.setItems(movieModel.getObservableMovies());
    }

    public void onActionEdit(ActionEvent actionEvent) throws Exception {
        Movie selectedMovie = cbSelectMovie.getSelectionModel().getSelectedItem();
        try {
            if (selectedMovie != null) {
                double newUserRating = Double.parseDouble(txtfNewRating.getText());
                if (newUserRating <= 10 && newUserRating > 0) {
                    selectedMovie.setUserRating(newUserRating);
                    movieModel.updateMovie(selectedMovie);
                    movieWindowController.updateTable();
                    lblStatus.setTextFill(Color.DARKGREEN);
                    lblStatus.setText("User rating updated for: " + selectedMovie.getTitle());
                } else {
                    lblStatus.setTextFill(Color.DARKRED);
                    lblStatus.setText("User rating cant be higher than 10 or lower than 0");
                }
            } else {
                lblStatus.setTextFill(Color.DARKRED);
                lblStatus.setText("Select a movie to rate");
            }
        } catch (Exception e){
            lblStatus.setTextFill(Color.DARKRED);
            lblStatus.setText("User rating has to be a number between 0 and 10");
        }
    }

    public void setMovieWindowController(MovieWindowController movieWindowController) {
        this.movieWindowController = movieWindowController;
    }
}
