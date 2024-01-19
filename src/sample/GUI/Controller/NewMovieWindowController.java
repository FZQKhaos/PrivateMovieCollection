package sample.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.BE.Movie;
import sample.GUI.Model.IMDBRatingModel;
import sample.GUI.Model.MovieModel;

import java.io.File;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;


public class NewMovieWindowController {
    @FXML
    private Label lblStatus;
    @FXML
    private TextField txtRating, txtTitle, txtFile;

    private MovieModel movieModel;
    private Movie selectedMovie;

    private MovieWindowController movieWindowController;

    private IMDBRatingModel imdbRatingModel;

    public NewMovieWindowController() throws Exception {
        movieModel = new MovieModel();
        imdbRatingModel = new IMDBRatingModel();
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
            txtFile.setText(selectedFile.getName());
        }

    }


    public void onActionSave(ActionEvent actionEvent) throws Exception {
        try {
                if (selectedMovie != null) {
                    // editing
                    if (!txtTitle.getText().isEmpty()) {
                        movieModel.updateMovie(getUserInput());
                        movieWindowController.updateTable();
                        closeWindow();
                    } else {
                        lblStatus.setTextFill(Color.DARKRED);
                        lblStatus.setText("Remember to give the movie a title");
                    }
                } else {
                    // creating
                    LocalDate currentDate = LocalDate.now();
                    Movie movie = new Movie(Double.parseDouble(txtRating.getText()), txtTitle.getText(), txtFile.getText(), currentDate);
                    movieModel.createMovie(movie);
                    movieWindowController.addToTable(movie);
                    closeWindow();
                }
        } catch (Exception e){
            lblStatus.setText("Fill out all text fields");
        }
    }

    private void closeWindow(){
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

    public void onActionLookUp(ActionEvent actionEvent) throws Exception {
        txtRating.setText(imdbRatingModel.searchImdbRating(txtTitle.getText()));
    }

    public void fillTextFields(Movie movie){
        txtTitle.setText(movie.getTitle());
        txtRating.setText(String.valueOf(movie.getImdbRating()));
        txtFile.setText(movie.getFilePath());
    }
}
