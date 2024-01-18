package sample.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.BE.Movie;
import sample.GUI.Model.MovieModel;

import java.io.File;
import java.time.LocalDate;


public class NewMovieWindowController {

    @FXML
    private TextField txtRating, txtTitle, txtFile;
    @FXML
    private Button btnCancel;

    private MovieModel movieModel;
    private Movie selectedMovie;
    private Movie updatedMovie;

    private MovieWindowController movieWindowController;

    public NewMovieWindowController() throws Exception {
        movieModel = new MovieModel();
    }

    public void setMovieWindowController(MovieWindowController movieWindowController){
        this.movieWindowController = movieWindowController;
    }

    public void onActionChoose(ActionEvent actionEvent) {
        Stage stage = new Stage();

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Movie File");
        fileChooser.setInitialDirectory(new File("data"));
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Movies", "*.mp4","*.mpeg4" )
        );
        File selectedFile = fileChooser.showOpenDialog(stage);

        if (selectedFile != null){
            txtTitle.setText(selectedFile.getName());
            txtFile.setText(selectedFile.getName());
        }

    }

    public void onActionCancel(ActionEvent actionEvent) {
        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }

    public void onActionSave(ActionEvent actionEvent) throws Exception {

        if (selectedMovie != null) {
            // editing
            movieModel.updateMovie(getUserInput());
            movieWindowController.addToTable(updatedMovie);
            movieWindowController.updateTable();
        }
        else {
            // creating
            LocalDate currentDate = LocalDate.now();
            updatedMovie = new Movie(Double.parseDouble(txtRating.getText()), txtTitle.getText(), txtFile.getText(), currentDate);
            movieModel.createMovie(updatedMovie);
            movieWindowController.addToTable(updatedMovie);
        }

        selectedMovie = null;
        Stage stage = (Stage) txtTitle.getScene().getWindow();
        stage.close();
    }

    public Movie getUserInput() {
        double imdbrating = Double.parseDouble(txtRating.getText());
        String title = txtTitle.getText();
        String file = txtFile.getText();

        selectedMovie.setImdbRating(imdbrating);
        selectedMovie.setTitle(title);
        selectedMovie.setFilePath(file);
        return selectedMovie;
    }

    public void setSelectedMovie(Movie movie) {
        selectedMovie = movie;
    }

}
