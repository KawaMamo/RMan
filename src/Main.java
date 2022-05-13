import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    static Stage stagePointer;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Reports Manager!");
        stage.setScene(scene);
        stagePointer = stage;
        setScreenMode(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private void setScreenMode(Stage stage) throws IOException {

        // to avoid transaction from original width to max, set the width and height to max
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        stage.setWidth(bounds.getWidth());
        stage.setHeight(bounds.getHeight());
        // load config file
        Config config = new Config();
        if(config.getProp().getProperty("fullScreen").equals("true")){
            stage.setMaximized(true);
        }

    }

    public static void changeScene(String fxmlFile) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource(fxmlFile));
        stagePointer.getScene().setRoot(fxmlLoader.load());
    }
}