import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.controlsfx.control.Notifications;

import java.io.IOException;
import java.sql.ResultSet;

public class AddDuty {

    ObservableList<String> duties = FXCollections.observableArrayList();

    @FXML
    private TextField dutiesTF;

    @FXML
    private ListView<String> dutiesList;
    Connect connect;
    boolean isEdit =false;


    @FXML
    private void initialize(){

        try {
            connect = new Connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        dutiesList.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                dutiesTF.setText(newValue);
            }
        });

        dutiesList.setItems(duties);

        try {
            ResultSet dutiesSet = connect.getDuties(TaskDashBoard.dutyTime);
            while (dutiesSet.next()){
                duties.add(dutiesSet.getString("description"));
                isEdit = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            Notifications.create().title("unable to load duties").text(e.getMessage()).showError();
        }
    }

    @FXML
    private void addDuty(){
        duties.add(dutiesTF.getText());
        dutiesTF.setText(null);
    }

    @FXML
    private void editDuty(){
        String text = dutiesTF.getText();
        duties.remove(dutiesList.getSelectionModel().getSelectedItem());
        duties.add(text);
    }

    @FXML
    private void deleteDuty(){
        duties.remove(dutiesList.getSelectionModel().getSelectedItem());
    }

    @FXML
    private void submit(){

        try {
            if(isEdit){
                connect.deleteDuties(TaskDashBoard.dutyTime);
            }

            int check = 0;
            for(String object : duties){
                check = connect.addDuty(TaskDashBoard.dutyTime, object);
            }
            if(check != 0){
                Notifications.create().title("نجاح").text("تمت الإضافة بنجاح").show();

            }

        } catch (IOException e) {
            e.printStackTrace();
            Notifications.create().title("unable to connect to DB").text(e.getMessage()).showError();
        } catch (Exception e) {
            e.printStackTrace();
            Notifications.create().title("unable to add duty").text(e.getMessage()).showError();
        }
        try {
            Main.changeScene("taskDashBoard.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
