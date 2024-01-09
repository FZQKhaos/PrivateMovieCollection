package sample.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
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

    public void onActionAdd(ActionEvent actionEvent) {

    }
}
