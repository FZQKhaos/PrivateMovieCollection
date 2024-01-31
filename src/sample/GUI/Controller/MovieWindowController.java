package sample.GUI.Controller;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import sample.BE.Category;
import sample.BE.Movie;
import sample.GUI.Model.CategoryModel;
import sample.GUI.Model.MovieModel;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class MovieWindowController implements Initializable {

    @FXML
    private TableColumn<Movie, LocalDate> colLastViewed;

    @FXML
    private TableColumn<Movie, String> colTitle, colCategory;

    @FXML
    private TableColumn<Movie, Double> colIR, colUR;

    @FXML
    private TableView<Movie> tblMovies;

    @FXML
    private TextField txtSearchField;

    @FXML
    private ComboBox<Category> cbShowCategory;

    private final MovieModel movieModel;

    private CategoryModel categoryModel;


    private final String folder = "data" + File.separator;

    public MovieWindowController() throws Exception {
        movieModel = new MovieModel();
        categoryModel = new CategoryModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        reminder();
        txfSearchBarListener();
        setupMovieTableView();
        addCategories();
        enableDoubleClick();
        showCategory();
    }

    private void addCategories(){
        for (Movie movie: movieModel.getObservableMovies()){
            try {
                movieModel.getMovieCategories(movie);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void updateCategories(Movie movie, Category category){
        try {
            List<Category> categories = movieModel.getMovieCategories(movie);
            categories.add(category);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void showCategory() {
        try {
            ObservableList<Category> categories = FXCollections.observableArrayList(categoryModel.getAllCategories());
            cbShowCategory.setItems(categories);

            cbShowCategory.setOnAction(event -> {
                try {
                    Category selectedCategory = cbShowCategory.getValue();
                    if (selectedCategory != null) {
                        movieModel.updateMoviesByCategory(selectedCategory);
                        addCategories();
                    } else {
                        movieModel.addAllMoviesToObservable();
                        tblMovies.setItems(movieModel.getObservableMovies());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void enableDoubleClick(){
        tblMovies.setOnMouseClicked(event -> {
            if (event.getButton().equals(MouseButton.PRIMARY) && event.getClickCount() == 2) {
                Movie selectedMovie = tblMovies.getSelectionModel().getSelectedItem();
                if (selectedMovie != null) {
                    String videoFilePath = selectedMovie.getFilePath();
                    playVideo(videoFilePath);
                    try {
                        LocalDate currentDate = LocalDate.now();
                        selectedMovie.setLastView(currentDate);
                        movieModel.updateMovie(selectedMovie);
                        updateTable();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        });

    }

    private void setupMovieTableView(){
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colCategory.setCellValueFactory(new PropertyValueFactory<>("categories"));
        colIR.setCellValueFactory(new PropertyValueFactory<>("imdbRating"));
        colUR.setCellValueFactory(new PropertyValueFactory<>("userRating"));
        colLastViewed.setCellValueFactory(new PropertyValueFactory<>("lastView"));
        tblMovies.setItems(movieModel.getObservableMovies());
    }

    private void txfSearchBarListener() {
        if (cbShowCategory.getSelectionModel().getSelectedItem() == null) {
            txtSearchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                try {
                    movieModel.searchMovies(newValue);
                    addCategories();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            txtSearchField.textProperty().addListener((observableValue, oldValue, newValue) -> {
                try {
                    movieModel.searchMoviesByCategory(newValue,cbShowCategory.getSelectionModel().getSelectedItem());
                    addCategories();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
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
    
    private void reminder(){
        if (!movieChecker().isEmpty()){
            alertBox("Reminder","Movies you might want to remove: " + movieChecker());
        }
    }

    private List<Movie> movieChecker(){
        List<Movie> moviesToRemove = new ArrayList<>();

        LocalDate expirationDate = LocalDate.now().minusYears(2);

        for(Movie movie: movieModel.getObservableMovies()){
            LocalDate lastView = movie.getLastView();

            if (lastView.isBefore(expirationDate) && movie.getUserRating() < 6){
                moviesToRemove.add(movie);
            }
        }
        return moviesToRemove;
    }

    private void alertBox(String title, String content){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void onActionRemoveMovie(ActionEvent actionEvent) throws Exception {
        Movie selectedMovie = tblMovies.getSelectionModel().getSelectedItem();

        if (selectedMovie != null){
            movieModel.deleteMovieCategory(selectedMovie);
            movieModel.deleteMovie(selectedMovie);
        }
    }

    public void addToTable(Movie movie){
        tblMovies.getItems().add(movie);
    }

    public void updateTable() throws Exception {
        tblMovies.getItems().clear();
        movieModel.addAllMoviesToObservable();
        tblMovies.setItems(movieModel.getObservableMovies());
        tblMovies.refresh();
        addCategories();
    }


    public void onActionNewMovie(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NewMovieWindow.fxml"));
        Parent root = loader.load();

        NewMovieWindowController newMovieWindowController = loader.getController();
        newMovieWindowController.setMovieWindowController(this);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);

        stage.setTitle("New Movie");
        stage.show();
    }

    public void onActionAddEditUR(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/UserRating.fxml"));
        Parent root = loader.load();

        UserRatingWindowController userRatingWindowController = loader.getController();
        userRatingWindowController.setMovieWindowController(this);

        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.setTitle("Add/Edit User Rating");
        stage.show();
    }

    public void onActionAddRemoveCategory(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NewCategoryWindow.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));

        stage.setTitle("Add category");
        stage.show();
    }

    public void onActionAddCategoryToMovie(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/AddCategoryToMovieWindow.fxml"));
        Parent root = loader.load();

        AddCategoryToMovieController addCategoryToMovieController = loader.getController();
        addCategoryToMovieController.setMovieWindowController(this);

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/NewMovieWindow.fxml"));
            Stage stage = new Stage();
            stage.setScene(new Scene(loader.load()));

            NewMovieWindowController controller = loader.getController();
            controller.setMovieWindowController(this);
            controller.setSelectedMovie(selectedMovie);
            controller.fillTextFields(selectedMovie);
            stage.show();
        }
    }

    public void OnClearCategory(ActionEvent actionEvent) {
        cbShowCategory.setValue(null);
        addCategories();
    }

    public void onActionOpenCharts(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/ChartWindow.fxml"));
        Parent root = loader.load();

        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);

        stage.setTitle("Charts");
        stage.show();
    }
}
