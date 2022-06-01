import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DayButton extends Button {

    int clickCounter = 1;

    DayButton(String name){

        super(name);
        setStyle("-fx-font-size:20pt;");
        setOnMouseClicked(mouseEvent -> {

            if (mouseEvent.getButton() == MouseButton.PRIMARY){
                TaskDashBoard.setList(Integer.valueOf(name));
            }else if(mouseEvent.getButton() == MouseButton.SECONDARY){
                Stage stage = new Stage();
                FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("addDuty.fxml"));
                try {
                    Scene scene = new Scene(fxmlLoader.load());
                    stage.setScene(scene);
                    stage.initModality(Modality.APPLICATION_MODAL);
                    stage.show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(clickCounter %2==0){
                setStyle("-fx-font-size:20pt;");
            }else {
                setStyle("-fx-background-color:#58D68D;-fx-font-size:20pt;");
            }
            clickCounter++;

        });
    }
}
