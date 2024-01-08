package sample.GUI.Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;


public class NewMovieWindowController {

    @FXML
    private ComboBox cbCategory;

    @FXML
    private Button btnCancel;

    @FXML
    private TextField txtRating, txtTitle, txtFile;


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

    public void onActionSave(ActionEvent actionEvent) {
        //placeholder for create movie i movie model
        //movieModel.createMovie();

        String title = txtTitle.getText();
        double imdbrating = Double.parseDouble(txtRating.getText());
        String file = txtFile.getText();

        //create new movie here

        Stage stage = (Stage) btnCancel.getScene().getWindow();
        stage.close();
    }
}
