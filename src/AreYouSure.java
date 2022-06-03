import javafx.fxml.FXML;

import java.io.IOException;

public class AreYouSure {

    public static int deleteId = 0;
    public static Report report = null;
    Connect connect;
    @FXML
    private void delete(){
        try {
            connect  = new Connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Filtering.reportsList.remove(report);
        deleteId = report.getId();
        connect.deleteReport(deleteId);
        connect.deleteProjects(deleteId);
        connect.deleteSuggestion(deleteId);
        connect.deleteUploadedImages(deleteId);
        Filtering.modal.close();
    }

    @FXML
    private void cancel(){
        Filtering.modal.close();
    }
}
