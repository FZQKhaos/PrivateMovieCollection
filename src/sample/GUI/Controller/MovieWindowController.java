package sample.GUI.Controller;

import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseButton;
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

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Date;
import java.util.ResourceBundle;


public class MovieWindowController implements Initializable {

    public TableColumn<Movie, String> colLastViewed;
    @FXML
    private TableColumn<Movie, String> colTitle, colCategory;
    @FXML
    private TableColumn<Movie, Double> colIR, colUR;
    @FXML
    private TableView<Movie> tblMovies;
    @FXML
    private Button EditRating;
    @FXML
    private TextField txtSearchField;

    private MovieModel movieModel;

    private String folder = "data" + File.separator;

    public MovieWindowController() throws Exception {

        movieModel = new MovieModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("categories"));
        colIR.setCellValueFactory(new PropertyValueFactory<>("imdbRating"));
        colUR.setCellValueFactory(new PropertyValueFactory<>("userRating"));
        colLastViewed.setCellValueFactory(new PropertyValueFactory<>("lastView"));

        for (Movie movie: movieModel.getObservableMovies()){
            try {
                movieModel.getMovieCategories(movie);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        tblMovies.setItems(movieModel.getObservableMovies());

        tblMovies.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Movie selectedMovie = tblMovies.getSelectionModel().getSelectedItem();
                if (selectedMovie != null) {
                    String videoFilePath = selectedMovie.getFilePath();
                    playVideo(videoFilePath);
                    try {
                        Date lastviewed = new Date();
                        selectedMovie.setLastView(String.valueOf(lastviewed));
                        movieModel.updateMovie(selectedMovie);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });
        
    }

    private void playVideo(String filePath) {
        File file = new File(folder + filePath);
        if (file.exists()) {
            try {
                Desktop.getDesktop().open(file);
            } catch (IOException e) {
                System.out.println("Error opening the media file: " + e.getMessage());
            }
        } else {
            System.out.println("File does not exist: " + filePath);
        }
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

    public void onActionRemoveMovie(ActionEvent actionEvent) throws Exception {
        Movie selectedMovie = tblMovies.getSelectionModel().getSelectedItem();

        if (selectedMovie != null){
            movieModel.deleteMovieCategory(selectedMovie);
            movieModel.deleteMovie(selectedMovie);
        }
    }

    public void onActionAddRemoveCategory(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NewCategoryWindow.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.setTitle("Add category");
        stage.show();
    }

    public void onActionAddEditUR(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/UserRating.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.setTitle("Add/Edit User Rating");
        stage.show();
    }

    public void onActionAddCategoryToMovie(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddCategoryToMovieWindow.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);

        stage.setTitle("Add Category to Movie");
        stage.show();
    }

    public void onActionEditMovie(ActionEvent actionEvent) throws IOException {
        Movie selectedMovie = tblMovies.getSelectionModel().getSelectedItem();
        if (selectedMovie != null) {
            // Needs Exception handling
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/NewMovieWindow.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));
            NewMovieWindowController controller = loader.getController();
            controller.setSelectedMovie(selectedMovie);
            stage.show();
        }
    }
}
