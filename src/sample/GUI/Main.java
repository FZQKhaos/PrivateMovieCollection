package sample.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/MovieWindow.fxml"));
        Parent root = loader.load();

        primaryStage.setTitle("Private Movie Collection");

        Image image = new Image("/pictures/CuteOtter.png");
        primaryStage.getIcons().add(image);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}
