import javafx.fxml.FXML;

import java.io.IOException;


public class AreYouSureNew {

    public static boolean status = false;
    @FXML
    private void delete(){
        status = true;
        AddReport.modal.close();
        try {
            Main.changeScene("altAddReport.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void cancel(){

        AddReport.modal.close();

    }
}
