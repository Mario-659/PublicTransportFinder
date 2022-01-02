package PublicTransportFinder.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class JavaFXApp extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("home.fxml"));
        primaryStage.setTitle("Public Transport Finder");
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().add(
                new Image(ClassLoader.getSystemResourceAsStream("images/appIcon.png")));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}