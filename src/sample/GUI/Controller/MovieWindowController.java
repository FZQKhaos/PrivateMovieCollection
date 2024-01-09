package sample.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import sample.BE.Movie;
import sample.GUI.Model.MovieModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class MovieWindowController implements Initializable {

    @FXML
    private TableColumn<Movie, String> colTitle, colCategory;
    @FXML
    private TableColumn<Movie, Double> colIR, colUR;
    @FXML
    private TableView tblMovies;
    @FXML
    private Button EditRating;
    @FXML
    private TextField txtSearchField;
    private MovieModel movieWindowModel;

    public MovieWindowController() throws Exception {

        movieWindowModel = new MovieModel();
    }

    private void showError() {
        // Make error message appear
    }

    public void onActionNewMovie(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NewMovieWindow.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);

        stage.setTitle("New Movie");
        stage.show();
    }

    public void onActionRemoveMovie(ActionEvent actionEvent) {

    }

    public void onActionAddRemoveCategory(ActionEvent actionEvent) {

    }

    public void onActionAddEditUR(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/UserRating.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.setTitle("Add/Edit User Rating");
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("category"));
        colIR.setCellValueFactory(new PropertyValueFactory<>("imdbRating"));
        colUR.setCellValueFactory(new PropertyValueFactory<>("userRating"));

        tblMovies.setItems(movieWindowModel.getObservableMovies());
    }
}
