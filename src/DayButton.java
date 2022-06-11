import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;

public class DayButton extends Button {

    //int clickCounter = 1;
    Connect connect;

    DayButton(String name){

        super(name);
        setStyle("-fx-font-size:20pt;");

        try {
            connect = new Connect();
            DecimalFormat formatter = new DecimalFormat("00");
            if(connect.getDuties(LocalDate.parse(TaskDashBoard.year+"-"+formatter.format(TaskDashBoard.month)+"-"+formatter.format(Integer.valueOf(name)))).next()){
                setStyle("-fx-background-color:#58D68D;-fx-font-size:20pt;");
            }else {
                setStyle("-fx-font-size:20pt;");
            }
            connect.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        setOnMouseClicked(mouseEvent -> {

            if (mouseEvent.getButton() == MouseButton.PRIMARY){
                TaskDashBoard.setList(Integer.valueOf(name));
            }else if(mouseEvent.getButton() == MouseButton.SECONDARY){
                TaskDashBoard.setDutyTime(Integer.parseInt(name));
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

            /*if(clickCounter %2==0){
                setStyle("-fx-font-size:20pt;");
            }else {
                setStyle("-fx-background-color:#58D68D;-fx-font-size:20pt;");
            }
            clickCounter++;*/

        });
    }
}
