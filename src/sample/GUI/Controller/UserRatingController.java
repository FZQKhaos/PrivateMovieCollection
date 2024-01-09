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

public class UserRatingController implements Initializable {

    @FXML
    private Label lblStatus;

    @FXML
    private ComboBox<Movie> cbSelectMovie;

    @FXML
    private TextField txtfNewRating;

    private MovieModel movieModel;

    public UserRatingController() throws Exception {
        movieModel = new MovieModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbSelectMovie.setItems(movieModel.getObservableMovies());
    }

    public void onActionEdit(ActionEvent actionEvent) throws Exception {
        Movie selectedMovie =cbSelectMovie.getSelectionModel().getSelectedItem();

        selectedMovie.setUserRating(Double.parseDouble(txtfNewRating.getText()));

        movieModel.updateMovie(selectedMovie);

        lblStatus.setTextFill(Color.DARKGREEN);
        lblStatus.setText("Userrating updated for: " + selectedMovie.getTitle());
    }
}
