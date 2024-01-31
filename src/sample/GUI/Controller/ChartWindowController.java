package sample.GUI.Controller;

import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import sample.BE.Movie;
import sample.GUI.Model.MovieModel;

import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ChartWindowController implements Initializable {

    @FXML
    private BorderPane borderPane;

    private final MovieModel movieModel;


    public ChartWindowController() throws Exception {
        movieModel = new MovieModel();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        borderPane.setCenter(buildBarChart());
    }

    private PieChart buildPieChart(){
        //Create data
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
        for (Movie m: movieModel.getObservableMovies()) {
            pieChartData.add(new PieChart.Data(m.getTitle(), m.getUserRating()));
        }

        //Binds the data's name property to show both name and value
        pieChartData.forEach(data ->
                data.nameProperty().bind(
                    Bindings.concat(
                        data.getName(), ": ", data.pieValueProperty()
                    )
                )
        );

        //Create PieChart object
        PieChart pieChart = new PieChart(pieChartData);
        pieChart.setLegendVisible(false);
        pieChart.setClockwise(true);
        pieChart.setLabelLineLength(50);
        pieChart.setLabelsVisible(true);
        pieChart.setStartAngle(180);

        //Create context menu and menu item(s)
        ContextMenu contextMenu = new ContextMenu();
        MenuItem miSwitchToBarChart = new MenuItem("Switch to Bar Chart");
        contextMenu.getItems().add(miSwitchToBarChart);

        pieChart.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.SECONDARY) {
                            contextMenu.show(pieChart, event.getScreenX(), event.getScreenY());
                        }
                    }
                });

        miSwitchToBarChart.setOnAction((ActionEvent event) -> {
            borderPane.setCenter(buildBarChart());
        });

        return pieChart;
    }
    private BarChart<String,Number> buildBarChart(){
        //Create Axis
        Axis<String> xAxis = new CategoryAxis();
        xAxis.setLabel("Movies");
        Axis<Number> yAxis = new NumberAxis();
        yAxis.setLabel("User Rating");

        //Create data
        XYChart.Series<String, Number> data = new XYChart.Series<>();
        for (Movie m: movieModel.getObservableMovies()) {
            data.getData().add(new XYChart.Data<>(m.getTitle(), m.getUserRating()));
        }

        //Create BarChart object
        BarChart<String, Number> barChart = new BarChart<>(xAxis,yAxis);
        barChart.setLegendVisible(false);

        //Adds the data to the bar chart
        barChart.getData().add(data);

        //Create context menu and menu item(s)
        ContextMenu contextMenu = new ContextMenu();
        MenuItem miSwitchToBarChart = new MenuItem("Switch to Pie Chart");
        contextMenu.getItems().add(miSwitchToBarChart);


        barChart.addEventHandler(MouseEvent.MOUSE_CLICKED,
                new EventHandler<>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getButton() == MouseButton.SECONDARY) {
                            contextMenu.show(barChart, event.getScreenX(), event.getScreenY());
                        }
                    }
                });

        miSwitchToBarChart.setOnAction((ActionEvent event) -> {
            borderPane.setCenter(buildPieChart());
        });

        return barChart;
    }

    @FXML
    private void onActionShowBarChart(ActionEvent actionEvent) {
        borderPane.setCenter(buildBarChart());
    }

    @FXML
    private void onActionShowPieChart(ActionEvent actionEvent) {
        borderPane.setCenter(buildPieChart());
    }

    @FXML
    private void onActionClose(ActionEvent actionEvent) {
        Stage stage = (Stage) borderPane.getScene().getWindow();
        stage.close();
    }

    @FXML
    private void onActionUpdateData(ActionEvent actionEvent) {
        Node node = borderPane.getCenter();
        if (node instanceof PieChart){
            PieChart pieChart = (PieChart) node;
            pieChart.getData().get(2).setPieValue(8);
        }
    }
}
