import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class DayButton extends Button {

    DayButton(String name){
        super(name);
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



        });
    }
}
