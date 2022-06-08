import javafx.fxml.FXML;

import java.io.IOException;

public class AreYouSure {

    public static int deleteId = 0;
    public static Report report = null;
    Connect connect;
    public static String caller;
    @FXML
    private void delete(){
        try {
            connect  = new Connect();
        } catch (IOException e) {
            e.printStackTrace();
        }


        deleteId = report.getId();
        connect.deleteReport(deleteId);
        connect.deleteProjects(deleteId);
        connect.deleteSuggestion(deleteId);
        connect.deleteUploadedImages(deleteId);
        if(caller== "ReportsDetails"){
            ReportsDetails.modal.close();
            try {
                Main.changeScene("reportsDetails.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(caller == "Filtering"){
            Filtering.modal.close();
            try {
                Main.changeScene("filtering.fxml");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }

    @FXML
    private void cancel(){

        if(caller== "ReportsDetails"){
            ReportsDetails.modal.close();
        }else if(caller == "Filtering"){
            Filtering.modal.close();
        }

    }
}
