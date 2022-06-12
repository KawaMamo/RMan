import javafx.fxml.FXML;


public class AreYouSureNew {

    public static boolean status = false;
    @FXML
    private void delete(){
        status = true;
        AddReport.modal.close();
        System.out.println("AreYouSureNew::delete");
    }

    @FXML
    private void cancel(){

        AddReport.modal.close();

    }
}
