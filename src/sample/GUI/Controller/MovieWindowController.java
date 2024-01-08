package sample.GUI.Controller;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import sample.BE.Movie;
import sample.GUI.Model.MovieWindowModel;

import java.io.IOException;
import java.util.List;


public class MovieWindowController {

    @FXML
    private TableView tblMovies;
    @FXML
    private Button EditRating;
    @FXML
    private TextField txtSearchField;
    private MovieWindowModel movieWindowModel;

    public MovieWindowController() throws IOException {

        movieWindowModel = new MovieWindowModel();
    }

    public void initialize()  {
        try {
            List<Movie> movies = movieWindowModel.getAllMovies();
            tblMovies.getItems().clear();
            tblMovies.setItems(FXCollections.observableList(movies));

        }
        catch(Exception e) {
            showError();
        }
    }

    private void showError() {
        // Make error message appear
    }

    public void onActionNewMovie(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/NewMovieWindow.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.setTitle("New Movie");
        stage.show();
    }

    public void onActionRemoveMovie(ActionEvent actionEvent) {

    }

    public void onActionAddRemoveCategory(ActionEvent actionEvent) {

    }

    public void onActionAddEditUR(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserRating.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.setTitle("Add/Edit User Rating");
        stage.show();
    }
}
