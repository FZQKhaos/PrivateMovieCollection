package sample.GUI.Controller;




import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import sample.BE.Category;
import sample.GUI.Model.CategoryModel;

import java.net.URL;
import java.util.ResourceBundle;

public class NewCategoryWindowController implements Initializable {
    @FXML
    private Label lblAddStatus;
    @FXML
    private ComboBox<Category> cbCategories;
    @FXML
    private Label lblDeleteStatus;
    @FXML
    private TextField txtCategory;
    private CategoryModel categoryModel;

    public NewCategoryWindowController() throws Exception {
        categoryModel = new CategoryModel();
    }

    public void onActionAdd(ActionEvent actionEvent) throws Exception {
        categoryModel.createCategory(new Category(txtCategory.getText()));

        lblAddStatus.setTextFill(Color.DARKGREEN);
        lblAddStatus.setText("Category added: " + txtCategory.getText());
        txtCategory.clear();
    }

    public void onActionDelete(ActionEvent actionEvent) throws Exception {
        Category selectedCategory = cbCategories.getSelectionModel().getSelectedItem();
        try {
            categoryModel.deleteCategory(selectedCategory);
            lblDeleteStatus.setTextFill(Color.DARKGREEN);
            lblDeleteStatus.setText("Category deleted: " + selectedCategory);

        } catch (Exception e) {
            displayError(e);
        }
    }

    private void displayError(Exception ex)
    {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Something went wrong");
        alert.setHeaderText(ex.getMessage());
        alert.showAndWait();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            cbCategories.setItems(categoryModel.getObservableCategories());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
