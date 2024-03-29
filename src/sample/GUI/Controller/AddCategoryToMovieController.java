package sample.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.paint.Color;
import sample.BE.Category;
import sample.BE.Movie;
import sample.GUI.Model.CategoryModel;
import sample.GUI.Model.MovieModel;

import java.net.URL;
import java.util.ResourceBundle;

public class AddCategoryToMovieController implements Initializable {

    @FXML
    private ComboBox<Movie> cbMovies;

    @FXML
    private ListView<Category> lstCategory;

    @FXML
    private Label lblStatus;

    private MovieModel movieModel;
    private CategoryModel categoryModel;
    private MovieWindowController movieWindowController;

    public AddCategoryToMovieController() throws Exception {
        movieModel = new MovieModel();
        categoryModel = new CategoryModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cbMovies.setItems(movieModel.getObservableMovies());
        try {
            lstCategory.setItems(categoryModel.getObservableCategories());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void onActionAdd(ActionEvent actionEvent) throws Exception {
        try {
            Movie movie = cbMovies.getSelectionModel().getSelectedItem();
            Category category = lstCategory.getSelectionModel().getSelectedItem();

            movieModel.addCategoryToMovie(movie, category);
            movieWindowController.updateCategories(movie, category);

            lblStatus.setTextFill(Color.DARKGREEN);
            lblStatus.setText("Category added to " + movie);
        } catch (Exception e){
            lblStatus.setTextFill(Color.DARKRED);
            lblStatus.setText("You have to select a movie and a category");
        }
    }

    public void setMovieWindowController(MovieWindowController movieWindowController) {
        this.movieWindowController = movieWindowController;
    }
}
