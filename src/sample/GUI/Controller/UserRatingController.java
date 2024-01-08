package sample.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import sample.GUI.Model.MovieModel;

import java.net.URL;
import java.util.ResourceBundle;

public class UserRatingController implements Initializable {

    @FXML
    private ComboBox cbSelectMovie;
    @FXML
    private TextField txtfNewRating;

    private MovieModel movieModel;

    public UserRatingController() throws Exception {
        movieModel = new MovieModel();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void onActionEdit(ActionEvent actionEvent) {

    }

    public void OnActionCancel(ActionEvent actionEvent) {

    }

}
